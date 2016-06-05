package ca.teyssedre.android.transmissioncontrol.model.request;

import org.json.JSONException;
import org.json.JSONObject;

public class IdsArgRequest extends JsonArguments {

    private String[] ids;

    public IdsArgRequest(String[] ids) {
        this.ids = ids;
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = super.toJson();
        try {
            StringBuilder b = new StringBuilder();
            b.append("{ ids: [");
            int i = 0;
            for (String id : ids) {
                b.append(id);
                i++;
                if (i < ids.length) {
                    b.append(", ");
                }
            }
            b.append(" ] }");
            object = new JSONObject(b.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
