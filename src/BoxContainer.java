import acm.graphics.GLine;
import acm.graphics.GPoint;

/**
 * The BoxContainer class represents a basic box container in the Breakout game.
 */
public class BoxContainer {

    private static BoxContainer Container; // Singleton instance of the BoxContainer

    private double leftX; // X-coordinate of the left side of the container
    private double topY; // Y-coordinate of the top side of the container
    private double rightX; // X-coordinate of the right side of the container
    private double bottomY; // Y-coordinate of the bottom side of the container

    /**
     * Gets the singleton instance of the BoxContainer.
     *
     * @return The singleton instance of the BoxContainer.
     */
    public static BoxContainer getContainer() {
        return Container;
    }

    /**
     * Gets the X-coordinate of the left side of the container.
     *
     * @return The X-coordinate of the left side.
     */
    public double getLeftX() {
        return leftX;
    }

    /**
     * Gets the Y-coordinate of the top side of the container.
     *
     * @return The Y-coordinate of the top side.
     */
    public double getTopY() {
        return topY;
    }

    /**
     * Gets the X-coordinate of the right side of the container.
     *
     * @return The X-coordinate of the right side.
     */
    public double getRightX() {
        return rightX;
    }

    /**
     * Gets the Y-coordinate of the bottom side of the container.
     *
     * @return The Y-coordinate of the bottom side.
     */
    public double getBottomY() {
        return bottomY;
    }

    /**
     * Gets the center point of the container.
     *
     * @return The center point of the container.
     */
    public GPoint getCenter() {
        return new GPoint((leftX + rightX) / 2, (topY + bottomY) / 2);
    }

    /**
     * Gets the respawn point within the container.
     *
     * @return The respawn point within the container.
     */
    public GPoint getRespawnPoint() {
        return new GPoint((leftX + rightX) / 2, (topY + bottomY) / 3);
    }

    /**
     * Creates a basic box container with specified coordinates (xTop, yTop, xBottom, yBottom).
     *
     * @param x1 X-coordinate of the top-left corner.
     * @param y1 Y-coordinate of the top-left corner.
     * @param x2 X-coordinate of the bottom-right corner.
     * @param y2 Y-coordinate of the bottom-right corner.
     */
    BoxContainer(double x1, double y1, double x2, double y2) {
        leftX = x1;
        topY = y1;
        rightX = x2;
        bottomY = y2;
        Container = this;
        drawUpperLine(x1, y1, x2);
    }

    private static void drawUpperLine(double x1, double y1, double x2) {
        GLine line = new GLine(x1, y1, x2, y1);
        line.setColor(ColorPalette.LIGHT_GRAY);
        Breakout.addObject(line);
    }

    /**
     * Reflects the specified rectangle against the container boundaries.
     *
     * @param x X-coordinate of the rectangle.
     * @param y Y-coordinate of the rectangle.
     * @param w Width of the rectangle.
     * @param h Height of the rectangle.
     * @return A GPoint indicating reflection direction (1: right, -1: left, 1: down, -1: up), or null if out of bounds.
     */
    public GPoint reflect(double x, double y, double w, double h) {
        if (y + h >= bottomY) return null; // Out of bounds
        else return new GPoint((x <= leftX || x + w >= rightX) ? -1 : 1, (y <= topY) ? -1 : 1);
    }
}
