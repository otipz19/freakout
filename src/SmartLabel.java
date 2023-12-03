import acm.graphics.*;

/**
 * A custom label class with adaptive font size to fit within a specified block.
 */
public class SmartLabel extends GLabel {

    /** The base font size for the label. */
    private static final int BASE_FONT_SIZE = 44;

    /** The x-coordinate of the label's position. */
    private double x;

    /** The y-coordinate of the label's position. */
    private double y;

    /** The width of the block that the label should fit in. */
    private double width;

    /** The height of the block that the label should fit in. */
    private double height;

    /** The font family and style to be used for the label. */
    private final String font;

    /**
     * Constructs a SmartLabel with specified coordinates, block dimensions, text, and font.
     *
     * @param x      The x-coordinate of the label's position.
     * @param y      The y-coordinate of the label's position.
     * @param width  The width of the block that the label should fit in.
     * @param height The height of the block that the label should fit in.
     * @param text   The text content of the label.
     * @param font   The font family and style to be used for the label.
     */
    public SmartLabel(double x, double y, double width, double height, String text, String font) {
        super(text);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.font = font;
        setLabel(text);
    }

    /**
     * Overrides the setLabel method to adjust the font size and center the label within the block.
     *
     * @param text The new text content for the label.
     */
    @Override
    public void setLabel(String text) {
        super.setLabel(text);
        matchFontSizeWithBlockBounds();
        centerLabel();
    }

    /**
     * Centers the label within the specified block.
     */
    private void centerLabel() {
        GRectangle bounds = this.getBounds();
        double blockCenterX = x + width / 2;
        double blockCenterY = y + height / 2;
        double labelX = blockCenterX - bounds.getWidth() / 2;
        double labelY = blockCenterY + bounds.getHeight() / 4;
        this.setLocation(labelX, labelY);
    }

    /**
     * Adjusts the font size to fit the label within the specified block.
     */
    private void matchFontSizeWithBlockBounds() {
        int fontSize = BASE_FONT_SIZE;
        setLabelFontSize(fontSize);
        while (this.getBounds().getWidth() >= width || this.getBounds().getHeight() >= height) {
            fontSize--;
            setLabelFontSize(fontSize);
        }
    }

    /**
     * Sets the font size of the label.
     *
     * @param size The new font size for the label.
     */
    private void setLabelFontSize(int size) {
        this.setFont(font + size);
    }
}

