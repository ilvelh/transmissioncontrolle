package ca.teyssedre.android.transmissioncontrol.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TransmissionProfile implements Parcelable {

    public static final Creator<TransmissionProfile> CREATOR = new Creator<TransmissionProfile>() {
        @Override
        public TransmissionProfile createFromParcel(Parcel in) {
            return new TransmissionProfile(in);
        }

        @Override
        public TransmissionProfile[] newArray(int size) {
            return new TransmissionProfile[size];
        }
    };
    public static String TRANSMISSION_RPC = "/transmission/rpc/";
    private String profileName;
    private String serverName;
    private String path;
    private String username;
    private String password;
    private int port;
    private boolean ssl;
    private boolean ignoreSSL;
    private boolean authentication;

    public TransmissionProfile(String profileName, String serverName, String path, String username,
                               String password, int port, boolean ssl, boolean ignoreSSL) {
        this.profileName = profileName;
        this.serverName = serverName;
        this.path = path;
        this.username = username;
        this.password = password;
        this.port = port;
        this.ssl = ssl;
        this.ignoreSSL = ignoreSSL;
    }


    public TransmissionProfile(String serverName, String username, String password, boolean ssl) {
        this.serverName = serverName;
        this.username = username;
        this.password = password;
        this.port = 443;
        this.ssl = ssl;
    }

    protected TransmissionProfile(Parcel in) {
        profileName = in.readString();
        serverName = in.readString();
        path = in.readString();
        username = in.readString();
        password = in.readString();
        port = in.readInt();
        ssl = in.readByte() != 0;
        ignoreSSL = in.readByte() != 0;
        authentication = in.readByte() != 0;
    }

    public String getRpcUrl() {
        String url = "";
        url += ssl ? "https://" : "http://";
        url += serverName + ":" + port;
        if (path != null) {
            url += "/" + path;
        }
        url += TRANSMISSION_RPC;
        return url;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getServerName() {
        return serverName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public boolean isSsl() {
        return ssl;
    }

    public boolean isIgnoreSSL() {
        return ignoreSSL;
    }

    public boolean hasAuthentication() {
        return authentication;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(profileName);
        parcel.writeString(serverName);
        parcel.writeString(path);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeInt(port);
        parcel.writeByte((byte) (ssl ? 1 : 0));
        parcel.writeByte((byte) (ignoreSSL ? 1 : 0));
        parcel.writeByte((byte) (authentication ? 1 : 0));
    }
}
