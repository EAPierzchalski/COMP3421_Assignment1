package ass1.solarSystemGame.objects.sun;

import ass1.GameObject;
import ass1.PolygonalGameObject;
import ass1.solarSystemGame.objects.CelestialObject;
import ass1.solarSystemGame.objects.CelestialObjectUtil;

import java.util.Random;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame.objects
 * Project: COMP3421_Assignment1
 */
public class Sun extends PolygonalGameObject implements CelestialObject {

    private static final double[] SUN_YELLOW = new double[]{0.9, 0.82, 0, 1};

    private static final double[] SUN_ORANGE = new double[]{0.9, 0.45, 0, 1};

    private static final double RADIUS = 10;

    private static final double MASS = 100;

    private static final int NUM_SIDES = 75;

    private static final int NUM_SUNSPOTS = 30;

    private double rotationSpeed = 5;

    private GameObject pivot;

    private static final double[] POLYGON_POINTS = CelestialObjectUtil.polygonPoints(NUM_SIDES, RADIUS);

    public Sun(GameObject gameObject) {
        super(ROOT, POLYGON_POINTS, SUN_YELLOW, SUN_ORANGE);
        this.pivot = new GameObject(gameObject);
        this.setParent(pivot);
        Random rand = new Random();
        for (int i = 0; i < NUM_SUNSPOTS; i++) {
            double scale = Math.pow(rand.nextDouble(), 2);
            double angle = 360 * rand.nextDouble();
            double radius = -1;
            while (!(radius > 0 && radius < RADIUS - 2)) {
                radius = rand.nextGaussian() * 2 * RADIUS;
            }
            //System.out.println(angle);
            Sunspot sunspot = new Sunspot(this, angle, radius, scale);
        }
    }

    public double getRadius() {
        return RADIUS;
    }

    public GameObject getPivot() {
        return pivot;
    }

    public double getMass() {
        return MASS;
    }

    @Override
    public void update(double dt) {
        super.update(dt);    //To change body of overridden methods use File | Settings | File Templates.
        this.rotate(rotationSpeed * dt);
    }
}
