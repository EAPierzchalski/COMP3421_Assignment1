package ass1;

import org.junit.Assert;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The GameEngine is the GLEventListener for our game.
 * 
 * Every object in the scene tree is updated on each display call.
 * Then the scene tree is rendered.
 *
 * You shouldn't need to modify this class.
 *
 * @author malcolmr
 */
public class GameEngine implements GLEventListener {

    private static double COLLISION_EPSILON = 1e-10;

    private Camera myCamera;
    private long myTime;

    /**
     * Construct a new game engine.
     *
     * @param camera The camera that is used in the scene.
     */
    public GameEngine(Camera camera) {
        myCamera = camera;
    }
    
    /**
     * @see javax.media.opengl.GLEventListener#init(javax.media.opengl.GLAutoDrawable)
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        // initialise myTime
        myTime = System.currentTimeMillis();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // ignore
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
            int height) {
        
        // tell the camera and the mouse that the screen has reshaped
        GL2 gl = drawable.getGL().getGL2();

        myCamera.reshape(gl, x, y, width, height);
        
        // this has to happen after myCamera.reshape() to use the new projection
        Mouse.theMouse.reshape(gl);
    }


    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // set the view matrix based on the camera position
        myCamera.setView(gl); 
        
        // update the mouse position
        Mouse.theMouse.update(gl);
        
        // update the objects
        update();

        // draw the scene tree
        GameObject.ROOT.draw(gl);
    }

    private void update() {
        
        // compute the time since the last frame
        long time = System.currentTimeMillis();
        double dt = (time - myTime) / 1000.0;
        myTime = time;
        
        // take a copy of the ALL_OBJECTS list to avoid errors 
        // if new objects are created in the update
        List<GameObject> objects = new ArrayList<GameObject>(GameObject.ALL_OBJECTS);
        
        // update all objects
        for (GameObject g : objects) {
            g.update(dt);
        }        
    }

    public List<GameObject> collision(double[] p) {
        List<GameObject> collisions = new LinkedList<GameObject>();
        for (GameObject gameObject : GameObject.ALL_OBJECTS) {
            if (gameObject instanceof PolygonalGameObject) {
                PolygonalGameObject polygonalGameObject = (PolygonalGameObject) gameObject;
                double[] localPolygonPoints = polygonalGameObject.getPoints();
                double[] globalPolygonPoints = new double[localPolygonPoints.length];
                double[] globalPosition = polygonalGameObject.getGlobalPosition();
                double globalRotation = polygonalGameObject.getGlobalRotation();
                double globalScale = polygonalGameObject.getGlobalScale();
                double[][] transformationMatrix = MathUtil.TRSMatrix(globalPosition, globalRotation, globalScale);
                for (int pointIndex = 0; pointIndex < localPolygonPoints.length/2; pointIndex++) {
                    double[] localVertex = new double[]{localPolygonPoints[2 * pointIndex],
                            localPolygonPoints[2 * pointIndex + 1],
                            1};
                    double[] globalVertex = MathUtil.multiply(transformationMatrix, localVertex);
                    globalPolygonPoints[2 * pointIndex] = globalVertex[0];
                    globalPolygonPoints[2 * pointIndex + 1] = globalVertex[1];
                }
                if (collides(p, globalPolygonPoints)) {
                    collisions.add(polygonalGameObject);
                }
            }
        }
        return collisions;
    }

    private static boolean collides(double[] testPoint, double[] polygonPoints) {
        int intersectionCount = 0;
        for (int pointIndex = 0; pointIndex < polygonPoints.length/2; pointIndex++) {
            int startIndex = 2 * pointIndex;
            double xStart = polygonPoints[startIndex];
            double yStart = polygonPoints[startIndex + 1];
            int endIndex = (2 * (pointIndex + 1)) % polygonPoints.length;
            double xEnd = polygonPoints[endIndex];
            double yEnd = polygonPoints[endIndex + 1];
            int sideOfLine = whatSideOfLine(testPoint, xStart, yStart, xEnd, yEnd);
            //System.out.println(String.format("x: %f, y: %f", testPoint[0], testPoint[1]));
            //System.out.println(String.format("xStart: %f, yStart: %f", xStart, yStart));
            //System.out.println(String.format("xEnd: %f, yEnd: %f", xEnd, yEnd));
            //System.out.println(String.format("Side of line: %d", whatSideOfLine(testPoint, xStart, yStart, xEnd, yEnd)));
            //System.out.println(String.format("In x bounds: %b", inRange(testPoint[0], xStart, xEnd, false)));
            //System.out.println(String.format("In y bounds: %b", inRange(testPoint[1], yStart, yEnd, true)));
            if (sideOfLine == 0) {
                if (inRange(testPoint[0], xStart, xEnd, false) &&
                        inRange(testPoint[1], yStart, yEnd, false)) {
                    return true;
                }
            } else if (sideOfLine == -1) {
                if (inRange(testPoint[1], yStart, yEnd, true)) {
                    intersectionCount += 1;
                }
            }
        }
        return intersectionCount % 2 == 1;
    }

    private static boolean inRange(double testPointX, double x1, double x2, boolean strongUpperBound) {
        return Math.min(x1, x2) <= testPointX &&
                (strongUpperBound ? testPointX < Math.max(x1, x2) : testPointX <= Math.max(x1, x2));
    }

    /***
     *
     * @param testPoint array containing the points {x, y}
     * @param x1    x-coordinate of point p1 on line
     * @param y1    y-coordinate of point p1 on line
     * @param x2  x-coordinate of point p2 on line
     * @param y2  y-coordinate of point p2 on line
     * @return -1 if the point is to the left of the line, 0 if the point lies on the line, 1 if the point is to the right of the line.
     */
    private static int whatSideOfLine(double[] testPoint, double x1, double y1, double x2, double y2) {
        double dx1 = testPoint[0] - Math.max(x1, x2);
        double dx2 = testPoint[0] - Math.min(x1, x2);
        double dy1 = testPoint[1] - Math.max(y1, y2);
        double dy2 = testPoint[1] - Math.min(y1, y2);
        double d12 = dx1 * dy2;
        double d21 = dy1 * dx2;
        return  d12 < d21 && Math.abs(d12 - d21) > COLLISION_EPSILON ? -1 :
                d12 > d21 && Math.abs(d12 - d21) > COLLISION_EPSILON ?  1 : 0;
    }

}
