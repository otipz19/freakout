import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class FirstLevel extends Level {
    public FirstLevel(int width, int height) {
        super(width, height);
        paddleWidth = 100;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 10;
        bricksRows = 6;
        bricksGap = 4;
        brickHeight = 20;
        ballRadius = 10;
        brickYOffset = 70;
        lives = 3;
        brickWidth = (width - (bricksPerRow - 1) * bricksGap) / bricksPerRow;
        palette = ColorPalette.FIRST_PALETTE;
    }

    @Override
    public void setup(){
        super.setup();
        AudioManager.playLevelBackground(0);
    }
}
