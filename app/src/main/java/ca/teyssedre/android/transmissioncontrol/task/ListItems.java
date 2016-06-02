package ca.teyssedre.android.transmissioncontrol.task;


import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import ca.teyssedre.android.transmissioncontrol.model.TransmissionProfile;
import ca.teyssedre.android.transmissioncontrol.model.request.ListArgRequest;
import ca.teyssedre.android.transmissioncontrol.model.request.TransmissionRequest;
import ca.teyssedre.android.transmissioncontrol.model.response.ListArgsResponse;
import ca.teyssedre.restclient.HttpClient;
import ca.teyssedre.restclient.HttpRequest;
import ca.teyssedre.restclient.HttpResponse;
import ca.teyssedre.restclient.NoSSLValidation;

public class ListItems extends AsyncTask<String, String, TransmissionRequest<ListArgsResponse>> {

    private static final String TAG = "ListItems";
    HttpClient client;
    TransmissionProfile profile;
    OnTaskComplete<TransmissionRequest<ListArgsResponse>> callback;

    public ListItems(OnTaskComplete<TransmissionRequest<ListArgsResponse>> callback) {
        this.callback = callback;

        client = new HttpClient();
        profile = new TransmissionProfile("dserverd.no-ip.info", "TorDLAccess", "p0l1c3$", true);

        if (profile.isIgnoreSSL()) {
            client.ignoreCertificateValidation(true);
        }
    }

    @Override
    protected TransmissionRequest<ListArgsResponse> doInBackground(String... strings) {

        TransmissionRequest<ListArgRequest> torrents = new TransmissionRequest<>("torrent-get", new ListArgRequest());

        torrents.applyProfile(profile);

        HttpRequest request = torrents.getRequest();
        try {
            request.setSslFactory(new NoSSLValidation());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return executeRequest(request);
    }

    private TransmissionRequest<ListArgsResponse> executeRequest(HttpRequest request) {

        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusCode();

        switch (statusCode) {
            case 200:
                return parseAnswer(response);
            case 409:
                Log.d(TAG, "Getting session id");
                String headerField = request.getConnection().getHeaderField("X-Transmission-Session-Id");
                request.addHeader("X-Transmission-Session-Id", headerField);
                return executeRequest(request);
            default:
                Log.d(TAG, "Request status:" + statusCode + " for " + request.getUrl());
                return null;
        }

    }

    private TransmissionRequest<ListArgsResponse> parseAnswer(HttpResponse response) {
        String data = response.getStringResponse();
        Log.d(TAG, "answer " + data);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        TransmissionRequest<ListArgsResponse> parsed = null;
        try {

            parsed = mapper.readValue(data, new TypeReference<TransmissionRequest<ListArgsResponse>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsed;
    }

    @Override
    protected void onPostExecute(TransmissionRequest<ListArgsResponse> listArgsResponseTransmissionRequest) {

    }
}
