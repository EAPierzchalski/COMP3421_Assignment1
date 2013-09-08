package ass1.solarSystemGame.objects.rocket;

import ass1.GameObject;
import ass1.PolygonalGameObject;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame.objects.rocket
 * Project: COMP3421_Assignment1
 */
public class Flame extends PolygonalGameObject {

    private static final double[] FIRE_BODY_COLOR_OFF = new double[]{0.9, 0.2, 0, 1};

    private static final double[] FIRE_BODY_COLOR_ON = new double[]{0.8, 0.6, 0, 1};

    private static final double[] FIRE_EDGE_COLOR = new double[]{0.9, 0.7, 0, 1};

    private static final double[] POLYGON_POINTS = new double[]
            {0,    -1,
             -0.5, -2d/3,
             -0.5, 2d/3,
             0,    1};

    public Flame(GameObject gameObject) {
        super(gameObject, POLYGON_POINTS, FIRE_BODY_COLOR_OFF, FIRE_EDGE_COLOR);
    }

    public void turnOn() {
        this.setFillColour(FIRE_BODY_COLOR_ON);
    }

    public void turnOff() {
        this.setFillColour(FIRE_BODY_COLOR_OFF);
    }

}
