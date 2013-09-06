package ass1.tests;

import ass1.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * User: Pierzchalski
 * Date: 04/09/13
 * Package: ass1.tests
 * Project: COMP3421_Assignment1
 */
public class CollisionTest {
    private static final double[] SQUARE_VERTICES = new double[]
            {0, 0,
             1, 0,
             1, 1,
             0, 1};

    private static final double[] TRIANGLE_VERTICES = new double[]
            {0, 0,
             1, 0,
             0.5, Math.sqrt(3)/2.0};

    private static final double[] RED = new double[] {1f, 0f, 0f, 1f};

    @Test
    public void collideSingleShape() {
        Camera camera = new Camera();
        GameEngine gameEngine = new GameEngine(camera);

        PolygonalGameObject square = new PolygonalGameObject(GameObject.ROOT, SQUARE_VERTICES, RED, RED);

        expectCollisionWith(gameEngine, .5, .5, square);
        expectCollisionWith(gameEngine, 0, 0, square);
        expectCollisionWith(gameEngine, 0, .5, square);
        expectCollisionWith(gameEngine, .5, 0, square);

        expectMissWith(gameEngine, -1, .5, square);

        square.translate(1, 1);

        expectMissWith(gameEngine, -1, 1.5, square);

        expectCollisionWith(gameEngine, 1, 1, square);
        expectCollisionWith(gameEngine, 1.5, 1.5, square);
        expectCollisionWith(gameEngine, 1.5, 2, square);

        expectMissWith(gameEngine, 2.5, 1.5, square);

        square.rotate(45);
        square.scale(1d/Math.sqrt(2));

        expectCollisionWith(gameEngine, 1, 1, square);
        expectCollisionWith(gameEngine, 1, 1.5, square);
        expectCollisionWith(gameEngine, 1.25, 1.25, square);
        expectCollisionWith(gameEngine, 1.25, 1.75, square);
        expectCollisionWith(gameEngine, 0.75, 1.75, square);
        expectCollisionWith(gameEngine, 0.75, 1.25, square);

        expectMissWith(gameEngine, 0, 1.2, square);
        expectMissWith(gameEngine, 2, 1.2, square);
    }

    @Test
    public void parentTransformations() {
        Camera camera = new Camera();
        GameEngine gameEngine = new GameEngine(camera);

        GameObject pivot = new GameObject(GameObject.ROOT);
        PolygonalGameObject square = new PolygonalGameObject(pivot, SQUARE_VERTICES, RED, RED);
        pivot.translate(1, 1);
        square.translate(0, 2);

        expectCollisionWith(gameEngine, 1, 3, square);
        expectCollisionWith(gameEngine, 2, 4, square);
        expectCollisionWith(gameEngine, 1.5, 3.5, square);
        expectCollisionWith(gameEngine, 1.5, 3, square);
        expectCollisionWith(gameEngine, 2, 3.5, square);

        expectMissWith(gameEngine, 2.5, 3.5, square);

        pivot.rotate(90);
        pivot.scale(2);

        expectCollisionWith(gameEngine, -5, 2, square);
        expectCollisionWith(gameEngine, -4, 2, square);
        expectCollisionWith(gameEngine, -4, 3, square);

        square.translate(-0.5, 0);
        square.rotate(-45);
        square.scale(1d/Math.sqrt(2));

        Assert.assertEquals(square.getGlobalPosition()[0], -3, 1e-10);
        Assert.assertEquals(square.getGlobalPosition()[1], 0, 1e-10);
        Assert.assertEquals(square.getGlobalRotation(), 45, 1e-10);
        Assert.assertEquals(square.getGlobalScale(), Math.sqrt(2), 1e-10);

        //System.out.println(coordinateList2String(square.getGlobalPoints()));

        expectCollisionWith(gameEngine, -3, 0.5, square);
        expectCollisionWith(gameEngine, -3, 2, square);
        expectCollisionWith(gameEngine, -2, 1, square);
        expectCollisionWith(gameEngine, -2.5, 0.5, square);
        expectCollisionWith(gameEngine, -3.5, 0.5, square);
        expectCollisionWith(gameEngine, -3, 0, square);
    }

    @Test
    public void multipleShapes() {
        Camera camera = new Camera();
        GameEngine gameEngine = new GameEngine(camera);

        GameObject pivot = new GameObject(GameObject.ROOT);
        PolygonalGameObject square = new PolygonalGameObject(pivot, SQUARE_VERTICES, RED, RED);
        PolygonalGameObject triangle = new PolygonalGameObject(square, TRIANGLE_VERTICES, RED, RED);

        expectCollisionWith(gameEngine, 0, 0, square);
        expectCollisionWith(gameEngine, 0, 0, triangle);
        expectCollisionWith(gameEngine, 0.5, 0, square);
        expectCollisionWith(gameEngine, 0.5, 0, triangle);
        expectCollisionWith(gameEngine, 0.5, 0.5, square);
        expectCollisionWith(gameEngine, 0.5, 0.5, triangle);
        expectCollisionWith(gameEngine, 0.25, 0.75, square);
        expectMissWith(gameEngine, 0.25, 0.75, triangle);

        triangle.rotate(-60);

        expectCollisionWith(gameEngine, 0.5, 0, square);
        expectCollisionWith(gameEngine, 0.5, 0, triangle);
        expectCollisionWith(gameEngine, 0.5, -0.5, triangle);
        expectMissWith(gameEngine, 0.5, -0.5, square);
    }

    private static void expectCollisionWith(GameEngine gameEngine,
                                            double testPointX,
                                            double testPointY,
                                            PolygonalGameObject polygonalGameObject) {
        double[] testPoint = new double[2];
        testPoint[0] = testPointX;
        testPoint[1] = testPointY;
        List<GameObject> collisions = gameEngine.collision(testPoint);
        Assert.assertTrue(!collisions.isEmpty());
        Assert.assertTrue(collisions.contains(polygonalGameObject));
    }

    private static void expectMissWith(GameEngine gameEngine,
                                       double testPointX,
                                       double testPointY,
                                       PolygonalGameObject polygonalGameObject) {
        double[] testPoint = new double[2];
        testPoint[0] = testPointX;
        testPoint[1] = testPointY;
        List<GameObject> collisions = gameEngine.collision(testPoint);
        Assert.assertTrue(!collisions.contains(polygonalGameObject));
    }

    private static String coordinateList2String(double[] coordinates) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int pointIndex = 0; pointIndex < coordinates.length/2; pointIndex++) {
            int xIndex = 2 * pointIndex;
            int yIndex = xIndex + 1;
            double xCoordinate = coordinates[xIndex];
            double yCoordinate = coordinates[yIndex];
            stringBuilder.append(String.format("x: %f, y: %f\n", xCoordinate, yCoordinate));
        }
        return stringBuilder.toString();
    }
}
