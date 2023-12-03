import acm.graphics.GRect;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The BaseMenu class is an abstract class representing the basic properties and behavior of menus in the Breakout game.
 * It implements the IScene interface.
 */
public abstract class BaseMenu implements IScene {

    protected static final String FONT = "comicsans-";

    protected double width; // Width of the menu
    protected double height; // Height of the menu
    protected double ySection; // Vertical section height of the menu
    protected double xSection; // Horizontal section width of the menu
    protected boolean isClicked; // Flag indicating whether the menu is clicked or not

    /**
     * Constructor to create a BaseMenu instance.
     *
     * @param width  The width of the menu.
     * @param height The height of the menu.
     */
    public BaseMenu(double width, double height) {
        this.width = width;
        this.height = height;
        this.ySection = height / 16;
        this.xSection = width / 10;
    }

    /**
     * Checks if the menu is ended (clicked).
     *
     * @return True if the menu is clicked, false otherwise.
     */
    public boolean isEnded() {
        return isClicked;
    }

    @Override
    public void update() {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Draws the background of the menu with the specified color.
     *
     * @param color The color of the menu background.
     */
    protected void drawBackground(Color color) {
        GRect background = new GRect(width, height);
        background.setColor(color);
        background.setFillColor(color);
        background.setFilled(true);
        Breakout.addObject(background);
    }
}