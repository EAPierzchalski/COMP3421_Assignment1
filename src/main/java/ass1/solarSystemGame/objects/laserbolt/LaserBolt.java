package ass1.solarSystemGame.objects.laserbolt;

import ass1.GameObject;
import ass1.MathUtil;
import ass1.PolygonalGameObject;
import ass1.solarSystemGame.objects.CelestialObject;
import ass1.solarSystemGame.objects.CelestialObjectUtil;
import ass1.solarSystemGame.objects.rocket.Rocket;

/**
 * Created with IntelliJ IDEA.
 * User: Edward
 * Date: 9/09/13
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class LaserBolt extends PolygonalGameObject {

    private static final int MAX_NUM_BOLTS = 50;
    private static int numBolts;

    private static double[] POLYGON_POINTS = new double[]
            {0, -0.1,
             0.5, -0.1,
             0.5, 1.0,
             0, 1.0};
    private static double[] RED = new double[]{1, 0, 0, 1};
    private static double[] DARK_RED = new double[]{1, 0.3, 0.3, 1};

    private static double BULLET_SPEED = 30;

    private double[] velocity;

    private double timeToLive = 1;

    public LaserBolt(Rocket source) {
        super(ROOT, POLYGON_POINTS, RED, DARK_RED);
        double[] position = source.getGlobalPosition();
        double[] parentVelocity = source.getVelocity();
        //System.out.println(MathUtil.printVector(parentVelocity));
        double angle = source.getGlobalRotation();
        double radians = Math.toRadians(angle);
        this.setPosition(position[0], position[1]);
        this.velocity = new double[]
                {parentVelocity[0] + Math.cos(radians) * BULLET_SPEED,
                 parentVelocity[1] + Math.sin(radians) * BULLET_SPEED};
        this.setRotation(angle);
        numBolts += 1;
        if (numBolts >= MAX_NUM_BOLTS) {
            this.destroy();
        }
    }

    //Java is proof that there is no loving god, just a spiteful one
    //scala is therefore proof of jesus?


    public void accelerate(double dvx, double dvy) {
        this.velocity[0] += dvx;
        this.velocity[1] += dvy;
    }

    @Override
    public void update(double dt) {
        super.update(dt);    //To change body of overridden methods use File | Settings | File Templates.
        //pew pew pew
        //^wrong class motherfucker
        timeToLive -= dt;
        if (timeToLive <= 0) {
            this.destroy();
        } else {
            for (GameObject gameObject : GameObject.ALL_OBJECTS) {
                if (gameObject instanceof CelestialObject) {
                    double[] force = CelestialObjectUtil.force(this, (CelestialObject) gameObject);
                    //System.out.println(MathUtil.printVector(force));
                    this.accelerate(force[0] * dt, force[1] * dt);
                }
            }
            double angle = Math.toDegrees(Math.atan2(-this.velocity[1], this.velocity[0]));
            this.setRotation(angle);
            this.move(dt);
        }
    }

    @Override
    public void destroy() {
        super.destroy();    //To change body of overridden methods use File | Settings | File Templates.
        numBolts -= 1;
    }

    private void move(double dt) {
        double[] position = this.getPosition();
        position[0] += this.velocity[0] * dt;
        position[1] += this.velocity[1] * dt;
        this.setPosition(position[0], position[1]);
    }
}
