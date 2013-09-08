package ass1.solarSystemGame.objects.rocket;

import ass1.GameObject;
import ass1.MathUtil;
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
            {-1,    0,
             -2d/3, -0.5,
             2d/3,  -0.5,
             1,     0};

    private double fireTimeLeft = 0;

    private double defaultFireTime = 1;

    public Flame(GameObject gameObject) {
        super(gameObject, POLYGON_POINTS, FIRE_BODY_COLOR_OFF, FIRE_EDGE_COLOR);
    }

    public void turnOn() {
        this.setFillColour(FIRE_BODY_COLOR_ON);
        this.fireTimeLeft = defaultFireTime;
    }

    public void turnOff() {
        this.setFillColour(FIRE_BODY_COLOR_OFF);
    }

    @Override
    public void update(double dt) {
        super.update(dt);    //To change body of overridden methods use File | Settings | File Templates.
        this.fireTimeLeft = MathUtil.clamp(this.fireTimeLeft - dt, 0, 10);
        if (this.fireTimeLeft == 0) {
            this.turnOff();
        }
    }
}
