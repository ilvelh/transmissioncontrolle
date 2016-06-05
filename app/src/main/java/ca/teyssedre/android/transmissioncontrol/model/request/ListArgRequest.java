package ca.teyssedre.android.transmissioncontrol.model.request;

import org.json.JSONException;
import org.json.JSONObject;

import ca.teyssedre.android.transmissioncontrol.model.request.JsonArguments;

public class ListArgRequest extends JsonArguments {

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        try {
            json = new JSONObject("{fields:[id, eta, name, addedDate, percentDone, leechers, seeders, status, hashString, peersConnected, files, totalSize ]}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
