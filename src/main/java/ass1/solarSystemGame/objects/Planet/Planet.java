package ass1.solarSystemGame.objects.Planet;

import ass1.GameObject;
import ass1.PolygonalGameObject;
import ass1.solarSystemGame.objects.CelestialObject;
import ass1.solarSystemGame.objects.CelestialObjectUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Planet extends PolygonalGameObject implements CelestialObject {

    private static final double RADIUS = 2;

    private static final double MASS = 10;

    private static final int NUM_SIDES = 10;

    private static final double[] POLYGON_POINTS = CelestialObjectUtil.polygonPoints(NUM_SIDES, RADIUS);

    private static final double[] PLANET_BLUE = new double[]{0.1, 0.6, 0.9, 1};

    private static final double[] PLANET_GREEN = new double[]{0.1, 0.9, 0.6, 1};

    private GameObject pivot;

    private double angularVelocity;

    public Planet(GameObject orbitAround, double orbitRadius, double orbitAngularVelocity, double planetAngularVelocity) {
        super(ROOT, POLYGON_POINTS, PLANET_BLUE, PLANET_GREEN);
        this.angularVelocity = planetAngularVelocity;
        PlanetOrbit planetOrbit = new PlanetOrbit(orbitAround, orbitAngularVelocity);
        this.pivot = new GameObject(planetOrbit);
        this.setParent(pivot);
        this.pivot.setPosition(orbitRadius, 0);
    }

    public GameObject getPivot() {
        return pivot;
    }

    @Override
    public double getMass() {
        return MASS;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getRadius() {
        return RADIUS;
    }

    @Override
    public void update(double dt) {
        super.update(dt);    //To change body of overridden methods use File | Settings | File Templates.
        this.rotate(angularVelocity * dt);
    }

    public class PlanetOrbit extends GameObject {
        private double orbitAngularVelocity;
        /**
         * Public constructor for creating GameObjects, connected to a parent (possibly the ROOT).
         * <p/>
         * New objects are created at the same location, orientation and scale as the parent.
         *
         * @param parent
         */
        public PlanetOrbit(GameObject parent, double orbitAngularVelocity) {
            super(parent);
            this.orbitAngularVelocity = orbitAngularVelocity;
        }

        @Override
        public void update(double dt) {
            super.update(dt);    //To change body of overridden methods use File | Settings | File Templates.
            this.rotate(orbitAngularVelocity * dt);
        }
    }
}
