package ass1.solarSystemGame;

import ass1.GameEngine;
import ass1.GameObject;
import ass1.solarSystemGame.objects.rocket.Rocket;
import ass1.solarSystemGame.objects.rocketcamera.RocketCamera;
import ass1.solarSystemGame.objects.sun.Sun;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame
 * Project: COMP3421_Assignment1
 */

public class SolarSystemGameEngine extends GameEngine implements KeyListener {

    private Rocket rocket;

    private Sun sun;

    private RocketCamera rocketCamera;

    private double angleStep = 2;

    /**
     * Construct a new game engine.
     *
     */
    public SolarSystemGameEngine(RocketCamera camera) {
        super(camera);
        this.rocketCamera = camera;
        this.sun = new Sun(GameObject.ROOT);
        this.rocket = new Rocket(GameObject.ROOT);
        this.rocket.translate(20, 0);
        this.rocketCamera.setTarget(rocket);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'c') {
            this.rocketCamera.setFollowingTargetRotation(!this.rocketCamera.isFollowingTargetRotation());
        } else if (e.getKeyChar() == 'a') {
            this.rocket.accelerateAngularVelocity(angleStep);
        } else if (e.getKeyChar() == 'd') {
            this.rocket.accelerateAngularVelocity(-angleStep);
        } else if (e.getKeyChar() == 'w') {
            this.rocket.getFlame().turnOn();
            this.rocket.increaseImpulse(5);
        }
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
