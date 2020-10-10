package networksimulator;

import java.util.ArrayList;
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
    private String reservedControl;
    private String window;
    private String checksum;
    private String urgPtr;

    // Data and option field
    private String msg;
    private String data;
    private int dataLength = 0;
    private int dataSum = 0;

    // For calculating checksum
    private int sIP1;   // source IP
    private int sIP2;
    private int dIP1;   // dest IP
    private int dIP2;
    private int sn1;    // seq num
    private int sn2;
    private int an1;    // ack num
    private int an2;
    private int tlen;   // TCP length
    private int sp;     // source port
    private int dp;     // dest port
    private int win;    // window

    public NetworkUtils(String message) {
        this.sourceIP = genSourceIPAddress();
        this.destIP = genDestIPAddress();
        this.ipReserved = "00";
        this.protocol = "06";

        this.sourcePort = genWellKnownPortNumber();
        this.destPort = genTempPortNumber();
        this.seqNum = genSeqNum();
        this.ackNum = genAckNum();
        this.hlen = "5";
        this.reservedControl = "0 00";
        this.window = genWindowSize();
        this.checksum = "";
        this.urgPtr = "00 00";

        this.msg = message;
        this.data = convertMsgToHex(message);
        this.dataLength = dataLength;
        this.dataSum = dataSum;
        this.lenTCP = calculateTCPLength();
        this.checksum = calculateChecksum();
    }

    private String genSourceIPAddress() {
        String first = Integer.toHexString(random.nextInt((223 - 192) + 1) + 192);
        String second = Integer.toHexString(random.nextInt(255));
        String third = Integer.toHexString(random.nextInt((254 - 1) + 1) + 1);
        String fourth = Integer.toHexString(random.nextInt((254 - 1) + 1) + 1);

        sIP1 = Integer.parseInt(first + second, 16);
        sIP2 = Integer.parseInt(third + fourth, 16);

        return first + " " + second + " " + third + " " + fourth;
    }

    private String genDestIPAddress() {
        String first = Integer.toHexString(random.nextInt((223 - 192) + 1) + 192);
        String second = Integer.toHexString(random.nextInt(255));
        String third = Integer.toHexString(random.nextInt((254 - 1) + 1) + 1);
        String fourth = Integer.toHexString(random.nextInt((254 - 1) + 1) + 1);

        dIP1 = Integer.parseInt(first + second, 16);
        dIP2 = Integer.parseInt(third + fourth, 16);

        if (first.length() < 2) {
            first = String.format("%1$" + 2 + "s", first).replace(' ', '0');
        }
        if (second.length() < 2) {
            second = String.format("%1$" + 2 + "s", second).replace(' ', '0');
        }
        if (third.length() < 2) {
            third = String.format("%1$" + 2 + "s", third).replace(' ', '0');
        }
        if (fourth.length() < 2) {
            fourth = String.format("%1$" + 2 + "s", fourth).replace(' ', '0');
        }

        return first + " " + second + " " + third + " " + fourth;
    }

    private String genWellKnownPortNumber() {
        String[] wkPorts = {"00 07", "00 14", "00 15", "00 16", "00 17", "00 19", "00 35", "00 43",
                "00 45","00 50", "00 6f", "00 7b", "00 8f", "01 bb"};

        String port = wkPorts[random.nextInt(wkPorts.length)];
        sp = Integer.parseInt(port.substring(0, 2) + port.substring(3, 5), 16);

        return port;
    }

    private String genTempPortNumber() {
        String num = Integer.toHexString(random.nextInt((65535 - 49152) + 1) + 49152);
        dp = Integer.parseInt(num.substring(0, 2) + num.substring(2, 4), 16);
        return num.substring(0, 2) + " " + num.substring(2, 4);
    }

    private String genSeqNum() {
        String num = Long.toHexString((long)(random.nextDouble() * (Math.pow(2, 32)) + 1));
        if (num.length() < 8) {
            num = String.format("%1$" + 8 + "s", num).replace(' ', '0');
        }
        String seq = num.substring(0, 2) + " " + num.substring(2, 4)
                + " " + num.substring(4, 6) + " " + num.substring(6, 8);

        sn1 = Integer.parseInt(num.substring(0, 2) + num.substring(2, 4), 16);
        sn2 = Integer.parseInt(num.substring(4, 6) + num.substring(6, 8), 16);

        return seq;
    }

    private String genAckNum() {
        String num = Long.toHexString((long)(random.nextDouble() * (Math.pow(2, 32)) + 1));
        if (num.length() < 8) {
            num = String.format("%1$" + 8 + "s", num).replace(' ', '0');
        }
        String ack = num.substring(0, 2) + " " + num.substring(2, 4)
                + " " + num.substring(4, 6) + " " + num.substring(6, 8);

        an1 = Integer.parseInt(num.substring(0, 2) + num.substring(2, 4), 16);
        an2 = Integer.parseInt(num.substring(4, 6) + num.substring(6, 8), 16);

        return ack;
    }

    private String genWindowSize() {
        String num = Integer.toHexString(random.nextInt(65536));
        if (num.length() < 4) {
            num = String.format("%1$" + 4 + "s", num).replace(' ', '0');
        }
        win = Integer.parseInt(num.substring(0, 2) + num.substring(2, 4), 16);
        return num.substring(0, 2) + " " + num.substring(2, 4);
    }

    private String convertMsgToHex(String msg) {
        char ch[] = msg.toCharArray();
        StringBuffer sb = new StringBuffer();
        ArrayList<String> dataList = new ArrayList<>();

        for (int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            if (i == ch.length - 1) {
                sb.append(hexString);
            } else {
                sb.append(hexString + " ");
            }
            dataLength += 1;
        }

        for (int i = 0; i < dataLength; i += 2) {
            if (i + 1 < dataLength) {
                dataList.add(Integer.toHexString(ch[i]) + "" + Integer.toHexString(ch[i + 1]));
            } else {
                dataList.add(Integer.toHexString(ch[i]) + "00");
            }
        }

        for (int i = 0; i < dataList.size(); i++) {
            dataSum += Integer.parseInt(dataList.get(i), 16);
        }

        String str = sb.toString();

        return str;
    }

    private String calculateTCPLength() {
        String num = Integer.toHexString(Integer.parseInt(hlen) * 4 + dataLength);
        if (num.length() < 4) {
            num = String.format("%1$" + 4 + "s", num).replace(' ', '0');
        }
        String len = num.substring(0, 2) + " " + num.substring(2, 4);
        tlen = Integer.parseInt(num.substring(0, 2) + num.substring(2, 4), 16);

        return len;
    }

    private String calculateChecksum() {
        int sum = sIP1 + sIP2 + dIP1 + dIP2 + 6 + tlen + sp + dp + sn1 + sn2 + an1 + an2 + 20480 + win + dataSum;

        String subtotal = Integer.toHexString(sum);

        if (subtotal.length() > 4) {
            subtotal = splitAndAdd(subtotal);
        }

        int total = Integer.parseInt(subtotal.substring(subtotal.length() - 4), 16);

        String calcChecksum = Integer.toHexString(~total);
        calcChecksum = calcChecksum.substring(calcChecksum.length() - 4);

        return calcChecksum.substring(0, 2) + " " + calcChecksum.substring(2, 4);
    }

    private String splitAndAdd(String subtotal) {
        ArrayList<String> pieces = new ArrayList<String>();
        int sum = 0;
        String sumStr = "12345";

        while (sumStr.length() > 4) {
            for (int i = subtotal.length(); i > 0; i -= 4) {
                if (subtotal.length() > 4) {
                    String piece = subtotal.substring(subtotal.length() - 4);
                    pieces.add(piece);
                    subtotal = subtotal.substring(0, subtotal.length() - 4);
                } else {
                    pieces.add(subtotal);
                }
            }

            for (int i = 0; i < pieces.size(); i++) {
                sum += Integer.parseInt(pieces.get(i), 16);
            }

            sumStr = Integer.toHexString(sum);
        }
        if (sumStr.length() < 4) {
            sumStr = String.format("%1$" + 4 + "s", sumStr).replace(' ', '0');
        }
        return sumStr;
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

    public String getHLEN() {
        return hlen;
    }

    public String getReservedControl() {
        return reservedControl;
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

    public String getMessage() {
        return msg;
    }

    public String getData() {
        return data;
    }
}

