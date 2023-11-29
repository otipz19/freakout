import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;

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
        GObject object = collideAndReturnObject();
        if (object != null) {

        }
    }

    /**
     * Checks collisions by 8 points.
     * Each point is got by rotating around the centre of ball,
     * starting from the centre of right side of circumscribed square
     */
    private GObject collideAndReturnObject(){
        double startX = getX() + Width;
        double startY = getY() + Height / 2;
        double midX = getX() + Width / 2;
        double midY = getY() + Height / 2;
        for(double angle = 0; angle <= 360; angle += 45){
            double radians = Math.toRadians(angle);
            double xTurned = midX + (startX - midX) * Math.cos(radians) - (startY - midY) * Math.sin(radians);
            double yTurned = midY + (startX - midX) * Math.sin(radians) + (startY - midY) * Math.cos(radians);
            GObject object = Breakout.getInstance().getElementAt(xTurned, yTurned);
            if(object != null && object != this){
                if(angle == 0 || angle == 180){
                    VelocityX *= -1;
                }
                else{
                    VelocityY *= -1;
                }
                return object;
            }
        }
        return null;
    }
}
