/*
 *
 *
 *
 *
 */

package battleship.view;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JPanel {
    private ImageIcon splashScreenIcon = new ImageIcon("src\\res\\splashscreen.png");
    private JProgressBar loadingBar = new JProgressBar();

    /**
     *
     */
    public SplashScreen() {
        setBounds(0, 0, 1280, 720);
        setLayout(null);

        loadingBar.setBounds(340, 345, 600, 30);
        add(loadingBar);
    }

    /**
     *
     */
    public void fakeLoading() {
        for (int percent = 0; percent <= 100; percent++) {
            try {
                loadingBar.setValue(percent);
                percent++;
                Thread.sleep(45);
            } catch (InterruptedException interruptedException) {

            }
        }
    }

    /**
     *
     * @param graphics
     */
    public void paint(Graphics graphics) {
        splashScreenIcon.paintIcon(this, graphics, 0, 0);
    }
}
