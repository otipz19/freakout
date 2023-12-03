import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class BreakerBall extends GCompound implements ICollidable{
    private double AccelerationX = 0;
    private double AccelerationY = 0;
    private double Vy0, Vx0;
    private double VelocityX;
    private double VelocityY;
    private double PositionX;
    private double PositionY;
    private double Width;
    private double Height;
    private boolean isActive;

    private ICollidable lastCollision;


    /**
     * Full constructor.(AccelerationX,AccelerationY,VelocityX,VelocityY,PosX,PosY,Width,Height
     */
    BreakerBall(double Ax, double Ay, double Vy, double Px, double Py, double W, double H) {
        this(Vy, Px, Py, W, H);
        AccelerationX = Ax;
        AccelerationY = Ay;
    }

    /**
     * Basic constructor.(VelocityX,VelocityY,PosX,PosY,Width,Height
     */
    BreakerBall(double Vy, double Px, double Py, double W, double H) {
        VelocityX = 1; // just to work
        VelocityY = Vy;
        randVX(true);
        PositionX = Px;
        PositionY = Py;
        setLocation(PositionX, PositionY);
        Width = W;
        Height = H;
        isActive = false;
        construct();
    }

    public void setActive(boolean opt){
        isActive = opt;
    }

    public void update() {
        if(isActive) {
            collideWithContainer();
            checkCollisionsWithObjects();
            updateStatesNoCollision();
            setLocation(PositionX, PositionY);
        }else{
            Paddle P = Paddle.getPaddle();
            PositionX = P.getX()+P.getWidth()/2-Width/2;
            PositionY = P.getY()-Height-5;
            setLocation(P.getX()+P.getWidth()/2-Width/2,P.getY()-Height-5);
            randVX(true);
        }
    }

    @Override
    public void onCollision(ICollidable other) {
        
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


    // randomize vx in range 30 - 60 deg
    private void randVX(boolean randSign){
        VelocityX = Math.signum(VelocityX) * Math.abs(VelocityY) * Math.tan(Math.toRadians(randAngle()));
        if(randSign)
            VelocityX *= randSign();
    }

    private double randSign(){
        if(RandomGenerator.getInstance().nextBoolean(0.5))
            return -1;
        else
            return 1;
    }

    private double randAngle(){
        return RandomGenerator.getInstance().nextDouble(30,60);
    }


    private void collideWithContainer() {
        GPoint ReflectVec = BoxContainer.getContainer().reflect(PositionX, PositionY, Width, Height);
        if (ReflectVec == null){
            handleOutOfBounds();
        } else {
            VelocityX *= ReflectVec.getX();
            VelocityY *= ReflectVec.getY();
            //PositionX = Math.max(Math.min(PositionX,BoxContainer.getContainer().getRightX()-Width/2),BoxContainer.getContainer().getLeftX()+Width/2);
            //PositionX = Math.max(Math.min(PositionX,BoxContainer.getContainer().getBottomY()-Height/2),BoxContainer.getContainer().getTopY()+Height/2);
        }
    }
    private void handleOutOfBounds(){
        isActive = false;
        Breakout.getLevel().decrementLife();
    }

    /*public void respawn(){
        GPoint resp = BoxContainer.getContainer().getRespawnPoint();
        setLocation(resp);
        PositionX = resp.getX();
        PositionY = resp.getY();
        setActive(false);
        randVX(true);
    }*/

    private void construct() {
        GOval ov = new GOval(0, 0, Width, Height);
        ov.setFilled(true);
        add(ov);
    }


    /**
     * Checks collisions by 8 points.
     * Each point is got by rotating around the centre of ball,
     * starting from the centre of right side of circumscribed square
     */
    private void checkCollisionsWithObjects() {
        double startX = getX() + Width;
        double startY = getY() + Height / 2;
        double midX = getX() + Width / 2;
        double midY = getY() + Height / 2;
        for(double angle = 0; angle <= 360; angle += 45){
            double radians = Math.toRadians(angle);
            double xTurned = midX + (startX - midX) * Math.cos(radians) - (startY - midY) * Math.sin(radians);
            double yTurned = midY + (startX - midX) * Math.sin(radians) + (startY - midY) * Math.cos(radians);
            GObject object = Breakout.getObjectAt(xTurned, yTurned);
            if(object instanceof ICollidable && object != this && object != lastCollision){
                if(angle == 0 || angle == 180){
                    VelocityX *= -1;
                }
                else{
                    VelocityY *= -1;
                }
                ICollidable collidable = (ICollidable)object;
                lastCollision = collidable;
                collidable.onCollision(this);
                return;
            }
        }
        lastCollision = null;
    }
}
