package ass1.solarSystemGame.engine.keybindings;

import ass1.solarSystemGame.engine.SolarSystemGameEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class FireKeyHandler extends AbstractKeyHandler {
    private static KeyStroke KEYSTROKE = KeyStroke.getKeyStroke("F");

    public FireKeyHandler(SolarSystemGameEngine theEngine) {
        super(theEngine);
    }

    @Override
    public String getActionKeyString() {
        return "fire key handler";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public KeyStroke getKeystroke() {
        return KEYSTROKE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        theEngine.getRocket().pewpew();
    }
}
