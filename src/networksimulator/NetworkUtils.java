package networksimulator;

import java.util.Random;

public class NetworkUtils {
    Random random = new Random();

    // Pseudoheader fields
    private String sourceIP;
    private String destIP;
    private String ipReserved;
    private String protocol;
    private String lenTCP;

    // TCP segment fields
    private String sourcePort;
    private String destPort;
    private String seqNum;
    private String ackNum;
    private String hlen;
    private String tcpReserved;
    private String control;
    private String window;
    private String checksum;
    private String urgPtr;

    // Data and option field
    private String data;

    public NetworkUtils(String message) {
        this.sourceIP = getIPAddress();
        this.destIP = getIPAddress();
        this.ipReserved = "00";
        this.protocol = "06";
        this.lenTCP = ""; // hlen + data

        this.sourcePort = getWellKnownPortNumber();
        this.destPort = "";
        this.seqNum = "";
        this.ackNum = "";
        this.hlen = "";
        this.tcpReserved = "000000";
        this.control = "000000";
        this.window = "";
        this.checksum = "";
        this.urgPtr = "";

        this.data = message;

    }

    private String getIPAddress() {
        String a = Integer.toHexString(random.nextInt((223 - 192) + 1) + 192);
        String b = Integer.toHexString(random.nextInt(255));
        String c = Integer.toHexString(random.nextInt((254 - 1) + 1) + 1);
        String d = Integer.toHexString(random.nextInt((254 - 1) + 1) + 1);
        return a + " " + b + " " + c + " " + d;
    }

    private String getWellKnownPortNumber() {
        String[] wkPorts = {"00 07", "00 14", "00 15", "00 16", "00 17", "00 19", "00 35", "00 43",
                "00 45","00 50", "00 6f", "00 7b", "00 8f", "01 bb"};

        return wkPorts[random.nextInt(wkPorts.length)];
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public String getDestIP() {
        return destIP;
    }

    public String getIPReserved() {
        return ipReserved;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getLenTCP() {
        return lenTCP;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public String getDestPort() {
        return destPort;
    }

    public String getSeqNum() {
        return seqNum;
    }

    public String getAckNum() {
        return ackNum;
    }

    public String getHlen() {
        return hlen;
    }

    public String getTCPReserved() {
        return tcpReserved;
    }

    public String getControl() {
        return control;
    }

    public String getWindow() {
        return window;
    }

    public String getChecksum() {
        return checksum;
    }

    public String getUrgPtr() {
        return urgPtr;
    }

    public String getData() {
        return data;
    }
}

