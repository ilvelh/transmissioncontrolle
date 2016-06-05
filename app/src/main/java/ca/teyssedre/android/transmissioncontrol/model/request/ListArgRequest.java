package ca.teyssedre.android.transmissioncontrol.model.request;

import org.json.JSONException;
import org.json.JSONObject;

public class ListArgRequest extends JsonArguments {

    private static final String fields = "fields:[id, eta, name, addedDate, percentDone, leechers, seeders, status, hashString, peersConnected, files, totalSize ]";
    private final String[] ids;

    public ListArgRequest(String... ids) {
        this.ids = ids;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        try {
            StringBuilder b = new StringBuilder();
            b.append("{");
            b.append(fields);
            if (ids != null && ids.length > 0) {
                b.append(",");
                b.append("ids:[");
                int i = 0;
                for (String id : ids) {
                    b.append(id);
                    i++;
                    if (i < ids.length) {
                        b.append(",");
                    }
                }
                b.append("]");
            }
            b.append("}");

            json = new JSONObject(b.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
