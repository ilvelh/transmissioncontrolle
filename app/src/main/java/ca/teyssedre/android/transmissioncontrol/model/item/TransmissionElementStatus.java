package ca.teyssedre.android.transmissioncontrol.model.item;

public enum TransmissionElementStatus {

    STATUS_UNKNOWN(-1),
    STATUS_STOPPED(0), /* Torrent is stopped */
    STATUS_CHECK_WAIT(1), /* Queued to check files */
    STATUS_CHECK(2), /* Checking files */
    STATUS_DOWNLOAD_WAIT(3), /* Queued to download */
    STATUS_DOWNLOAD(4), /* Downloading */
    STATUS_SEED_WAIT(5), /* Queued to seed */
    STATUS_SEED(6);

    private final int code;

    TransmissionElementStatus(int i) {
        this.code = i;
    }

    public static TransmissionElementStatus parse(int i) {
        switch (i) {
            case 0:
                return STATUS_STOPPED;
            case 1:
                return STATUS_CHECK_WAIT;
            case 2:
                return STATUS_CHECK;
            case 3:
                return STATUS_DOWNLOAD_WAIT;
            case 4:
                return STATUS_DOWNLOAD;
            case 5:
                return STATUS_SEED_WAIT;
            case 6:
                return STATUS_SEED;
            default:
                return STATUS_UNKNOWN;
        }
    }

    public int getCode() {
        return code;
    }
}
