package ass1.solarSystemGame.objects.rocket;

import ass1.GameObject;
import ass1.MathUtil;
import ass1.solarSystemGame.objects.CelestialObject;

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
    private double maxAngularVelocity = 60;
    private double impulse = 0;
    private double maxImpulse = 10;

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

    public void accelerateAngularVelocity(double dJ) {
        this.angularVelocity = MathUtil.clamp(this.angularVelocity + dJ, -this.maxAngularVelocity, this.maxAngularVelocity);
    }

    public double getMaxAngularVelocity() {
        return maxAngularVelocity;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double vx, double vy) {
        this.velocity[0] = vx;
        this.velocity[1] = vy;
    }

    public void accelerate(double dvx, double dvy) {
        this.velocity[0] += dvx;
        this.velocity[1] += dvy;
    }

    public void increaseImpulse(double dI) {
        this.impulse = MathUtil.clamp(impulse + dI, 0, maxImpulse);
    }

    public void move(double dt) {
        double[] position = this.getPosition();
        position[0] += this.velocity[0] * dt;
        position[1] += this.velocity[1] * dt;
        this.setPosition(position[0], position[1]);
    }

    @Override
    public void drawSelf(GL2 gl) {
        super.drawSelf(gl);    //To change body of overridden methods use File | Settings | File Templates.
        gl.glColor3d(0, 0.2, 1);
        gl.glBegin(GL2.GL_LINE); {
            gl.glVertex2d(0, 0);
            gl.glVertex2d(5 * Math.cos(getGlobalRotation()), 5 * Math.sin(getGlobalRotation()));
        } gl.glEnd();
    }

    @Override
    public void update(double dt) {
        super.update(dt);    //To change body of overridden methods use File | Settings | File Templates.
        for (GameObject gameObject : GameObject.ALL_OBJECTS) {
            if (gameObject instanceof CelestialObject) {
                //double[] force = CelestialObjectUtil.force(this, (CelestialObject) gameObject);
                //this.accelerate(force[0], force[1]);
            }
        }
        this.accelerateByImpulse(dt);
        this.move(dt);
        this.turn(dt);
    }

    private void turn(double dt) {
        this.rotate(angularVelocity * dt);
    }

    private void accelerateByImpulse(double dt) {
        double changeInImpulse = impulse * dt;
        double newImpulse = MathUtil.clamp(impulse - changeInImpulse, 0, maxImpulse);
        impulse = newImpulse;
        velocity[0] += Math.sin(getGlobalRotation()) * changeInImpulse;
        velocity[1] += Math.cos(getGlobalRotation()) * changeInImpulse;
    }
}
