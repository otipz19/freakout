import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class BreakerBall extends GCompound {
    private double AccelerationX = 0;
    private double AccelerationY = 0;
    private double VelocityX;
    private double VelocityY;
    private double PositionX;
    private double PositionY;
    private double Width;
    private double Height;
    private boolean isActive;
    public void setActive(boolean opt){
        isActive = opt;
    }

    private Breakout program = Breakout.getInstance();

    /**
     * Full constructor.(AccelerationX,AccelerationY,VelocityX,VelocityY,PosX,PosY,Width,Height
     */
    BreakerBall(double Ax, double Ay, double Vx, double Vy, double Px, double Py, double W, double H) {
        AccelerationX = Ax;
        AccelerationY = Ay;
        VelocityX = Vx;
        VelocityY = Vy;
        PositionX = Px;
        PositionY = Py;
        Width = W;
        Height = H;
        isActive = true;
        construct();
    }

    /**
     * Basic constructor.(VelocityX,VelocityY,PosX,PosY,Width,Height
     */
    BreakerBall(double Vx, double Vy, double Px, double Py, double W, double H) {
        VelocityX = Vx;
        VelocityY = Vy;
        PositionX = Px;
        PositionY = Py;
        setLocation(PositionX, PositionY);
        Width = W;
        Height = H;
        isActive = true;
        construct();
    }

    public void update() {
        if(isActive) {
            updateStatesNoCollision();
            collideWithContainer();
            checkCollisionsWithObjects();
            move(VelocityX, VelocityY);
        }
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
        if (ReflectVec == null){
            handleOutOfBounds();
        } else {
            VelocityX *= ReflectVec.getX();
            VelocityY *= ReflectVec.getY();
        }
    }

    private void handleOutOfBounds(){
        GPoint center = BoxContainer.getContainer().getCenter();
        setLocation(center);
        PositionX = center.getX();
        PositionY = center.getY();
        setActive(false);
        Breakout.getLevel().decrementLife();
    }

    private void construct() {
        GOval ov = new GOval(0, 0, Width, Height);
        ov.setFilled(true);
        add(ov);
    }

    private void checkCollisionsWithObjects() {
        GObject object = collideAndReturnObject();
        if (object != null && object instanceof Brick) {
            Brick brick = (Brick)object;
            brick.onCollision();
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
            GObject object = program.getElementAt(xTurned, yTurned);
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
