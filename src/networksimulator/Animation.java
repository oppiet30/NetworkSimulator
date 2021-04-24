package networksimulator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
public class Animation extends JPanel implements ActionListener {
    Boundaries b = new Boundaries();
    final int A_WIDTH = 1000;
    final int A_HEIGHT = 650;
    final int INITIAL_X = b.INITIAL_X;
    final int INITIAL_Y = b.INITIAL_Y;
    final int DELAY = 5;

    Image blueEnvelope;
    Image background;
    Timer timer;
    int x, y;
    final int TOP_Y = INITIAL_Y;
    final int LEFT_X = b.LEFT_X;
    final int BTM_Y = b.BTM_Y;
    final int RIGHT_X = b.RIGHT_X;
    final int FINAL_X = b.FINAL_X;

    public Animation() {
        initAnim();
    }

    private void loadImage() {
        URL u1 = Animation.class.getResource("/resources/blueEnvelope.png");
        URL u2 = Animation.class.getResource("/resources/NetworkConfiguration.png");
        blueEnvelope = new ImageIcon(u1).getImage();
        background = new ImageIcon(u2).getImage();
    }

    private void initAnim() {
        setPreferredSize(new Dimension(A_WIDTH, A_HEIGHT));

        URL u3 = Animation.class.getResource("/resources/pause.png");
        Icon pause = new ImageIcon(u3);
        JButton btnPause = new JButton(pause);
        btnPause.setBounds(390, 0, 100, 100);
        btnPause.setContentAreaFilled(false);
        btnPause.setBorderPainted(false);
        add(btnPause);

        URL u4 = Animation.class.getResource("/resources/play.png");
        Icon play = new ImageIcon(u4);
        JButton btnPlay = new JButton(play);
        btnPlay.setBounds(510, 0, 100, 100);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setBorderPainted(false);
        add(btnPlay);

        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAnim();
            }
        });

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAnim();
            }
        });

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawImages(g);
    }

    private void drawImages(Graphics g) {
        g.drawImage(background, 0, 0, this);
        g.drawImage(blueEnvelope, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (x < LEFT_X && y == TOP_Y) {
            x += 1;
        } else if (x == LEFT_X && y < BTM_Y) {
            y += 1;
        } else if (x < RIGHT_X && y == BTM_Y) {
            x += 1;
        } else if (x == RIGHT_X && y > TOP_Y) {
            y -= 1;
        } else if (x < FINAL_X && y == TOP_Y) {
            x += 1;
        }
        repaint();
    }

    public int getEnvX() {
        return x;
    }

    public int getEnvY() {
        return y;
    }

    public void startAnim() {
        timer.start();
    }

    public void stopAnim() {
        timer.stop();
    }
}