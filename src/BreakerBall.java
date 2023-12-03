import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.util.RandomGenerator;

/**
 * The BreakerBall class represents the ball used in the Breakout game.
 * It extends GCompound and implements the ICollidable interface.
 */
public class BreakerBall extends GCompound implements ICollidable {

    private double AccelerationX = 0; // X-axis acceleration of the ball
    private double AccelerationY = 0; // Y-axis acceleration of the ball
    private double Vy0, Vx0; // Initial velocities (not used)
    private double VelocityX; // X-axis velocity of the ball
    private double VelocityY; // Y-axis velocity of the ball

    /**
     * Sets the velocity of the ball.
     *
     * @param vX The X-axis velocity.
     * @param vY The Y-axis velocity.
     */
    public void setVelocity(double vX, double vY) {
        VelocityX = vX;
        VelocityY = -vY;
    }

    /**
     * Gets the X-axis velocity of the ball.
     *
     * @return The X-axis velocity.
     */
    public double getVelocityX() {
        return VelocityX;
    }

    /**
     * Gets the Y-axis velocity of the ball.
     *
     * @return The Y-axis velocity.
     */
    public double getVelocityY() {
        return VelocityY;
    }

    private double PositionX; // X-coordinate of the ball
    private double PositionY; // Y-coordinate of the ball
    private double Width; // Width of the ball
    private double Height; // Height of the ball
    private boolean isActive; // Flag indicating whether the ball is active

    private ICollidable lastCollision; // Reference to the last object collided with

    private static BreakerBall Ball; // Singleton instance of the BreakerBall

    /**
     * Gets the singleton instance of the BreakerBall.
     *
     * @return The singleton instance of the BreakerBall.
     */
    public static BreakerBall getBall() {
        return Ball;
    }

    /**
     * Sets the activity state of the ball.
     *
     * @param opt The activity state to set.
     */
    public void setActive(boolean opt) {
        isActive = opt;
    }

    /**
     * Full constructor with acceleration. (AccelerationX, AccelerationY, VelocityX, VelocityY, PosX, PosY, Width, Height)
     */
    BreakerBall(double Ax, double Ay, double Vy, double Px, double Py, double W, double H) {
        this(Vy, Px, Py, W, H);
        AccelerationX = Ax;
        AccelerationY = Ay;
    }

    /**
     * Basic constructor without acceleration. (VelocityX, VelocityY, PosX, PosY, Width, Height)
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

    /**
     * Updates the state of the ball.
     */
    public void update() {
        if (isActive) {
            collideWithContainer();
            checkCollisionsWithObjects();
            updateStatesNoCollision();
            setLocation(PositionX, PositionY);
        } else if (Paddle.getInstance() != null) {
            Paddle P = Paddle.getInstance();
            PositionX = P.getX() + P.getWidth() / 2 - Width / 2;
            PositionY = P.getY() - Height - 20;
            setLocation(P.getX() + P.getWidth() / 2 - Width / 2, P.getY() - Height - 20);
            randVX(true);
        }
    }

    @Override
    public void onCollision(ICollidable other) {
        // Not used in this implementation
    }

    /**
     * Updates speeds and positions of the ball in case of no collision.
     */
    private void updateStatesNoCollision() {
        VelocityX += AccelerationX;
        VelocityY += AccelerationY;
        PositionX += VelocityX;
        PositionY += VelocityY;
    }

    /**
     * Randomizes the X-axis velocity in the range of 30 - 60 degrees.
     *
     * @param randSign Flag indicating whether to randomize the sign of the velocity.
     */
    private void randVX(boolean randSign) {
        VelocityX = Math.abs(VelocityY) * RandomGenerator.getInstance().nextDouble(0.7, 1.3);
        if (randSign)
            VelocityX *= randSign();
    }

    /**
     * Randomly generates the sign for the velocity.
     *
     * @return -1 or 1 based on a random boolean value.
     */
    private double randSign() {
        if (RandomGenerator.getInstance().nextBoolean(0.5))
            return -1;
        else
            return 1;
    }

    /**
     * Handles collision with the container (walls).
     */
    private void collideWithContainer() {
        GPoint ReflectVec = BoxContainer.getContainer().reflect(PositionX, PositionY, Width, Height);
        if (ReflectVec == null) {
            handleOutOfBounds();
        } else {
            VelocityX *= ReflectVec.getX();
            VelocityY *= ReflectVec.getY();
            AudioManager.playBounce();
        }
    }

    /**
     * Handles the ball being out of bounds.
     */
    private void handleOutOfBounds() {
        GPoint center = BoxContainer.getContainer().getCenter();
        setLocation(center);
        PositionX = center.getX();
        PositionY = center.getY();
        setActive(false);
        Breakout.getLevel().decrementLife();
    }

    /**
     * Constructs the visual representation of the ball.
     */
    private void construct() {
        GOval ov = new GOval(0, 0, Width, Height);
        ColorPalette palette = Breakout.getLevel().getPalette();
        ov.setColor(palette.getBall());
        ov.setFillColor(palette.getBall());
        ov.setFilled(true);
        add(ov);
    }

    /**
     * Checks collisions with objects using eight points around the ball.
     */
    private void checkCollisionsWithObjects() {
        double startX = getX() + Width;
        double startY = getY() + Height / 2;
        double midX = getX() + Width / 2;
        double midY = getY() + Height / 2;
        for (double angle = 0; angle <= 360; angle += 45) {
            double radians = Math.toRadians(angle);
            double xTurned = midX + (startX - midX) * Math.cos(radians) - (startY - midY) * Math.sin(radians);
            double yTurned = midY + (startX - midX) * Math.sin(radians) + (startY - midY) * Math.cos(radians);
            GObject object = Breakout.getObjectAt(xTurned, yTurned);
            if (object instanceof ICollidable && object != this && object != lastCollision) {
                if (angle == 0 || angle == 180) {
                    VelocityX *= -1;
                } else {
                    VelocityY *= -1;
                }
                ICollidable collidable = (ICollidable) object;
                lastCollision = collidable;
                collidable.onCollision(this);
                AudioManager.playBounce();
                return;
            }
        }
        lastCollision = null;
    }
}
