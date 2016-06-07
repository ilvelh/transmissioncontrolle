package ca.teyssedre.android.transmissioncontrol.task;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import ca.teyssedre.android.transmissioncontrol.model.TransmissionProfile;
import ca.teyssedre.android.transmissioncontrol.model.request.IdsArgRequest;
import ca.teyssedre.android.transmissioncontrol.model.request.ListArgRequest;
import ca.teyssedre.android.transmissioncontrol.model.request.TransmissionRequest;
import ca.teyssedre.android.transmissioncontrol.model.response.ListArgsResponse;
import ca.teyssedre.restclient.HttpClient;
import ca.teyssedre.restclient.HttpRequest;
import ca.teyssedre.restclient.HttpResponse;

public class StartTorrent extends AsyncTask<String, String, TransmissionRequest<ListArgsResponse>> {

    private static final String TAG = "StartTorrent";
    private final HttpClient client;
    private final TransmissionProfile profile;
    OnTaskComplete<TransmissionRequest<ListArgsResponse>> callback;

    public StartTorrent(TransmissionProfile profile, OnTaskComplete<TransmissionRequest<ListArgsResponse>> callback) {

        this.callback = callback;

        client = new HttpClient();

        this.profile = profile;

        if (this.profile.isIgnoreSSL()) {
            client.ignoreCertificateValidation(true);
        }
    }

    @Override
    protected TransmissionRequest<ListArgsResponse> doInBackground(String... strings) {

        TransmissionRequest<IdsArgRequest> set = new TransmissionRequest<>("torrent-start", new IdsArgRequest(strings));
        TransmissionRequest<ListArgRequest> get = new TransmissionRequest<>("torrent-get", new ListArgRequest(strings));

        set.applyProfile(profile);
        executeRequest(set.getRequest());

        get.applyProfile(profile);
        executeRequest(get.getRequest());

        return parseAnswer(get.getRequest().getResponse());
    }

    @Override
    protected void onPostExecute(TransmissionRequest<ListArgsResponse> result) {
        if (callback != null) {
            if (result != null) {
                callback.onCompleted(result);
            } else {
                callback.onError();
            }
        }
    }

    private void executeRequest(HttpRequest request) {

        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusCode();

        switch (statusCode) {
            case 200:
                Log.d(TAG, "StartTorrent success");
            case 409:
                Log.d(TAG, "Getting session id");
                String headerField = request.getConnection().getHeaderField("X-Transmission-Session-Id");
                if (headerField == null || headerField.length() == 0) {
                    //TODO: error no data;
                    return;
                }
                profile.setSessionId(headerField);
                request.addHeader("X-Transmission-Session-Id", headerField);
                executeRequest(request);
            default:
                Log.e(TAG, "Request status:" + statusCode + " for " + request.getUrl());
        }

    }

    private TransmissionRequest<ListArgsResponse> parseAnswer(HttpResponse response) {
        String data = response.getStringResponse();
        if (data == null) {
            return null;
        }
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
}
