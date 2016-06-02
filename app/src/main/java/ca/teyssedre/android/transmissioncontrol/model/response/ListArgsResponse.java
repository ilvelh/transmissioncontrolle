package ca.teyssedre.android.transmissioncontrol.model.response;

import java.util.List;

import ca.teyssedre.android.transmissioncontrol.model.item.TransmissionElement;
import ca.teyssedre.android.transmissioncontrol.model.request.JsonArguments;

/**
 * 6/1/2016 - 3:59 PM
 *
 * @author pteyssedre
 * @version 1.0
 */
public class ListArgsResponse extends JsonArguments {

    private List<TransmissionElement> torrents;

    public ListArgsResponse() {
    }

    public List<TransmissionElement> getTorrents() {
        return torrents;
    }

    public void setTorrents(List<TransmissionElement> torrents) {
        this.torrents = torrents;
    }
}
