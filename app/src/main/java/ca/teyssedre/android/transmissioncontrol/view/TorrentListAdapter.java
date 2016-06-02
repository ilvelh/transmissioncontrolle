package ca.teyssedre.android.transmissioncontrol.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ca.teyssedre.android.transmissioncontrol.R;
import ca.teyssedre.android.transmissioncontrol.model.item.TransmissionElement;

public class TorrentListAdapter extends RecyclerView.Adapter<TorrentViewHolder> {

    private final List<TransmissionElement> data;
    private final OnTorrentClickListener clickListener;

    public TorrentListAdapter(List<TransmissionElement> data, OnTorrentClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    @Override
    public TorrentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new TorrentViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(TorrentViewHolder holder, int position) {
        TransmissionElement element = getItem(position);
        holder.updateElement(element);
    }

    private TransmissionElement getItem(int position) {
        if (position > -1 && position < data.size()) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }
}
