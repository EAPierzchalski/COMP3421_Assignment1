package ass1.solarSystemGame.objects.rocketcamera;

import ass1.Camera;
import ass1.GameObject;

/**
 * User: Pierzchalski
 * Date: 08/09/13
 * Package: ass1.solarSystemGame.objects.camera
 * Project: COMP3421_Assignment1
 */
public class RocketCamera extends Camera {

    private static final float[] BLACK = new float[]{0, 0, 0, 1};

    private static final double DEFAULT_SCALE = 30;

    private GameObject target = ROOT;

    private static double ZOOM_FACTOR = 1.1;

    public RocketCamera(GameObject target) {
        super();
        this.setBackground(BLACK);
        this.scale(DEFAULT_SCALE);
        this.target = target;
    }

    @Override
    public double[] getPosition() {
        return target.getPosition();
    }

    @Override
    public double[] getGlobalPosition() {
        return target.getGlobalPosition();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public double getGlobalRotation() {
        double rotation;
        if (followingTargetRotation) {
            rotation = target.getGlobalRotation() - 90;
        } else {
            rotation = super.getGlobalRotation();    //To change body of overridden methods use File | Settings | File Templates.
        }
        return rotation;
    }

    @Override
    public double getRotation() {
        double rotation;
        if (followingTargetRotation) {
            rotation = target.getRotation() - 90;
        } else {
            rotation = super.getRotation();    //To change body of overridden methods use File | Settings | File Templates.
        }
        return rotation;
    }

    public boolean isFollowingTargetRotation() {
        return followingTargetRotation;
    }

    public void setFollowingTargetRotation(boolean followingTargetRotation) {
        this.followingTargetRotation = followingTargetRotation;
    }

    private boolean followingTargetRotation = false;

    public GameObject getTarget() {
        return target;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void zoomIn() {
        this.scale(1d/ZOOM_FACTOR);
    }

    public void zoomOut() {
        this.scale(ZOOM_FACTOR);
    }
}
