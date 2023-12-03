import acm.graphics.GRect;
import acm.util.RandomGenerator;
import java.awt.*;

/**
 * The EnlargementBonus class represents a bonus that enlarges the paddle upon activation.
 */
public class EnlargementBonus extends Bonus {

    private double AccelerationX = 0; // X-axis acceleration
    private double AccelerationY = 0; // Y-axis acceleration
    private double VelocityX = 0; // X-axis velocity
    private double VelocityY = 3; // Y-axis velocity
    private double PositionX; // X-coordinate of the bonus
    private double PositionY; // Y-coordinate of the bonus
    private double Width = 20; // Width of the bonus
    private double Height = 20; // Height of the bonus
    private boolean isActive; // Flag indicating whether the bonus is active

    /**
     * Basic constructor that sets the position of the bonus.
     *
     * @param Px The X-coordinate of the bonus.
     * @param Py The Y-coordinate of the bonus.
     */
    EnlargementBonus(double Px, double Py) {
        PositionX = Px;
        PositionY = Py;
        setLocation(PositionX, PositionY);
        isActive = true;
        construct();
    }

    /**
     * Constructs the graphical representation of the bonus.
     */
    private void construct() {
        GRect outerRect = new GRect(0, 0, Width, Height);
        outerRect.setFilled(false);
        outerRect.setColor(Color.RED);
        add(outerRect);

        GRect innerRect = new GRect(5, 5, 10, 10);
        innerRect.setFilled(true);
        innerRect.setFillColor(Color.RED);
        add(innerRect);
    }

    /**
     * Updates the bonus state by moving and checking for collisions.
     */
    public void update() {
        if (isActive) {
            move();
            collideWithActivator();
            move(VelocityX, VelocityY);
            checkIfOutOfBounds();
        }
    }

    /**
     * Handles the collision with the activator (paddle) and triggers the bonus effect.
     */
    @Override
    void collideWithActivator() {
        Paddle paddle = Paddle.getInstance();
        if (paddle != null && (paddle.getX() < PositionX + Width && paddle.getX() + paddle.getWidth() > PositionX)
                && (paddle.getY() < PositionY + Height && paddle.getY() + paddle.getHeight() > PositionY)) {
            use();
            Breakout.removeObject(this);
            Breakout.getLevel().firstEnlargementBonus = null;
        }
    }

    /**
     * Moves the bonus based on its velocity and acceleration.
     */
    @Override
    void move() {
        VelocityX += AccelerationX;
        VelocityY += AccelerationY;
        PositionX += VelocityX;
        PositionY += VelocityY;
    }

    /**
     * Applies the bonus effect, enlarging the paddle.
     */
    @Override
    void use() {
        Paddle paddle = Paddle.getInstance();
        RandomGenerator randomGenerator = new RandomGenerator();
        paddle.scale(randomGenerator.nextDouble(1, 1.1), 1);
    }

    /**
     * Checks if the bonus is out of bounds and removes it if necessary.
     */
    @Override
    void checkIfOutOfBounds() {
        if (PositionY > Breakout.getLevel().height) {
            Breakout.removeObject(Breakout.getLevel().firstEnlargementBonus);
            Breakout.getLevel().firstEnlargementBonus = null;
        }
    }
}