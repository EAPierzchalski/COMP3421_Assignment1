package ass1.solarSystemGame.engine.keybindings;

import ass1.solarSystemGame.engine.SolarSystemGameEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZoomInKeyHandler extends AbstractKeyHandler {
    private static KeyStroke KEYSTROKE = KeyStroke.getKeyStroke("Z");

    public ZoomInKeyHandler(SolarSystemGameEngine theEngine) {
        super(theEngine);
    }

    @Override
    public String getActionKeyString() {
        return "zoom in key handler";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public KeyStroke getKeystroke() {
        return KEYSTROKE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        theEngine.getRocketCamera().zoomIn();
    }
}
