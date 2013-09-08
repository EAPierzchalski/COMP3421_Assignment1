package ass1.solarSystemGame;

import ass1.Mouse;
import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 1/09/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class SolarSystemGame {

    public static void main(String[] args) {
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glCapabilities.setSampleBuffers(true);
        glCapabilities.setNumSamples(4);

        SolarSystemGameEngine solarSystemGameEngine = new SolarSystemGameEngine();

        GLJPanel gamePanel = new GLJPanel(glCapabilities);
        JFrame gameFrame = new JFrame("Test Game");

        gamePanel.addGLEventListener(solarSystemGameEngine);
        gamePanel.addKeyListener(solarSystemGameEngine);
        gamePanel.addMouseListener(Mouse.theMouse);
        gamePanel.addMouseMotionListener(Mouse.theMouse);

        FPSAnimator fpsAnimator = new FPSAnimator(60);
        fpsAnimator.add(gamePanel);
        fpsAnimator.start();

        gameFrame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        gameFrame.setSize(1024, 768);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }

}
