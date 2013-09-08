package ass1;

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
                double[] globalPolygonCoordinates = polygonalGameObject.getGlobalPoints();
                if (collides(p, globalPolygonCoordinates)) {
                    collisions.add(polygonalGameObject);
                }
            }
        }
        return collisions;
    }

    private static boolean collides(double[] testPoint, double[] polygonCoordinates) {
        int intersectionCount = 0;
        boolean onAnEdge = false;
        //System.out.println(String.format("\ntestPoint:  {%f, %f}", testPoint[0], testPoint[1]));
        for (int pointIndex = 0; pointIndex < polygonCoordinates.length/2 && !onAnEdge; pointIndex++) {
            int startIndex = 2 * pointIndex;
            double xStart = polygonCoordinates[startIndex];
            double yStart = polygonCoordinates[startIndex + 1];
            int endIndex = (2 * (pointIndex + 1)) % polygonCoordinates.length;
            double xEnd = polygonCoordinates[endIndex];
            double yEnd = polygonCoordinates[endIndex + 1];
            int sideOfLine = whatSideOfLine(testPoint, xStart, yStart, xEnd, yEnd);
            //double distanceFromLine = distanceFromLine(testPoint, xStart, yStart, xEnd, yEnd);
            //System.out.println(String.format("edge start: {%f, %f}", xStart, yStart));
            //System.out.println(String.format("edge end:   {%f, %f}", xEnd, yEnd));
            //System.out.println(String.format("Side of line: %d", sideOfLine));
            //System.out.println(String.format("distance from line: %f", distanceFromLine));
            if (sideOfLine == 0) {
                if (inRange(testPoint[0], xStart, xEnd, false) &&
                        inRange(testPoint[1], yStart, yEnd, false)) {
                    onAnEdge = true;
                }
            } else if (sideOfLine == -1) {
                if (inRange(testPoint[1], yStart, yEnd, true)) {
                    intersectionCount += 1;
                    //System.out.println("  Collision!");
                }
            }
        }
        boolean collides = false;
        if (onAnEdge || intersectionCount % 2 == 1) {
            collides = true;
        }
        return collides;
    }

    private static boolean inRange(double testVal, double boundVal1, double boundVal2, boolean strongUpperBound) {
        double upperBound = Math.max(boundVal1, boundVal2);
        double lowerBound = Math.min(boundVal1, boundVal2);
        boolean aboveLowerBound = lowerBound - COLLISION_EPSILON <= testVal;
        boolean belowUpperBound;
        if (strongUpperBound) {
            belowUpperBound = testVal < upperBound;
        } else {
            belowUpperBound = testVal <= upperBound + COLLISION_EPSILON;
        }
        return aboveLowerBound && belowUpperBound;
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
        double[] lowPoint;
        double[] highPoint;
        if (y1 < y2) {
            lowPoint = new double[]{x1, y1};
            highPoint = new double[]{x2, y2};
        } else {
            lowPoint = new double[]{x2, y2};
            highPoint = new double[]{x1, y1};
        }
        double[] lineVector = new double[]{highPoint[0] - lowPoint[0], highPoint[1] - lowPoint[1]};
        double[] perpendicular = new double[]{lowPoint[1] - testPoint[1], testPoint[0] - lowPoint[0]};
        double dot = lineVector[0] * perpendicular[0] + lineVector[1] * perpendicular[1];
        int sideOfLine;
        if (Math.abs(dot) < COLLISION_EPSILON) {
            sideOfLine = 0;
        } else if (dot > 0) {
            sideOfLine = 1;
        } else {
            sideOfLine = -1;
        }
        return sideOfLine;
    }

}
