package ass1.solarSystemGame.objects.sun;

import ass1.PolygonalGameObject;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame.objects
 * Project: COMP3421_Assignment1
 */
public class Sun extends PolygonalGameObject {

    public static final Sun theSun = new Sun();

    private static final double[] SUN_YELLOW = new double[]{0.9, 0.82, 0, 1};

    private static final double[] SUN_ORANGE = new double[]{0.9, 0.45, 0, 1};

    private static final int NUM_SIDES = 100;

    private static final double[] POLYGON_POINTS = polygonPoints(NUM_SIDES);

    private Sun() {
        super(ROOT, POLYGON_POINTS, SUN_YELLOW, SUN_ORANGE);
    }

    private static double[] polygonPoints(int numSides) {
        double[] coordinates = new double[numSides * 2];
        for (int i = 0; i < numSides; i++) {
            int xIndex = 2 * i;
            int yIndex = xIndex + 1;
            double circleFraction = (double) i / (double) numSides;
            double xCoordinate = Math.cos(2 * Math.PI * circleFraction);
            double yCoordinate = Math.cos(2 * Math.PI * circleFraction);
            coordinates[xIndex] = xCoordinate;
            coordinates[yIndex] = yCoordinate;
        }
        return coordinates;
    }
}
