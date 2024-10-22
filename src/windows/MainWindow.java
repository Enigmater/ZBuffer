package windows;

import constants.Constants;
import logic.ZBuffer;

import javax.swing.*;

public class MainWindow extends  JFrame implements Runnable {

    public MainWindow() {

        setupView();
        setVisible(true);
    }

    private void setupView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("PI-11 Tihonov");
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }
}
