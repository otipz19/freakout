import acm.graphics.GObject;

import java.awt.event.MouseEvent;

/**
 * The IScene interface represents a scene in the game and defines methods for setup, update, and input handling.
 */
public interface IScene {
    /**
     * Performs initial setup for the scene.
     */
    public void setup();

    /**
     * Updates the state of the scene.
     */
    public void update();

    /**
     * Checks if the scene has started.
     *
     * @return true if the scene has started, false otherwise.
     */
    public boolean isStarted();

    /**
     * Checks if the scene has ended.
     *
     * @return true if the scene has ended, false otherwise.
     */
    public boolean isEnded();

    /**
     * Handles mouse click events in the scene.
     *
     * @param object The GObject that was clicked.
     */
    public void mouseClicked(GObject object);

    /**
     * Handles mouse movement events in the scene.
     *
     * @param e The MouseEvent representing the mouse movement.
     */
    public void mouseMoved(MouseEvent e);
}
