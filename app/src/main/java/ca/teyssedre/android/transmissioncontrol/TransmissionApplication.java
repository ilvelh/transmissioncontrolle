package ca.teyssedre.android.transmissioncontrol;

import android.app.Application;
import android.util.Log;

import java.util.Arrays;

import ca.teyssedre.android.transmissioncontrol.model.TransmissionProfile;
import ca.teyssedre.android.transmissioncontrol.model.request.TransmissionRequest;
import ca.teyssedre.android.transmissioncontrol.model.response.ListArgsResponse;
import ca.teyssedre.android.transmissioncontrol.task.ListItems;
import ca.teyssedre.android.transmissioncontrol.task.OnTaskComplete;
import ca.teyssedre.android.transmissioncontrol.task.StartTorrent;
import ca.teyssedre.android.transmissioncontrol.task.StopTorrent;

public class TransmissionApplication extends Application {

    private static final String TAG = "App";

    private TransmissionProfile profile;
    private OnTaskComplete<TransmissionRequest<ListArgsResponse>> onListResponse;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.w(TAG, "Low memory");
    }

    public TransmissionProfile getProfile() {
        return profile;
    }

    public void setProfile(TransmissionProfile profile) {
        this.profile = profile;
    }

    public void GetTorrents() {
        ListItems task = new ListItems(profile, onListResponse);
    }

    public void StartTorrent(int... ids) {
        StartTorrent start = new StartTorrent(profile, null);
        start.execute(Arrays.toString(ids));
    }

    public void StopTorrent(int... ids) {
        StopTorrent stop = new StopTorrent(profile, null);
        stop.execute(Arrays.toString(ids));
    }

    public void setListResultListener(OnTaskComplete<TransmissionRequest<ListArgsResponse>> listResultListener) {
        this.onListResponse = listResultListener;
    }
}
