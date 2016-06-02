package ca.teyssedre.android.transmissioncontrol.model;

public class TransmissionProfile {

    public static String TRANSMISSION_RPC = "/transmission/rpc/";

    private String serverName;
    private String path;
    private String username;
    private String password;
    private int port;
    private boolean ssl;
    private boolean ignoreSSL;

    public TransmissionProfile(String serverName, String username, String password, int port, boolean ssl) {
        this.serverName = serverName;
        this.username = username;
        this.password = password;
        this.port = port;
        this.ssl = ssl;
    }

    public TransmissionProfile(String serverName, String username, String password, boolean ssl) {
        this.serverName = serverName;
        this.username = username;
        this.password = password;
        this.port = 443;
        this.ssl = ssl;
    }

    public TransmissionProfile(String serverName, String path, String username, String password, boolean ssl) {
        this.serverName = serverName;
        this.path = path;
        this.username = username;
        this.password = password;
        this.ssl = ssl;
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
}
