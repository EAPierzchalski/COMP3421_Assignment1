package ass1.solarSystemGame;

import ass1.GameEngine;
import ass1.GameObject;
import ass1.solarSystemGame.objects.rocket.Rocket;
import ass1.solarSystemGame.objects.rocketcamera.RocketCamera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame
 * Project: COMP3421_Assignment1
 */

public class SolarSystemGameEngine extends GameEngine implements KeyListener {

    public Rocket rocket;

    public static RocketCamera rocketCamera = new RocketCamera();

    /**
     * Construct a new game engine.
     *
     */
    public SolarSystemGameEngine() {
        super(rocketCamera);
        rocket = new Rocket(GameObject.ROOT);
        rocketCamera.setParent(rocket);
    }

    public void init() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
