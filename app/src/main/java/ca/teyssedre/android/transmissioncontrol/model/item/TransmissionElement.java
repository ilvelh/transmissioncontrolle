package ca.teyssedre.android.transmissioncontrol.model.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransmissionElement {

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

    public TransmissionElement() {
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
}
