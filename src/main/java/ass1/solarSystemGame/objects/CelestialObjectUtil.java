package ass1.solarSystemGame.objects;

import ass1.GameObject;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame.objects
 * Project: COMP3421_Assignment1
 */
public abstract class CelestialObjectUtil {
    public static final double GRAVITATIONAL_CONSTANT = 10;
    public static double[] force(GameObject gameObject, CelestialObject celestialObject) {
        double[] force = new double[2];
        double[] gameObjectPosition = gameObject.getPosition();
        double[] celestialObjectPosition = celestialObject.getPosition();
        double[] relativePosition = new double[]
                {celestialObjectPosition[0] - gameObjectPosition[0],
                 celestialObjectPosition[1] - gameObjectPosition[1]};
        if (Math.hypot(relativePosition[0], relativePosition[1]) < celestialObject.getRadius()) {
            force[0] = 0;
            force[1] = 0;
        } else {
            //include an extra factor to normalize the relative position vector
            double radialFactor = Math.pow(Math.hypot(relativePosition[0], relativePosition[1]), 3);
            double factor = GRAVITATIONAL_CONSTANT * celestialObject.getMass() / radialFactor;
            force[0] = relativePosition[0] * factor;
            force[1] = relativePosition[1] * factor;
        }
        return force;
    }
}
