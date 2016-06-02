package ca.teyssedre.android.transmissioncontrol.model.item;

public class TransmissionElementFile {

    private int bytesCompleted;
    private int length;
    private String name;

    public TransmissionElementFile() {
    }

    public int getBytesCompleted() {
        return bytesCompleted;
    }

    public void setBytesCompleted(int bytesCompleted) {
        this.bytesCompleted = bytesCompleted;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
