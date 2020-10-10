package networksimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationLayer {
    Boundaries b;
    Frame frame;
    Animation anim;
    NetworkUtils util;

    public ApplicationLayer(Frame aFrame, Animation animation, NetworkUtils networkUtils, Boundaries boundaries) {
        frame = aFrame;
        anim = animation;
        util = networkUtils;
        b = boundaries;

        JButton btnH1App = new JButton("");
        JButton btnH2App = new JButton("");

        btnH1App.setBounds(b.H1APP_X, b.TAPP_Y, b.RECT_W, b.RECT_H);
        btnH1App.setContentAreaFilled(false);
        btnH1App.setBorderPainted(false);
        anim.add(btnH1App);

        btnH2App.setBounds(b.H2APP_X, b.TAPP_Y, b.RECT_W, b.RECT_H);
        btnH2App.setContentAreaFilled(false);
        btnH2App.setBorderPainted(false);
        anim.add(btnH2App);

        btnH1App.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hasArrivedH1()) {
                    anim.stopAnim();
                    JOptionPane.showMessageDialog(
                            anim,
                            "The message has not arrived.",
                            "H1 Application Layer",
                            JOptionPane.WARNING_MESSAGE
                    );
                    anim.startAnim();
                } else {
                    anim.stopAnim();
                    JOptionPane.showMessageDialog(
                            anim,
                            util.getMessage(),
                            "User Input",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    anim.startAnim();
                }
            }
        });

        btnH2App.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hasArrivedH2()) {
                    anim.stopAnim();
                    JOptionPane.showMessageDialog(
                            anim,
                            "The message has not arrived.",
                            "H2 Application Layer",
                            JOptionPane.WARNING_MESSAGE
                    );
                    anim.startAnim();
                } else {
                    anim.stopAnim();
                    JOptionPane.showMessageDialog(
                            anim,
                            util.getMessage(),
                            "User Input",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    anim.startAnim();
                }
            }
        });
    }

    private Boolean hasArrivedH1() {
        if (anim.getEnvX() < b.LEFT_X - 172/2) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean hasArrivedH2() {
        if (anim.getEnvX() < b.RIGHT_X || anim.getEnvY() > b.BAPP_Y - 35) {
            return false;
        } else {
            return true;
        }
    }
}
