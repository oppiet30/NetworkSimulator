package networksimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Transmission {
    Frame frame;
    Animation anim;
    NetworkUtils util;
    JDialog bitSequence;

    private Map<String, String> digiMap = new HashMap<>();
    private String sourcePort;
    private String destPort;
    private String seqNum;
    private String ackNum;
    private String hlen;
    private String reservedControl;
    private String window;
    private String urgPtr;
    private String data;
    private String bitSeq;

    public Transmission(Frame aFrame, Animation animation, NetworkUtils networkUtils) {
        frame = aFrame;
        anim = animation;
        util = networkUtils;

        digiMap.put("0", "0000");
        digiMap.put("1", "0001");
        digiMap.put("2", "0010");
        digiMap.put("3", "0011");
        digiMap.put("4", "0100");
        digiMap.put("5", "0101");
        digiMap.put("6", "0110");
        digiMap.put("7", "0111");
        digiMap.put("8", "1000");
        digiMap.put("9", "1001");
        digiMap.put("a", "1010");
        digiMap.put("b", "1011");
        digiMap.put("c", "1100");
        digiMap.put("d", "1101");
        digiMap.put("e", "1110");
        digiMap.put("f", "1111");

        sourcePort = hexToBinary(util.getSourcePort().replaceAll("\\s", ""));
        destPort = hexToBinary(util.getDestPort().replaceAll("\\s", ""));
        seqNum = hexToBinary(util.getSeqNum().replaceAll("\\s", ""));
        ackNum = hexToBinary(util.getAckNum().replaceAll("\\s", ""));
        hlen = hexToBinary(util.getHLEN());
        reservedControl = hexToBinary(util.getReservedControl().replaceAll("\\s", ""));
        window = hexToBinary(util.getWindow().replaceAll("\\s", ""));
        urgPtr = hexToBinary(util.getUrgPtr().replaceAll("\\s", ""));
        data = hexToBinary(util.getData().replaceAll("\\s", ""));

        bitSeq = sourcePort + destPort + seqNum + ackNum + hlen + reservedControl + window
                + urgPtr + data;

        System.out.println(bitSeq);

        JButton btnLink = new JButton("");
        btnLink.setBounds(238, 552, 524, 30);
        btnLink.setContentAreaFilled(false);
        btnLink.setBorderPainted(false);
        anim.add(btnLink);

        btnLink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hasArrived()) {
                    anim.stopAnim();
                    JOptionPane.showMessageDialog(
                            anim,
                            "The message has not arrived.",
                            "Bit Sequence Transmission",
                            JOptionPane.WARNING_MESSAGE
                    );
                    anim.startAnim();
                } else {
                    anim.stopAnim();
                    createBitSequence();
                    anim.startAnim();
                }
            }
        });
    }

    private String hexToBinary(String s) {
        char[] hex = s.toCharArray();
        String binaryString = "";
        for (char h : hex) {
            binaryString = binaryString + digiMap.get(String.valueOf(h));
        }
        return binaryString;
    }

    private Boolean hasArrived() {
        if (anim.getEnvX() < 238) {
            return false;
        } else {
            return true;
        }
    }

    private void createBitSequence() {
        String html = "<html><body style='width: %1spx'>%1s";
        JOptionPane pane = new JOptionPane();

        pane.showMessageDialog(
                null,
                String.format(html, 100, bitSeq),
                "Bit Sequence Transmission",
                JOptionPane.PLAIN_MESSAGE
        );
    }
}
