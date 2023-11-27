import acm.graphics.GObject;
import acm.graphics.GPoint;

public class CollisionInfo {
    private GPoint collisionPoint;
    private GObject object;

    public GPoint getCollisionPoint() {
        return collisionPoint;
    }

    public GObject getObject() {
        return object;
    }

    public CollisionInfo(double x, double y, GObject object) {
        this.collisionPoint = new GPoint(x, y);
        this.object = object;
    }
}
