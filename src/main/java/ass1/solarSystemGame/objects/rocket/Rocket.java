package ass1.solarSystemGame.objects.rocket;

import ass1.GameObject;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame.objects
 * Project: COMP3421_Assignment1
 */
public class Rocket extends GameObject {

    public Body body;
    public Flame flame;

    /**
     * Public constructor for creating GameObjects, connected to a parent (possibly the ROOT).
     * <p/>
     * New objects are created at the same location, orientation and scale as the parent.
     *
     * @param parent
     */
    public Rocket(GameObject parent) {
        super(parent);
        body = new Body(this);
        flame = new Flame(this);
    }
}
