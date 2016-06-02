package ca.teyssedre.android.transmissioncontrol.view;

import android.view.View;

import ca.teyssedre.android.transmissioncontrol.model.item.TransmissionElement;

/**
 * 6/2/2016 - 10:12 AM
 *
 * @author pteyssedre
 * @version 1.0
 */
public interface OnTorrentClickListener {

    void onTorrentClick(View view, TransmissionElement element);

    void onTorrentLongClick(View view, TransmissionElement element);
}
