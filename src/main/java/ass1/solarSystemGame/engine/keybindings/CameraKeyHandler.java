package ass1.solarSystemGame.engine.keybindings;

import ass1.solarSystemGame.engine.SolarSystemGameEngine;
import ass1.solarSystemGame.objects.rocketcamera.RocketCamera;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class CameraKeyHandler extends AbstractKeyHandler {
    private static KeyStroke KEYSTROKE = KeyStroke.getKeyStroke("C");

    public CameraKeyHandler(SolarSystemGameEngine theEngine) {
        super(theEngine);
    }

    @Override
    public String getActionKeyString() {
        return "camera key handler";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public KeyStroke getKeystroke() {
        return KEYSTROKE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RocketCamera rocketCamera = this.theEngine.getRocketCamera();
        rocketCamera.setFollowingTargetRotation(!rocketCamera.isFollowingTargetRotation());
    }
}
