import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.util.RandomGenerator;

public class BreakerBall extends GCompound implements ICollidable{
    private double AccelerationX = 0;
    private double AccelerationY = 0;
    private double Vy0, Vx0;
    private double VelocityX;
    private double VelocityY;
    public void setVelocity(double vX, double vY){
        VelocityX = vX;
        VelocityY = -vY;
    }
    public double getVelocityX(){return VelocityX;}
    public double getVelocityY(){return VelocityY;}
    private double PositionX;
    private double PositionY;
    private double Width;
    private double Height;
    private boolean isActive;

    private ICollidable lastCollision;

    static private BreakerBall Ball;
    public static BreakerBall getBall() {
        return Ball;
    }
    public void setActive(boolean opt){
        isActive = opt;
    }

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
        VelocityY = -Vy;
        randVX(true);
        PositionX = Px;
        PositionY = Py;
        setLocation(PositionX, PositionY);
        Width = W;
        Height = H;
        isActive = false;
        construct();
        Ball = this;
    }

    public void update() {
        if(isActive) {
            collideWithContainer();
            checkCollisionsWithObjects();
            updateStatesNoCollision();
            setLocation(PositionX, PositionY);
        }else if(Paddle.getInstance() != null){
            Paddle P = Paddle.getInstance();
            PositionX = P.getX()+P.getWidth()/2-Width/2;
            PositionY = P.getY()-Height-20;
            setLocation(P.getX()+P.getWidth()/2-Width/2,P.getY()-Height-20);
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


    /** randomize vx in range 30 - 60 deg*/
    private void randVX(boolean randSign){
        VelocityX = Math.abs(VelocityY) * RandomGenerator.getInstance().nextDouble(0.7,1.3);
        if(randSign)
            VelocityX *= randSign();
    }

    private double randSign(){
        if(RandomGenerator.getInstance().nextBoolean(0.5))
            return -1;
        else
            return 1;
    }


    private void collideWithContainer() {
        GPoint ReflectVec = BoxContainer.getContainer().reflect(PositionX, PositionY, Width, Height);
        if (ReflectVec == null){
            handleOutOfBounds();
        } else {
            VelocityX *= ReflectVec.getX();
            VelocityY *= ReflectVec.getY();
            AudioManager.playBounce();
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
        ColorPalette pallete = Breakout.getLevel().getPalette();
        ov.setColor(pallete.getBall());
        ov.setFillColor(pallete.getBall());
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
        for(double angle = 0; angle <= 360; angle +=45){
            double radians = Math.toRadians(angle);
            double xTurned = midX + (startX - midX) * Math.cos(radians) - (startY - midY) * Math.sin(radians);
            double yTurned = midY + (startX - midX) * Math.sin(radians) + (startY - midY) * Math.cos(radians);
            GObject object = Breakout.getObjectAt(xTurned, yTurned);
            if(object instanceof ICollidable && object != this && object != lastCollision){
                if(angle == 0 || angle==180){
                    VelocityX *= -1;
                }
                else{
                    VelocityY *= -1;
                }
                ICollidable collidable = (ICollidable)object;
                lastCollision = collidable;
                collidable.onCollision(this);
                AudioManager.playBounce();
                return;
            }
        }
        lastCollision = null;
    }
}
