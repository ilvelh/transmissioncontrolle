package ca.teyssedre.android.transmissioncontrol.model.request;

import org.json.JSONObject;

public abstract class JsonArguments implements JsonTransformable {
    @Override
    public JSONObject toJson() {
        return new JSONObject();
    }
}
