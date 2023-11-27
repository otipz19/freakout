import acm.graphics.*;

public class BreakerBall extends GCompound {
    private float AccelerationX = 0;
    private float AccelerationY = 0;
    private float VelocityX;
    private float VelocityY;
    private float PositionX;
    private float PositionY;
    private float Width;
    private float Height;

    /**
     * Full constructor.(AccelerationX,AccelerationY,VelocityX,VelocityY,PosX,PosY,Width,Height
     */
    BreakerBall(float Ax, float Ay, float Vx, float Vy, float Px, float Py, float W, float H) {
        AccelerationX = Ax;
        AccelerationY = Ay;
        VelocityX = Vx;
        VelocityY = Vy;
        PositionX = Px;
        PositionY = Py;
        Width = W;
        Height = H;
        construct();
    }

    /**
     * Basic constructor.(VelocityX,VelocityY,PosX,PosY,Width,Height
     */
    BreakerBall(float Vx, float Vy, float Px, float Py, float W, float H) {
        VelocityX = Vx;
        VelocityY = Vy;
        PositionX = Px;
        PositionY = Py;
        setLocation(PositionX, PositionY);
        Width = W;
        Height = H;
        construct();
    }

    public void update() {
        updateStatesNoCollision();
        collideWithContainer();
        checkCollisionsWithObjects();
        move(VelocityX, VelocityY);
    }

    /**
     * Updates speeds and positions of self
     */
    private void updateStatesNoCollision() {
        VelocityX += AccelerationX;
        VelocityY += AccelerationY;
        PositionX += VelocityX;
        PositionY += VelocityY;
    }

    private void collideWithContainer() {
        GPoint ReflectVec = BoxContainer.getContainer().reflect(PositionX, PositionY, Width, Height);
        VelocityX *= ReflectVec.getX();
        VelocityY *= ReflectVec.getY();
    }

    private void construct() {
        GOval ov = new GOval(0, 0, Width, Height);
        ov.setFilled(true);
        add(ov);
    }

    private void checkCollisionsWithObjects() {
        CollisionInfo collisionInfo = getObjectAtColliderPoints();
        if (collisionInfo != null) {
            GObject object = collisionInfo.getObject();
            if (object instanceof Paddle || object instanceof Brick) {
                GLine side = getClosestSide(object, collisionInfo.getCollisionPoint());
                GPoint origin = new GPoint(VelocityX, VelocityY);
                GPoint reflected = getReflectedVector(origin, side);
                VelocityY = (float)reflected.getY();
                VelocityX = (float)reflected.getX();
            }
        }
    }

    private CollisionInfo getObjectAtColliderPoints() {
        for (double y = getY(); y <= getY() + Height; y += Height / 2) {
            for (double x = getX(); x <= getX() + Width; x += Width / 2) {
                GObject object = Breakout.getInstance().getElementAt(x, y);
                if (object != null && object != this) {
                    return new CollisionInfo(x, y, object);
                }
            }
        }
        return null;
    }

    private GLine getClosestSide(GObject object, GPoint collisionPoint){
        //Corner vertices of collided object
        GPoint leftUp = new GPoint(object.getX(), object.getY());
        GPoint rightUp = new GPoint(object.getX() + object.getWidth(), object.getY());
        GPoint rightDown = new GPoint(object.getX() + object.getWidth(), object.getY() + object.getHeight());
        GPoint leftDown = new GPoint(object.getX(), object.getY() + object.getHeight());

        //Sides of collided object
        GLine upSide = getSide(leftUp, rightUp);
        GLine downSide = getSide(leftDown, rightDown);
        GLine leftSide = getSide(leftUp, leftDown);
        GLine rightSide = getSide(rightUp, rightDown);

        //Pretty stupid way to find the closest side
        double minDistance = getDistanceToMid(collisionPoint, upSide);
        GLine closestSide = upSide;
        double curDistance = getDistanceToMid(collisionPoint, downSide);
        if(curDistance < minDistance){
            closestSide = downSide;
            minDistance = curDistance;
        }
        curDistance = getDistanceToMid(collisionPoint, leftSide);
        if(curDistance < minDistance){
            closestSide = leftSide;
            minDistance = curDistance;
        }
        curDistance = getDistanceToMid(collisionPoint, rightSide);
        if(curDistance < minDistance){
            closestSide = rightSide;
        }

        return closestSide;
    }

    private GLine getSide(GPoint start, GPoint end){
        return new GLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    private double getDistanceToMid(GPoint point, GLine line){
        GPoint mid = getMidPoint(line);
        double x = point.getX() - mid.getX();
        double y = point.getY() - mid.getY();
        return Math.sqrt(x * x + y * y);
    }

    private GPoint getMidPoint(GLine line){
        GPoint start = line.getStartPoint();
        GPoint end = line.getEndPoint();
        return new GPoint((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    private GPoint getReflectedVector(GPoint vector, GLine line){
        GPoint normalVec = getNormalVector(line);
        return subtract(vector, productByScalar(productByScalar(normalVec, dotProduct(vector, normalVec) / dotProduct(normalVec, normalVec)), 2));
    }

    private GPoint getNormalVector(GLine line){
        GPoint start = line.getStartPoint();
        GPoint end = line.getEndPoint();
        return new GPoint(start.getY() - end.getY(), end.getX() - start.getX());
    }

    private double dotProduct(GPoint first, GPoint second){
        return first.getX() * second.getX() + first.getY() * second.getY();
    }

    private GPoint subtract(GPoint first, GPoint second){
        return new GPoint(first.getX() - second.getX(), first.getY() - second.getY());
    }

    private GPoint productByScalar(GPoint vector, double scalar){
        return new GPoint(vector.getX() * scalar, vector.getY() * scalar);
    }
}
