import acm.program.GraphicsProgram;

public class SecondLevel extends Level {
    public SecondLevel(int width, int height) {
        super(width, height);
        paddleWidth = 150;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 5;
        bricksRows = 3;
        bricksGap = 6;
        brickHeight = 10;
        ballRadius = 10;
        brickYOffset = 70;
        lives = 3;
        brickWidth = (width - (bricksPerRow - 1) * bricksGap) / bricksPerRow;
        palette = new SecondPalette();
    }

    @Override
    public void setup(){
        super.setup();
        AudioManager.playLevelBackground(1);
    }
}
