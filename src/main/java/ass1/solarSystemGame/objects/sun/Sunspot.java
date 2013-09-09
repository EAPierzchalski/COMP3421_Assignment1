package ass1.solarSystemGame.objects.sun;

import ass1.GameObject;
import ass1.PolygonalGameObject;

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

    /**
     * Create a polygonal game object and add it to the scene tree
     * <p/>
     * The polygon is specified as a list of doubles in the form:
     * <p/>
     * [ x0, y0, x1, y1, x2, y2, ... ]
     * <p/>
     * The line and fill colours can possibly be null, in which case that part of the object
     * should not be drawn.
     *
     * @param parent     The parent in the scene tree
     * @param points     A list of points defining the polygon
     * @param fillColour The fill colour in [r, g, b, a] form
     * @param lineColour The outlien colour in [r, g, b, a] form
     */
    public Sunspot(Sun parent, double[] points, double[] fillColour, double[] lineColour) {
        super(parent, points, fillColour, lineColour);
    }
}
