package ass1.solarSystemGame.objects.rocket;

import ass1.GameObject;
import ass1.PolygonalGameObject;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame.objects.rocket
 * Project: COMP3421_Assignment1
 */
public class Body extends PolygonalGameObject {

    private static final double[] ROCKET_BODY_COLOR = new double[]{0.9, 0.9, 0.9, 1};

    private static final double[] ROCKET_EDGE_COLOR = new double[]{0.7, 0.7, 0.7, 1};

    private static final double[] POLYGON_POINTS = new double[]
            {0,  -1,
             3,   0,
             0,   1};

    public Body(GameObject parent) {
        super(parent, POLYGON_POINTS, ROCKET_BODY_COLOR, ROCKET_EDGE_COLOR);
    }

}
