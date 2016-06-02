package ca.teyssedre.android.transmissioncontrol.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ca.teyssedre.android.transmissioncontrol.R;
import ca.teyssedre.android.transmissioncontrol.model.item.TransmissionElement;

public class TorrentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final OnTorrentClickListener clickListener;
    private TextView name;
    private TransmissionElement element;

    public TorrentViewHolder(View view, OnTorrentClickListener clickListener) {
        super(view);
        this.clickListener = clickListener;
        name = (TextView) itemView.findViewById(R.id.item_name);
        itemView.setOnClickListener(this);
    }

    public void updateElement(TransmissionElement element) {
        if (element == null) {
            return;
        }
        this.element = element;
        name.setText(this.element.getName());
    }

    @Override
    public void onClick(View v) {
        if (v == itemView) {
            if (clickListener != null) {
                clickListener.onTorrentClick(v, this.element);
            }
        }
    }
}
