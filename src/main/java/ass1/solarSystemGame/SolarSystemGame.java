package ass1.solarSystemGame;

import ass1.GameObject;
import ass1.Mouse;
import ass1.solarSystemGame.engine.SolarSystemGameEngine;
import ass1.solarSystemGame.engine.keybindings.*;
import ass1.solarSystemGame.objects.rocketcamera.RocketCamera;
import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class SolarSystemGame extends JFrame {

    private static SolarSystemGame theGame = new SolarSystemGame();

    private SolarSystemGame() {
        super("Solar System Rocketry");
    }

    private void init() {
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glCapabilities.setSampleBuffers(true);
        glCapabilities.setNumSamples(4);

        GLJPanel gamePanel = new GLJPanel(glCapabilities);

        RocketCamera rocketCamera = new RocketCamera(GameObject.ROOT);
        SolarSystemGameEngine gameEngine = new SolarSystemGameEngine(rocketCamera);

        gamePanel.addGLEventListener(gameEngine);
        gamePanel.addMouseListener(Mouse.theMouse);
        gamePanel.addMouseMotionListener(Mouse.theMouse);

        this.addKeyBindings(gamePanel, gameEngine);

        gameEngine.init();

        FPSAnimator fpsAnimator = new FPSAnimator(gamePanel, 60);
        fpsAnimator.start();

        this.getContentPane().add(gamePanel, BorderLayout.CENTER);
        this.setSize(1024, 768);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addKeyBindings(JComponent jComponent, SolarSystemGameEngine gameEngine) {
        LeftKeyHandler leftKeyHandler = new LeftKeyHandler(gameEngine);
        leftKeyHandler.addTo(jComponent);

        RightKeyHandler rightKeyHandler = new RightKeyHandler(gameEngine);
        rightKeyHandler.addTo(jComponent);

        UpKeyHandler upKeyHandler = new UpKeyHandler(gameEngine);
        upKeyHandler.addTo(jComponent);

        CameraKeyHandler cameraKeyHandler = new CameraKeyHandler(gameEngine);
        cameraKeyHandler.addTo(jComponent);

        ZoomInKeyHandler zoomInKeyHandler = new ZoomInKeyHandler(gameEngine);
        zoomInKeyHandler.addTo(jComponent);

        ZoomOutKeyHandler zoomOutKeyHandler = new ZoomOutKeyHandler(gameEngine);
        zoomOutKeyHandler.addTo(jComponent);

        FireKeyHandler fireKeyHandler = new FireKeyHandler(gameEngine);
        fireKeyHandler.addTo(jComponent);
    }

    public static void main(String[] args) {
        theGame.init();
    }
}
