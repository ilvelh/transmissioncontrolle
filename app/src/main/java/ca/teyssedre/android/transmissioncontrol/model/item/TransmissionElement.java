package ca.teyssedre.android.transmissioncontrol.model.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransmissionElement implements Parcelable {

    public static final Creator<TransmissionElement> CREATOR = new Creator<TransmissionElement>() {
        @Override
        public TransmissionElement createFromParcel(Parcel in) {
            return new TransmissionElement(in);
        }

        @Override
        public TransmissionElement[] newArray(int size) {
            return new TransmissionElement[size];
        }
    };
    private int id;
    private String name;
    private Date addedDate;
    private int leechers;
    private int seeders;
    private TransmissionElementStatus status;
    private String hashString;
    private int peersConnected;
    private List<TransmissionElementFile> files;
    private double totalSize;
    private double percentDone;

    public TransmissionElement() {
    }

    protected TransmissionElement(Parcel in) {
        id = in.readInt();
        name = in.readString();
        leechers = in.readInt();
        seeders = in.readInt();
        status = TransmissionElementStatus.parse(in.readInt());
        hashString = in.readString();
        peersConnected = in.readInt();
        totalSize = in.readDouble();
        percentDone = in.readDouble();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public int getLeechers() {
        return leechers;
    }

    public void setLeechers(int leechers) {
        this.leechers = leechers;
    }

    public int getSeeders() {
        return seeders;
    }

    public void setSeeders(int seeders) {
        this.seeders = seeders;
    }

    public TransmissionElementStatus getStatus() {
        return status;
    }

    @JsonDeserialize(using = ElementStatusDeserializer.class)
    public void setStatus(TransmissionElementStatus status) {
        this.status = status;
    }

    public String getHashString() {
        return hashString;
    }

    public void setHashString(String hashString) {
        this.hashString = hashString;
    }

    public int getPeersConnected() {
        return peersConnected;
    }

    public void setPeersConnected(int peersConnected) {
        this.peersConnected = peersConnected;
    }

    public List<TransmissionElementFile> getFiles() {
        if (files == null) {
            files = new ArrayList<>();
        }
        return files;
    }

    public void setFiles(List<TransmissionElementFile> files) {
        this.files = files;
    }

    public double getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(double totalSize) {
        this.totalSize = totalSize;
    }

    public double getPercentDone() {
        return percentDone;
    }

    public void setPercentDone(double percentDone) {
        this.percentDone = percentDone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(leechers);
        dest.writeInt(seeders);
        dest.writeInt(status.getCode());
        dest.writeString(hashString);
        dest.writeInt(peersConnected);
        dest.writeDouble(totalSize);
        dest.writeDouble(percentDone);
    }
}
