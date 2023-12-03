import acm.util.MediaTools;
import java.applet.AudioClip;

public class AudioManager {
    public static final AudioClip bounce = MediaTools.loadAudioClip("sounds/bounce.au");
    public static final AudioClip brickBreak = MediaTools.loadAudioClip("sounds/brick-break.au");
    private static final AudioClip menuBack = MediaTools.loadAudioClip("sounds/background-menu.au");
    private static final AudioClip firstBack = MediaTools.loadAudioClip("sounds/background-first-level.au");
    private static final AudioClip secondBack = MediaTools.loadAudioClip("sounds/background-second-level.au");
    private static final AudioClip thirdBack = MediaTools.loadAudioClip("sounds/background-third-level.au");
    private static final AudioClip lose = MediaTools.loadAudioClip("sounds/lose.au");
    private static final AudioClip win = MediaTools.loadAudioClip("sounds/win.au");

    private static boolean isMenuPlaying;

    public static void playMenuBackground(){
        if(!isMenuPlaying){
            stopAllBackground(false);
            isMenuPlaying = true;
            menuBack.loop();
        }
    }

    public static void playLevelBackground(int index){
        stopAllBackground();
        switch (index){
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

    public static void playWin(){
        stopAllBackground();
        win.play();
    }

    public static void playLose(){
        stopAllBackground();
        lose.play();
    }

    public static void playBounce(){
        bounce.play();
    }

    public static void playBrickBreak() {
        brickBreak.play();
    }

    private static void stopAllBackground(){
        stopAllBackground(true);
    }

    private static void stopAllBackground(boolean stopMenu){
        if(stopMenu){
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
