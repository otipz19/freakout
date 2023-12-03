import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.awt.*;

/**
 * Represents a SpeedBonus in the Breakout game, providing an increase in ball speed upon activation.
 */
public class SpeedBonus extends Bonus {

    private double AccelerationX = 0;
    private double AccelerationY = 0.1;
    private double VelocityX = 0;
    private double VelocityY = 0;
    private double PositionX;
    private double PositionY;
    private double Width = 20;
    private double Height = 20;
    private boolean isActive;

    /**
     * Initializes a SpeedBonus at the specified position.
     *
     * @param Px The x-coordinate of the bonus.
     * @param Py The y-coordinate of the bonus.
     */
    SpeedBonus(double Px, double Py) {
        PositionX = Px;
        PositionY = Py;
        setLocation(PositionX, PositionY);
        isActive = true;
        construct();
    }

    /**
     * Constructs the graphical representation of the SpeedBonus.
     */
    private void construct() {
        GRect outerRect = new GRect(0, 0, Width, Height);
        outerRect.setFilled(false);
        outerRect.setColor(Color.GREEN);
        add(outerRect);

        GRect innerRect = new GRect(5, 5, 10, 10);
        innerRect.setColor(Color.GREEN);
        innerRect.setFilled(true);
        add(innerRect);
    }

    /**
     * Updates the state of the SpeedBonus, moving it and checking for collisions or removal conditions.
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
     * Handles collision with the activator (Paddle), triggering the bonus effect and removing the bonus.
     */
    @Override
    void collideWithActivator() {
        Paddle paddle = Paddle.getInstance();
        if (paddle != null && (paddle.getX() < PositionX + Width && paddle.getX() + paddle.getWidth() > PositionX) &&
                (paddle.getY() < PositionY + Height && paddle.getY() + paddle.getHeight() > PositionY)) {
            use();
            Breakout.removeObject(this);
            Breakout.getLevel().firstSpeedBonus = null;
        }
    }

    /**
     * Moves the SpeedBonus based on its velocity.
     */
    @Override
    void move() {
        VelocityX += AccelerationX;
        VelocityY += AccelerationY;
        PositionX += VelocityX;
        PositionY += VelocityY;
    }

    /**
     * Applies the bonus effect, modifying the velocity of the BreakerBall.
     */
    @Override
    void use() {
        BreakerBall ball = BreakerBall.getBall();
        RandomGenerator randomGenerator = new RandomGenerator();
        ball.setVelocity(ball.getVelocityX() * randomGenerator.nextDouble(0.8, 1.2),
                ball.getVelocityY() * randomGenerator.nextDouble(0.8, 1.2));
    }

    /**
     * Checks if the SpeedBonus is out of bounds, removing it if necessary.
     */
    @Override
    void checkIfOutOfBounds() {
        if (PositionY > Breakout.getLevel().height) {
            Breakout.removeObject(Breakout.getLevel().firstSpeedBonus);
            Breakout.getLevel().firstSpeedBonus = null;
        }
    }
}
