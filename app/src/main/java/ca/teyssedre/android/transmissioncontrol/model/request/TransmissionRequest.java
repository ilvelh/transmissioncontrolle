package ca.teyssedre.android.transmissioncontrol.model.request;


import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import ca.teyssedre.android.transmissioncontrol.model.TransmissionProfile;
import ca.teyssedre.restclient.HttpContentType;
import ca.teyssedre.restclient.HttpRequest;
import ca.teyssedre.restclient.HttpRequestType;
import ca.teyssedre.restclient.NoSSLValidation;

public class TransmissionRequest<T extends JsonArguments> {

    private static final String TAG = "TRequest";
    public T arguments;
    public String method;
    public int tag;

    private HttpRequest request;

    public TransmissionRequest() {
    }

    public TransmissionRequest(String method, T instance) {
        this.method = method;
        Random random = new Random();
        this.tag = random.nextInt();
        this.request = new HttpRequest();
        this.request.setType(HttpRequestType.POST);
        this.arguments = instance;
    }

    public void applyProfile(TransmissionProfile profile) {
        this.request.setUrl(profile.getRpcUrl());
        this.request.addBasic(profile.getUsername() + ":" + profile.getPassword());
        this.request.setContentType(HttpContentType.APPLICATION_JSON);
        this.request.addData(this.toString());
        if (profile.getSessionId() != null) {
            this.request.addHeader("X-Transmission-Session-Id", profile.getSessionId());
        }
        if (profile.isIgnoreSSL()) {
            try {
                this.request.setSslFactory(new NoSSLValidation());
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        JSONObject jsonObject = toJson();
        Log.d(TAG, "processing data as " + jsonObject.toString());
        return jsonObject.toString();
    }

    private JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("method", method);
            json.put("tag", tag);
            if (arguments != null) {
                json.put("arguments", arguments.toJson());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @JsonIgnore
    public HttpRequest getRequest() {
        return request;
    }
}
