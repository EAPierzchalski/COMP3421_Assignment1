package ass1.solarSystemGame.engine.keybindings;

import ass1.solarSystemGame.engine.SolarSystemGameEngine;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractKeyHandler extends AbstractAction {
    protected SolarSystemGameEngine theEngine;

    public AbstractKeyHandler(SolarSystemGameEngine theEngine) {
        this.theEngine = theEngine;
    }

    public abstract String getActionKeyString();

    public abstract KeyStroke getKeystroke();

    public void addTo(JComponent jComponent) {
        jComponent.getInputMap().put(this.getKeystroke(), this.getActionKeyString());
        jComponent.getActionMap().put(this.getActionKeyString(), this);
    }
}
