import acm.graphics.GCompound;

/**
 * The Bonus class is an abstract class representing the basic properties and behavior of bonuses in the Breakout game.
 * It extends GCompound.
 */
public abstract class Bonus extends GCompound {

    private double AccelerationX = 0; // X-axis acceleration of the bonus
    private double AccelerationY = 0; // Y-axis acceleration of the bonus
    private double VelocityX; // X-axis velocity of the bonus
    private double VelocityY; // Y-axis velocity of the bonus
    private double PositionX; // X-coordinate of the bonus
    private double PositionY; // Y-coordinate of the bonus
    private double Width; // Width of the bonus
    private double Height; // Height of the bonus
    private boolean isActive; // Flag indicating whether the bonus is active or not

    /**
     * Abstract method to move the bonus.
     */
    abstract void move();

    /**
     * Abstract method to handle collision with an activator (e.g., BreakerPaddle).
     */
    abstract void collideWithActivator();

    /**
     * Abstract method to define the action when the bonus is used.
     */
    abstract void use();

    /**
     * Abstract method to check if the bonus is out of bounds.
     */
    abstract void checkIfOutOfBounds();
}
