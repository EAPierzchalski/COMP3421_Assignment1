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
    public static final double POWER = 3;
    public static double[] force(GameObject gameObject, CelestialObject celestialObject) {
        double[] force = new double[2];
        double[] gameObjectPosition = gameObject.getGlobalPosition();
        double[] celestialObjectPosition = celestialObject.getGlobalPosition();
        double[] relativePosition = new double[]
                {celestialObjectPosition[0] - gameObjectPosition[0],
                 celestialObjectPosition[1] - gameObjectPosition[1]};
        double distance = Math.hypot(relativePosition[0], relativePosition[1]);
        if (distance < celestialObject.getRadius()) {
            double forceAtEdge = GRAVITATIONAL_CONSTANT *
                    celestialObject.getMass() /
                    Math.pow(celestialObject.getRadius(), POWER);
            force[0] = relativePosition[0] * distance * forceAtEdge / celestialObject.getRadius();
            force[1] = relativePosition[1] * distance * forceAtEdge / celestialObject.getRadius();
        } else {
            //include an extra factor to normalize the relative position vector
            double factor = GRAVITATIONAL_CONSTANT *
                    celestialObject.getMass() /
                    Math.pow(distance, POWER);
            force[0] = relativePosition[0] * factor;
            force[1] = relativePosition[1] * factor;
        }
        return force;
    }

    public static double[] polygonPoints(int numSides, double radius) {
        double[] coordinates = new double[numSides * 2];
        for (int i = 0; i < numSides; i++) {
            int xIndex = 2 * i;
            int yIndex = xIndex + 1;
            double circleFraction = (double) i / (double) numSides;
            double xCoordinate = radius * Math.cos(2 * Math.PI * circleFraction);
            double yCoordinate = radius * Math.sin(2 * Math.PI * circleFraction);
            coordinates[xIndex] = xCoordinate;
            coordinates[yIndex] = yCoordinate;
        }
        return coordinates;
    }
}
