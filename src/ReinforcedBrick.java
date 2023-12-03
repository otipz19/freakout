import java.awt.*;

/**
 * The ReinforcedBrick class represents a reinforced brick in the game.
 */
public class ReinforcedBrick extends BaseBrick {
    private static final Color REINFORCED_COLOR = Color.BLACK;
    private final Color plainColor;

    /**
     * Creates a new ReinforcedBrick with the specified position, size, and color.
     *
     * @param x      The x-coordinate of the brick.
     * @param y      The y-coordinate of the brick.
     * @param width  The width of the brick.
     * @param height The height of the brick.
     * @param color  The color of the brick.
     */
    public ReinforcedBrick(double x, double y, double width, double height, Color color) {
        super(x, y, REINFORCED_BRICK_SCORE, REINFORCED_BRICK_LIVES, width, height, REINFORCED_COLOR);
        this.plainColor = color;
    }

    /**
     * Handles the action to be taken when a live of the reinforced brick decreases.
     */
    @Override
    protected void onLiveDecrease() {
        if (lives > 0) {
            changeColor(plainColor);
        }
    }
}
