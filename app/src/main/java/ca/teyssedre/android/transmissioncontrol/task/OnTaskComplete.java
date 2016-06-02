package ca.teyssedre.android.transmissioncontrol.task;

public interface OnTaskComplete<T> {

    void onCompleted(T data);

    void onError();
}
