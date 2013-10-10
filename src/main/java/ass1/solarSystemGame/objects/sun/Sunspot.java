package ass1.solarSystemGame.objects.sun;

import ass1.GameObject;
import ass1.PolygonalGameObject;
import ass1.solarSystemGame.objects.CelestialObjectUtil;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sunspot extends PolygonalGameObject {
    private static double[] SUNSPOT_BROWN = new double[]{0.75, 0.3, 0, 1};
    private static double[] SUNSPOT_DARK_BROWN = new double[]{0.5, 0.1, 0, 1};
    private static double AVERAGE_SPOT_ROTATION = 10;

    private double rotationSpeed = 0;

    private static double[] pointCoordinates = CelestialObjectUtil.polygonPoints(10, 0.5);

    public Sunspot(Sun parent, double angle, double radius, double scale) {
        super(parent, pointCoordinates, SUNSPOT_BROWN, SUNSPOT_DARK_BROWN);
        Random rand = new Random();
        double myRotation = rand.nextDouble() * 360;
        this.rotate(myRotation);
        this.scale(scale);
        double radians = Math.toRadians(angle);
        double dx = Math.cos(radians) * radius;
        double dy = Math.sin(radians) * radius;
        this.translate(dx, dy);
        this.rotationSpeed = rand.nextDouble() * 2 * AVERAGE_SPOT_ROTATION;
    }

    @Override
    public void update(double dt) {
        super.update(dt);    //To change body of overridden methods use File | Settings | File Templates.
        this.rotate(rotationSpeed * dt);
    }
}
