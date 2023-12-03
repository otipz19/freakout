import acm.graphics.GCompound;

/**
 * The ScoreBoard class represents the scoreboard in the game.
 */
public class ScoreBoard extends GCompound {
    private static final String FONT = "comicsans-";
    private SmartLabel label;
    private double width;
    private double height;

    /**
     * Creates a new ScoreBoard with the specified position, width, and height.
     *
     * @param x      The x-coordinate of the scoreboard.
     * @param y      The y-coordinate of the scoreboard.
     * @param width  The width of the scoreboard.
     * @param height The height of the scoreboard.
     */
    public ScoreBoard(double x, double y, double width, double height) {
        this.setLocation(x, y);
        this.width = width;
        this.height = height;
        label = new SmartLabel(0, 0, width, height, "0", FONT);
        label.setColor(ColorPalette.LIGHT_GRAY);
        setScore(0);
        add(label);
    }

    /**
     * Sets the score displayed on the scoreboard.
     *
     * @param score The score to be set.
     */
    public void setScore(int score) {
        label.setLabel(Integer.toString(score));
        label.setLocation(4 * (width - label.getWidth()) / 5, label.getY());
    }
}
