package networksimulator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
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
        ImageIcon envImg = new ImageIcon("src/resources/blueEnvelope.png");
        blueEnvelope = envImg.getImage();
        ImageIcon bgImg = new ImageIcon("src/resources/NetworkConfiguration.png");
        background = bgImg.getImage();
    }

    private void initAnim() {
        setPreferredSize(new Dimension(A_WIDTH, A_HEIGHT));

        Icon pause = new ImageIcon("src/resources/pause.png");
        JButton btnPause = new JButton(pause);
        btnPause.setBounds(390, 0, 100, 100);
        btnPause.setContentAreaFilled(false);
        btnPause.setBorderPainted(false);
        add(btnPause);

        Icon play = new ImageIcon("src/resources/play.png");
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