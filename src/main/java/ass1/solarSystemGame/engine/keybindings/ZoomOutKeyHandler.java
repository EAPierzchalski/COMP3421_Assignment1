package ass1.solarSystemGame.engine.keybindings;

import ass1.solarSystemGame.engine.SolarSystemGameEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZoomOutKeyHandler extends AbstractKeyHandler {
    private static KeyStroke KEYSTROKE = KeyStroke.getKeyStroke("X");

    public ZoomOutKeyHandler(SolarSystemGameEngine theEngine) {
        super(theEngine);
    }

    @Override
    public String getActionKeyString() {
        return "zoom out key handler";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public KeyStroke getKeystroke() {
        return KEYSTROKE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        theEngine.getRocketCamera().zoomOut();
    }
}
