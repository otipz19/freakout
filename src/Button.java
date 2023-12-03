import acm.graphics.*;
import java.awt.*;

/**
 * The Button class represents a graphical button with text.
 */
public class Button extends GCompound {

    private GRect rect; // Rectangle representing the button background
    private SmartLabel label; // SmartLabel for displaying text on the button

    /**
     * Creates a new Button with the specified parameters.
     *
     * @param x      The X-coordinate of the button.
     * @param y      The Y-coordinate of the button.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @param text   The text to be displayed on the button.
     * @param font   The font style for the text.
     */
    public Button(double x, double y, double width, double height, String text, String font) {
        this.setLocation(x, y);
        rect = new GRect(0, 0, width, height);
        add(rect);
        label = new SmartLabel(0, 0, width, height, text, font);
        add(label);
    }

    /**
     * Sets the text color of the button.
     *
     * @param color The color to set for the text.
     */
    public void setTextColor(Color color) {
        label.setColor(color);
    }

    /**
     * Sets the background color of the button.
     *
     * @param color The color to set for the button background.
     */
    public void setBackgroundColor(Color color) {
        rect.setFillColor(color);
        rect.setFilled(true);
    }
}