package networksimulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class TransportLayer {
    Boundaries b = new Boundaries();
    Frame frame;
    Animation anim;
    NetworkUtils util;
    JDialog segment;

    public TransportLayer(Frame aFrame, Animation animation, NetworkUtils networkUtils) {
        frame = aFrame;
        anim = animation;
        util = networkUtils;

        JButton btnH1Trans = new JButton("");
        JButton btnH2Trans = new JButton("");

        btnH1Trans.setBounds(b.H1TRANS_X, b.TTRANS_Y, b.RECT_W, b.RECT_H);
        btnH1Trans.setContentAreaFilled(false);
        btnH1Trans.setBorderPainted(false);
        anim.add(btnH1Trans);

        btnH2Trans.setBounds(b.H2TRANS_X, b.TTRANS_Y, b.RECT_W, b.RECT_H);
        btnH2Trans.setContentAreaFilled(false);
        btnH2Trans.setBorderPainted(false);
        anim.add(btnH2Trans);

        btnH1Trans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hasArrivedH1()) {
                    anim.stopAnim();
                    JOptionPane.showMessageDialog(
                            anim,
                            "The message has not arrived.",
                            "H1 Transport Layer",
                            JOptionPane.WARNING_MESSAGE);
                    anim.startAnim();
                } else {
                    anim.stopAnim();
                    createSegment();
                }
            }
        });

        btnH2Trans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hasArrivedH2()) {
                    anim.stopAnim();
                    JOptionPane.showMessageDialog(
                            anim,
                            "The message has not arrived.",
                            "H2 Transport Layer",
                            JOptionPane.WARNING_MESSAGE);
                    anim.startAnim();
                } else {
                    anim.stopAnim();
                    createSegment();
                }
            }
        });
    }

    private Boolean hasArrivedH1() {
        if (anim.getEnvX() <= b.LEFT_X && anim.getEnvY() < b.TTRANS_Y) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean hasArrivedH2() {
        if (anim.getEnvX() < b.RIGHT_X) {
            return false;
        } else {
            return true;
        }
    }

    private void createSegment() {
        int seg_w = 375;
        int seg_h = 425;

        Dimension bit32 = new Dimension(350, 30);
        Dimension bit16 = new Dimension(173, 30);
        Dimension bit8 = new Dimension(85, 30);

        segment = new JDialog(frame, "Transport Layer Segment");
        segment.setPreferredSize(new Dimension(seg_w, seg_h));
        segment.setResizable(false);
        segment.pack();
        segment.setLocationRelativeTo(frame);
        segment.setLayout(new FlowLayout());

        Border blkBorder = BorderFactory.createLineBorder(Color.BLACK);

        // Pseudoheader
        JLabel sourceIPLbl = new JLabel("<html><body>32-bit source IP address<br><center>"
                + util.getSourceIP() + "</center></body></html>", SwingConstants.CENTER);
        JLabel destIPLbl = new JLabel("<html><body>32-bit destination IP address<br><center>"
                + util.getDestIP() + "</center></body></html>", SwingConstants.CENTER);
        JLabel ipReservedLbl = new JLabel("<html><body>Reserved<br><center>"
                + util.getIPReserved() + "</center></body></html>", SwingConstants.CENTER);
        JLabel protocolLbl = new JLabel("<html><body>8-bit protocol<br><center>"
                + util.getProtocol() + "</center></body></html>", SwingConstants.CENTER);
        JLabel lenTCPLbl = new JLabel("<html><body>16-bit TCP total length<br><center>"
                + util.getLenTCP() + "</center></body></html>", SwingConstants.CENTER);

        sourceIPLbl.setPreferredSize(bit32);
        sourceIPLbl.setBackground(Color.BLUE);
        sourceIPLbl.setForeground(Color.WHITE);
        sourceIPLbl.setOpaque(true);

        destIPLbl.setPreferredSize(bit32);
        destIPLbl.setBackground(Color.BLUE);
        destIPLbl.setForeground(Color.WHITE);
        destIPLbl.setOpaque(true);

        ipReservedLbl.setPreferredSize(bit8);
        ipReservedLbl.setBackground(Color.BLUE);
        ipReservedLbl.setForeground(Color.WHITE);
        ipReservedLbl.setOpaque(true);

        protocolLbl.setPreferredSize(bit8);
        protocolLbl.setBackground(Color.BLUE);
        protocolLbl.setForeground(Color.WHITE);
        protocolLbl.setOpaque(true);

        lenTCPLbl.setPreferredSize(bit16);
        lenTCPLbl.setBackground(Color.BLUE);
        lenTCPLbl.setForeground(Color.WHITE);
        lenTCPLbl.setOpaque(true);

        segment.add(sourceIPLbl);
        segment.add(destIPLbl);
        segment.add(ipReservedLbl);
        segment.add(protocolLbl);
        segment.add(lenTCPLbl);

        // Segment
        JLabel sourcePortLbl = new JLabel("<html><body>Source port number<br><center>"
                + util.getSourcePort() + "</center></body></html>", SwingConstants.CENTER);
        JLabel destPortLbl = new JLabel("<html><body>Destination port number<br><center>"
                + util.getDestPort() + "</center></body></html>", SwingConstants.CENTER);
        JLabel seqNumLbl = new JLabel("<html><body>Sequence number<br><center>"
                + util.getSeqNum() + "</center></body></html>", SwingConstants.CENTER);
        JLabel ackNumLbl = new JLabel("<html><body>Acknowledgment number<br><center>"
                + util.getAckNum() + "</center></body></html>", SwingConstants.CENTER);
        JLabel hlenLbl = new JLabel("<html><body>HLEN<br><center>"
                + util.getHlen() + "</center></body></html>", SwingConstants.CENTER);
        JLabel tcpReservedLbl = new JLabel("<html><body>Reserved<br><center>"
                + util.getTCPReserved() + "</center></body></html>", SwingConstants.CENTER);
        JLabel controlLbl = new JLabel("<html><body>Control<br><center>"
                + util.getControl() + "</center></body></html>", SwingConstants.CENTER);
        JLabel windowLbl = new JLabel("<html><body>Window size<br><center>"
                + util.getWindow() + "</center></body></html>", SwingConstants.CENTER);
        JLabel checksumLbl = new JLabel("<html><body>Checksum<br><center>"
                + util.getChecksum() + "</center></body></html>", SwingConstants.CENTER);
        JLabel urgPtrLbl = new JLabel("<html><body>Urgent pointer<br><center>"
                + util.getUrgPtr() + "</center></body></html>", SwingConstants.CENTER);

        sourcePortLbl.setPreferredSize(bit16);
        sourcePortLbl.setBackground(Color.WHITE);
        sourcePortLbl.setBorder(blkBorder);
        sourcePortLbl.setOpaque(true);

        destPortLbl.setPreferredSize(bit16);
        destPortLbl.setBackground(Color.WHITE);
        destPortLbl.setBorder(blkBorder);
        destPortLbl.setOpaque(true);

        seqNumLbl.setPreferredSize(bit32);
        seqNumLbl.setBackground(Color.WHITE);
        seqNumLbl.setBorder(blkBorder);
        seqNumLbl.setOpaque(true);

        ackNumLbl.setPreferredSize(bit32);
        ackNumLbl.setBackground(Color.WHITE);
        ackNumLbl.setBorder(blkBorder);
        ackNumLbl.setOpaque(true);

        hlenLbl.setPreferredSize(new Dimension(45, 30));
        hlenLbl.setBackground(Color.WHITE);
        hlenLbl.setBorder(blkBorder);
        hlenLbl.setOpaque(true);

        tcpReservedLbl.setPreferredSize(new Dimension(60, 30));
        tcpReservedLbl.setBackground(Color.WHITE);
        tcpReservedLbl.setBorder(blkBorder);
        tcpReservedLbl.setOpaque(true);

        controlLbl.setPreferredSize(new Dimension(60, 30));
        controlLbl.setBackground(Color.WHITE);
        controlLbl.setBorder(blkBorder);
        controlLbl.setOpaque(true);

        windowLbl.setPreferredSize(bit16);
        windowLbl.setBackground(Color.WHITE);
        windowLbl.setBorder(blkBorder);
        windowLbl.setOpaque(true);

        checksumLbl.setPreferredSize(bit16);
        checksumLbl.setBackground(Color.WHITE);
        checksumLbl.setBorder(blkBorder);
        checksumLbl.setOpaque(true);

        urgPtrLbl.setPreferredSize(bit16);
        urgPtrLbl.setBackground(Color.WHITE);
        urgPtrLbl.setBorder(blkBorder);
        urgPtrLbl.setOpaque(true);

        segment.add(sourcePortLbl);
        segment.add(destPortLbl);
        segment.add(seqNumLbl);
        segment.add(ackNumLbl);
        segment.add(hlenLbl);
        segment.add(tcpReservedLbl);
        segment.add(controlLbl);
        segment.add(windowLbl);
        segment.add(checksumLbl);
        segment.add(urgPtrLbl);

        // Data
        JLabel dataLbl = new JLabel("<html><body>Data and option<br><center>"
                + util.getData() + "</center></body></html>", SwingConstants.CENTER);

        dataLbl.setPreferredSize(new Dimension(350, 60));
        dataLbl.setBackground(Color.YELLOW);
        dataLbl.setOpaque(true);

        segment.add(dataLbl);

        JButton btnOK = new JButton("OK");
        segment.add(btnOK);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segment.dispose();
                anim.startAnim();
            }
        });

        segment.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                anim.startAnim();
            }
        });

        segment.setVisible(true);
    }
}
