package ca.teyssedre.android.transmissioncontrol.model.request;

import org.json.JSONObject;

public abstract class JSONable implements JSONStringifiable{
    @Override
    public JSONObject toJson() {
        return null;
    }
}
