import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.Color;

/**
 * The BaseBrick class is an abstract class representing the basic properties and behavior of a brick in the Breakout game.
 * It extends GCompound and implements the ICollidable interface.
 */
public abstract class BaseBrick extends GCompound implements ICollidable {

    private static final RandomGenerator rng = RandomGenerator.getInstance(); // Random number generator instance

    // Constants defining scores and lives for different types of bricks
    protected static final int SIMPLE_BRICK_SCORE = 5;
    protected static final int SIMPLE_BRICK_LIVES = 1;
    protected static final int REINFORCED_BRICK_SCORE = 20;
    protected static final int REINFORCED_BRICK_LIVES = 2;
    protected static final int ULTRA_BRICK_SCORE = 50;
    protected static final int ULTRA_BRICK_LIVES = 3;

    private final int score; // Score value of the brick
    protected int lives; // Remaining lives of the brick
    private final GRect rect; // Rectangle representing the visual aspect of the brick

    /**
     * Constructor to create a BaseBrick instance.
     *
     * @param x      The x-coordinate of the brick.
     * @param y      The y-coordinate of the brick.
     * @param score  The score value of the brick.
     * @param lives  The initial number of lives of the brick.
     * @param width  The width of the brick.
     * @param height The height of the brick.
     * @param color  The color of the brick.
     */
    public BaseBrick(double x, double y, int score, int lives, double width, double height, Color color) {
        this.setLocation(x, y);
        this.score = score;
        this.lives = lives;
        rect = new GRect(0, 0, width, height);
        rect.setColor(color);
        rect.setFillColor(color);
        rect.setFilled(true);
        add(rect);
    }

    /**
     * Gets the color of the brick.
     *
     * @return The color of the brick.
     */
    public Color getColor() {
        return rect.getColor();
    }

    /**
     * Handles collision with another collidable object, typically a BreakerBall.
     *
     * @param other The collidable object that collided with the brick.
     */
    public void onCollision(ICollidable other) {
        if (other instanceof BreakerBall) {
            lives--;
            onLiveDecrease();
            if (lives <= 0) {
                Breakout.getLevel().addScore(score);
                BricksManager.getInstance().brickDestroyed();
                switch (rng.nextInt(2)) {
                    case 0:
                        if (Breakout.getLevel().shouldSpawnEnlargementBonus &&
                                Breakout.getLevel().firstEnlargementBonus == null) {
                            EnlargementBonus a = new EnlargementBonus(getX(), getY());
                            Breakout.getLevel().firstEnlargementBonus = a;
                            Breakout.addObject(a);
                        }
                        break;
                    case 1:
                        if (Breakout.getLevel().shouldSpawnSpeedBonus &&
                                Breakout.getLevel().firstSpeedBonus == null) {
                            SpeedBonus a = new SpeedBonus(getX(), getY());
                            Breakout.getLevel().firstSpeedBonus = a;
                            Breakout.addObject(a);
                        }
                        break;
                }
                Breakout.removeObject(this);
                AudioManager.playBrickBreak();
            }
        }
    }

    /**
     * Abstract method to be implemented by subclasses to define behavior when lives decrease.
     */
    protected abstract void onLiveDecrease();

    /**
     * Changes the color of the brick.
     *
     * @param color The new color of the brick.
     */
    protected void changeColor(Color color) {
        rect.setColor(color);
        rect.setFillColor(color);
    }
}