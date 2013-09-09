package ass1.solarSystemGame.engine.keybindings;

import ass1.solarSystemGame.engine.SolarSystemGameEngine;
import ass1.solarSystemGame.objects.rocket.Rocket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class UpKeyHandler extends AbstractKeyHandler {
    private static KeyStroke KEYSTROKE = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);

    public UpKeyHandler(SolarSystemGameEngine theEngine) {
        super(theEngine);
    }

    @Override
    public String getActionKeyString() {
        return "up key handler";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public KeyStroke getKeystroke() {
        return KEYSTROKE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rocket rocket = this.theEngine.getRocket();
        rocket.addBurnTime(Rocket.MAX_BURN_TIME);

    }
}
