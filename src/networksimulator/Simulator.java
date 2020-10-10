package networksimulator;

import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Simulator extends JFrame {
    Animation anim;
    InputMessage inputMessage;
    private String msg;

    public Simulator() {
        initUI();
    }

    private void initUI() {
        initInput();

        anim = new Animation();
        add(anim);
        anim.setLayout(null);

        Boundaries boundaries = new Boundaries();
        NetworkUtils util = new NetworkUtils(msg);
        new ApplicationLayer(this, anim, util, boundaries);
        new TransportLayer(this, anim, util, boundaries);
        new Transmission(this, anim, util);

        setResizable(false);
        pack();

        setTitle("Network Simulator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initInput() {
        inputMessage = new InputMessage(this);
        inputMessage.pack();
        inputMessage.setLocationRelativeTo(null);
        inputMessage.setVisible(true);
        msg = inputMessage.getValidatedText();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Simulator sim = new Simulator();
            sim.setVisible(true);
        });
    }
}