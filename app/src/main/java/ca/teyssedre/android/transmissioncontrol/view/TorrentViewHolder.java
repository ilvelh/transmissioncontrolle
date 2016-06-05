package ca.teyssedre.android.transmissioncontrol.view;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import ca.teyssedre.android.transmissioncontrol.R;
import ca.teyssedre.android.transmissioncontrol.TransmissionApplication;
import ca.teyssedre.android.transmissioncontrol.model.item.TransmissionElement;
import ca.teyssedre.android.transmissioncontrol.model.item.TransmissionElementStatus;

public class TorrentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "ViewHolder";
    private final OnTorrentClickListener clickListener;
    private final TransmissionApplication application;
    private TextView name;
    private ImageView icon;
    private TextView download;
    private TextView upload;
    private ProgressBar progressBar;
    private TransmissionElement element;

    public TorrentViewHolder(View view, OnTorrentClickListener clickListener) {
        super(view);
        application = (TransmissionApplication) itemView.getContext().getApplicationContext();
        this.clickListener = clickListener;
        name = (TextView) itemView.findViewById(R.id.item_name);
        download = (TextView) itemView.findViewById(R.id.item_download_value);
        upload = (TextView) itemView.findViewById(R.id.item_upload_value);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        icon = (ImageView) itemView.findViewById(R.id.item_icon_status);
        icon.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void updateElement(TransmissionElement element) {
        if (element == null) {
            return;
        }
        this.element = element;
        name.setText(this.element.getName());
        switch (this.element.getStatus()) {
            case STATUS_STOPPED:
                setupStatusIcon(R.drawable.ic_play_circle_filled_black_48dp);
                break;
            case STATUS_CHECK:
            case STATUS_CHECK_WAIT:
            case STATUS_DOWNLOAD:
                setupStatusIcon(R.drawable.ic_pause_circle_filled_black_48dp);
                break;
            case STATUS_SEED:
                setupStatusIcon(R.drawable.ic_file_upload_black_48dp);
                break;
        }
        progressBar.setMax(100);
        progressBar.setProgress((int) (this.element.getPercentDone() * 100));
    }

    @Override
    public void onClick(View v) {
        if (v == itemView) {
            if (clickListener != null) {
                clickListener.onTorrentClick(v, this.element);
            }
        } else if (v == icon) {
            // TODO apply TorrentAction
            switch (this.element.getStatus()) {
                case STATUS_STOPPED:
                    Snackbar.make(itemView, "starting torrent", Snackbar.LENGTH_SHORT)
                            .show();
                    application.StartTorrent(this.element.getId());
                    break;
                case STATUS_SEED:
                case STATUS_SEED_WAIT:
                case STATUS_DOWNLOAD:
                case STATUS_DOWNLOAD_WAIT:

                    Snackbar.make(itemView, "stoping torrent", Snackbar.LENGTH_SHORT)
                            .show();
                    application.StopTorrent(this.element.getId());
                    break;
            }
            if (this.element.getStatus() == TransmissionElementStatus.STATUS_STOPPED) {
            }
        }
    }

    private void setupStatusIcon(int statusIcon) {
        if (icon != null) {
            icon.setImageDrawable(getDrawable(statusIcon));
        }
    }

    @Nullable
    private Drawable getDrawable(int resourceId) {
        if (itemView.getContext() == null)
            return null;
        Drawable d;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d = itemView.getResources().getDrawable(resourceId, itemView.getContext().getTheme());
        } else {
            d = itemView.getResources().getDrawable(resourceId);
        }
        return d;
    }

}
