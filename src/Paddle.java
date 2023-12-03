import acm.graphics.GCompound;
import acm.graphics.GRect;

import java.awt.*;

/**
 * The Paddle class represents the player's paddle in the game.
 */
public class Paddle extends GCompound implements ICollidable {
    private GRect rect;

    // Singleton instance for Paddle
    private static Paddle instance;

    /**
     * Returns the singleton instance of the Paddle class.
     *
     * @return The Paddle instance.
     */
    public static Paddle getInstance() {
        return instance;
    }

    /**
     * Creates a new Paddle with the specified position, width, height, and color.
     *
     * @param x      The x-coordinate of the paddle.
     * @param y      The y-coordinate of the paddle.
     * @param width  The width of the paddle.
     * @param height The height of the paddle.
     * @param color  The color of the paddle.
     */
    public Paddle(double x, double y, double width, double height, Color color) {
        instance = this;
        this.setLocation(x, y);
        rect = new GRect(0, 0, width, height);
        rect.setColor(color);
        rect.setFillColor(color);
        rect.setFilled(true);
        add(rect);
    }

    /**
     * Handles collision with other objects.
     *
     * @param other The ICollidable object with which the collision occurs.
     */
    @Override
    public void onCollision(ICollidable other) {
        // Handle collision logic if needed
    }
}
