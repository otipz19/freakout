import acm.util.MediaTools;
import java.applet.AudioClip;

/**
 * The AudioManager class handles the audio functionality for the Breakout game.
 */
public class AudioManager {

    /**
     * AudioClip for the bounce sound effect.
     */
    public static final AudioClip bounce = MediaTools.loadAudioClip("sounds/bounce.au");

    /**
     * AudioClip for the brick break sound effect.
     */
    public static final AudioClip brickBreak = MediaTools.loadAudioClip("sounds/brick-break.au");

    /**
     * AudioClip for the menu background music.
     */
    private static final AudioClip menuBack = MediaTools.loadAudioClip("sounds/background-menu.au");

    /**
     * AudioClip for the background music of the first game level.
     */
    private static final AudioClip firstBack = MediaTools.loadAudioClip("sounds/background-first-level.au");

    /**
     * AudioClip for the background music of the second game level.
     */
    private static final AudioClip secondBack = MediaTools.loadAudioClip("sounds/background-second-level.au");

    /**
     * AudioClip for the background music of the third game level.
     */
    private static final AudioClip thirdBack = MediaTools.loadAudioClip("sounds/background-third-level.au");

    /**
     * AudioClip for the lose sound effect.
     */
    private static final AudioClip lose = MediaTools.loadAudioClip("sounds/lose.au");

    /**
     * AudioClip for the win sound effect.
     */
    private static final AudioClip win = MediaTools.loadAudioClip("sounds/win.au");

    private static boolean isMenuPlaying; // Flag to track whether menu background music is playing or not

    /**
     * Plays the menu background music.
     */
    public static void playMenuBackground() {
        if (!isMenuPlaying) {
            stopAllBackground(false);
            isMenuPlaying = true;
            menuBack.loop();
        }
    }

    /**
     * Plays the background music for the specified level index.
     *
     * @param index The index of the level.
     */
    public static void playLevelBackground(int index) {
        stopAllBackground();
        switch (index) {
            case 0:
                firstBack.loop();
                break;
            case 1:
                secondBack.loop();
                break;
            case 2:
                thirdBack.loop();
                break;
        }
    }

    /**
     * Plays the win sound effect.
     */
    public static void playWin() {
        stopAllBackground();
        win.play();
    }

    /**
     * Plays the lose sound effect.
     */
    public static void playLose() {
        stopAllBackground();
        lose.play();
    }

    /**
     * Plays the bounce sound effect.
     */
    public static void playBounce() {
        bounce.play();
    }

    /**
     * Plays the brick break sound effect.
     */
    public static void playBrickBreak() {
        brickBreak.play();
    }

    /**
     * Stops all background music.
     */
    private static void stopAllBackground() {
        stopAllBackground(true);
    }

    /**
     * Stops all background music, optionally stopping menu background music.
     *
     * @param stopMenu Flag indicating whether to stop menu background music.
     */
    private static void stopAllBackground(boolean stopMenu) {
        if (stopMenu) {
            isMenuPlaying = false;
            menuBack.stop();
        }
        firstBack.stop();
        secondBack.stop();
        thirdBack.stop();
        lose.stop();
        win.stop();
    }
}