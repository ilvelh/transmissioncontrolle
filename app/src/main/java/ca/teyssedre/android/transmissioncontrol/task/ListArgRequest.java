package ca.teyssedre.android.transmissioncontrol.task;

import org.json.JSONException;
import org.json.JSONObject;

import ca.teyssedre.android.transmissioncontrol.model.request.JSONable;

public class ListArgRequest extends JSONable {

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        try {
            json = new JSONObject("{fields:[id, eta, name, totalSize, addedDate]}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
