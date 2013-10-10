package ass1.solarSystemGame.objects.rocket;

import ass1.GameObject;
import ass1.MathUtil;
import ass1.solarSystemGame.objects.CelestialObject;
import ass1.solarSystemGame.objects.CelestialObjectUtil;
import ass1.solarSystemGame.objects.laserbolt.LaserBolt;

import javax.media.opengl.GL2;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame.objects
 * Project: COMP3421_Assignment1
 */
public class Rocket extends GameObject {

    private Body body;
    private Flame flame;
    private double[] velocity;
    private double angularVelocity = 0;
    public static double MAX_ANGULAR_VELOCITY = 180;
    public static int NUM_ANGULAR_VELOCITY_STEPS = 4;
    public static double ANGULAR_VELOCITY_STEP = MAX_ANGULAR_VELOCITY / NUM_ANGULAR_VELOCITY_STEPS;
    public static double MAX_BURN_TIME = 0.5;
    public static double BURN_POWER = 5;
    private double burnTime = 0;

    /**
     * Public constructor for creating GameObjects, connected to a parent (possibly the ROOT).
     * <p/>
     * New objects are created at the same location, orientation and scale as the parent.
     *
     * @param parent
     */
    public Rocket(GameObject parent) {
        super(parent);
        body = new Body(this);
        flame = new Flame(this);
        velocity = new double[]{0, 0};
    }

    public Body getBody() {
        return body;
    }

    public Flame getFlame() {
        return flame;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public double[] getVelocity() {
        double[] v = new double[2];
        v[0] = velocity[0];
        v[1] = velocity[1];
        return v;
    }

    public void accelerateAngularVelocity(double dJ) {
        this.angularVelocity = MathUtil.clamp(this.angularVelocity + dJ, -MAX_ANGULAR_VELOCITY, MAX_ANGULAR_VELOCITY);
    }


    public void accelerate(double dvx, double dvy) {
        this.velocity[0] += dvx;
        this.velocity[1] += dvy;
    }

    public void addBurnTime(double burnTime) {
        this.burnTime = MathUtil.clamp(this.burnTime + burnTime, 0, MAX_BURN_TIME);
    }

    @Override
    public void update(double dt) {
        super.update(dt);    //To change body of overridden methods use File | Settings | File Templates.
        for (GameObject gameObject : GameObject.ALL_OBJECTS) {
            if (gameObject instanceof CelestialObject) {
                double[] force = CelestialObjectUtil.force(this, (CelestialObject) gameObject);
                //System.out.println(MathUtil.printVector(force));
                this.accelerate(force[0] * dt, force[1] * dt);
            }
        }

        this.burn(dt);
        this.move(dt);
        this.turn(dt);
    }

    private void turn(double dt) {
        this.rotate(angularVelocity * dt);
    }

    private void burn(double dt) {
        this.burnTime = MathUtil.clamp(this.burnTime - dt, 0, MAX_BURN_TIME);
        if (this.burnTime == 0) {
            flame.turnOff();
        } else {
            double radians = Math.toRadians(this.getRotation());
            double xBurnPower = Math.cos(radians) * BURN_POWER * dt;
            double yBurnPower = Math.sin(radians) * BURN_POWER * dt;
            //System.out.println(MathUtil.printVector(new double[]{xBurnPower, yBurnPower}));
            this.accelerate(xBurnPower, yBurnPower);
            flame.turnOn();
        }
    }

    public void move(double dt) {
        double[] position = this.getPosition();
        position[0] += this.velocity[0] * dt;
        position[1] += this.velocity[1] * dt;
        this.setPosition(position[0], position[1]);
    }

    public void pewpew() {
        LaserBolt laserBolt = new LaserBolt(this);
    }
}
