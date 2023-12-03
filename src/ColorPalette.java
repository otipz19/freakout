import java.awt.*;

/**
 * The ColorPalette class defines color palettes for different levels in the Breakout game.
 */
public class ColorPalette {

    public static final Color LIGHT_GRAY = new Color(0xf9f9f9); // Light gray color constant

    public static final ColorPalette FIRST_PALETTE = new FirstPalette(); // Color palette for the first level
    public static final ColorPalette SECOND_PALETTE = new SecondPalette(); // Color palette for the second level
    public static final ColorPalette THIRD_PALETTE = new ThirdPalette(); // Color palette for the third level

    protected Color background; // Background color
    protected Color paddle; // Paddle color
    protected Color ball; // Ball color
    protected Color brick0; // Color for the first type of brick
    protected Color brick1; // Color for the second type of brick
    protected Color brick2; // Color for the third type of brick
    protected Color brick3; // Color for the fourth type of brick
    protected Color brick4; // Color for the fifth type of brick
    protected Color brick5; // Color for the sixth type of brick

    /**
     * Gets the background color.
     *
     * @return The background color.
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Gets the paddle color.
     *
     * @return The paddle color.
     */
    public Color getPaddle() {
        return paddle;
    }

    /**
     * Gets the ball color.
     *
     * @return The ball color.
     */
    public Color getBall() {
        return ball;
    }

    /**
     * Gets the color for a specific brick index.
     *
     * @param index The index of the brick.
     * @return The color for the specified brick index.
     */
    public Color getBrickColor(int index) {
        switch (index) {
            case 0:
                return brick0;
            case 1:
                return brick1;
            case 2:
                return brick2;
            case 3:
                return brick3;
            case 4:
                return brick4;
            default:
                return brick5;
        }
    }
}
