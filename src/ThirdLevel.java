import acm.program.GraphicsProgram;

public class ThirdLevel extends Level {
    public ThirdLevel(int width, int height) {
        super(width, height);
        paddleWidth = 150;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 10;
        bricksRows = 5;
        bricksGap = 6;
        brickHeight = 10;
        ballRadius = 10;
        brickYOffset = 70;
        lives = 3;
        brickWidth = (width - (bricksPerRow - 1) * bricksGap) / bricksPerRow;
        palette = new ThirdPalette();
    }

    @Override
    public void setup(){
        super.setup();
        AudioManager.playLevelBackground(2);
    }
}
