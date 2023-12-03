/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 *
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.GObject;
import acm.program.*;

import java.awt.event.*;

/**
 * The Breakout class implements the game of Breakout using the ACM library.
 */
public class Breakout extends GraphicsProgram {

    /**
     * Width of the application window in pixels.
     */
    public static final int APPLICATION_WIDTH = 600;

    /**
     * Height of the application window in pixels.
     */
    public static final int APPLICATION_HEIGHT = 900;

    /**
     * Time delay between frames in milliseconds.
     */
    private static final int DELTA_TIME = 10;

    private static Breakout instance; // Singleton instance of the Breakout class

    private static IScene scene; // Current game scene

    private static GameResult lastGameResult; // Result of the last game played
    private static GameResult bestGameResult = new GameResult(false, ScoreSerializer.getBestScore());
    private static SceneType lastLevelType = SceneType.FIRST_LEVEL; // Type of the last played level

    /**
     * Gets the best game result achieved.
     *
     * @return The best game result.
     */
    public static GameResult getBestGameResult() {
        return bestGameResult;
    }

    /**
     * Gets the result of the last game played.
     *
     * @return The result of the last game.
     */
    public static GameResult getLastGameResult() {
        return lastGameResult;
    }

    /**
     * Sets the result of the last game played and updates the best game result if needed.
     *
     * @param lastGameResult The result of the last game played.
     */
    public static void setLastGameResult(GameResult lastGameResult) {
        Breakout.lastGameResult = lastGameResult;
        if (bestGameResult == null || bestGameResult.getScore() < lastGameResult.getScore()) {
            bestGameResult = lastGameResult;
            ScoreSerializer.setBestScore(bestGameResult.getScore());
        }
    }

    /**
     * Gets the current game level.
     *
     * @return The current game level, or null if the current scene is not a level.
     */
    public static Level getLevel() {
        if (scene instanceof Level) {
            return (Level) scene;
        }
        return null;
    }

    /**
     * Gets the type of the last played level.
     *
     * @return The type of the last played level.
     */
    public static SceneType getLastLevelType() {
        return lastLevelType;
    }

    /**
     * Adds a graphical object to the canvas.
     *
     * @param object The graphical object to be added.
     */
    public static void addObject(GObject object) {
        instance.add(object);
    }

    /**
     * Clears all objects from the canvas.
     */
    public static void clearCanvas() {
        instance.removeAll();
    }

    /**
     * Gets the graphical object at the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The graphical object at the specified coordinates.
     */
    public static GObject getObjectAt(double x, double y) {
        return instance.getElementAt(x, y);
    }

    /**
     * Removes a graphical object from the canvas.
     *
     * @param object The graphical object to be removed.
     */
    public static void removeObject(GObject object) {
        instance.remove(object);
    }

    /**
     * Sets the active game scene based on the specified scene type.
     *
     * @param sceneType The type of the scene to be set.
     */
    public static void setActiveScene(SceneType sceneType) {
        switch (sceneType) {
            // Creates a new instance of the corresponding scene type
            case START_MENU:
                scene = new StartMenu(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                break;
            case LEVEL_MENU:
                scene = new LevelMenu(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                break;
            case RESTART_MENU:
                String label = lastGameResult.isWon() ? "YOU WON!" : "YOU LOST...";
                scene = new RestartMenu(APPLICATION_WIDTH, APPLICATION_HEIGHT, label);
                break;
            case FIRST_LEVEL:
                scene = new FirstLevel(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                lastLevelType = sceneType;
                break;
            case SECOND_LEVEL:
                scene = new SecondLevel(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                lastLevelType = sceneType;
                break;
            case THIRD_LEVEL:
                scene = new ThirdLevel(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                lastLevelType = sceneType;
                break;
        }
    }

    /**
     * Initializes the Breakout instance by setting itself as the singleton instance and adding mouse listeners.
     */
    public void init() {
        instance = this;
        addMouseListeners();
    }

    /**
     * Runs the Breakout game by setting the active scene to the start menu and continuously playing scenes.
     */
    public void run() {
        setActiveScene(SceneType.START_MENU);
        while (true) {
            playScene();
        }
    }

    private void playScene() {
        removeAll();
        IScene scene = Breakout.scene;
        scene.setup();
        while (!scene.isEnded()) {
            scene.update();
            pause(DELTA_TIME);
        }
    }

    /**
     * Handles mouse movement events in the game.
     *
     * @param e The mouse event.
     */
    public void mouseMoved(MouseEvent e) {
        if (scene != null && scene.isStarted() && !scene.isEnded()) {
            scene.mouseMoved(e);
        }
    }

    /**
     * Handles mouse click events in the game.
     *
     * @param e The mouse event.
     */
    public void mouseClicked(MouseEvent e) {
        if (scene != null && scene.isStarted() && !scene.isEnded()) {
            GObject object = getElementAt(e.getX(), e.getY());
            scene.mouseClicked(object);
        }
    }
}