package ca.teyssedre.android.transmissioncontrol.model.request;

import org.json.JSONObject;

public interface JsonTransformable {

    JSONObject toJson();
}
