package ass1.solarSystemGame.engine;

import ass1.GameEngine;
import ass1.GameObject;
import ass1.solarSystemGame.objects.rocket.Rocket;
import ass1.solarSystemGame.objects.rocketcamera.RocketCamera;
import ass1.solarSystemGame.objects.sun.Sun;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class SolarSystemGameEngine extends GameEngine {

    private RocketCamera rocketCamera;
    private Sun sun;
    private Rocket rocket;

    public SolarSystemGameEngine(RocketCamera rocketCamera) {
        super(rocketCamera);
        this.rocketCamera = rocketCamera;
    }

    public void init() {
        this.sun = new Sun(GameObject.ROOT);
        this.rocket = new Rocket(GameObject.ROOT);
        rocketCamera.setTarget(rocket);
        rocket.setPosition(20, 10);
    }

    public Rocket getRocket() {
        return rocket;
    }

    public RocketCamera getRocketCamera() {
        return rocketCamera;
    }
}
