import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

public class FirstLevel extends Level {
    public FirstLevel(int width, int height) {
        super(width, height);
        paddleWidth = 100;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 10;
        bricksRows = 2;
        ballRadius = 10;
        brickYOffset = 70;
        lives = 3;
        palette = ColorPalette.FIRST_PALETTE;
        createBricksManager();
        bricksManager.setRows(bricksRows);
        bricksManager.setBricksInRow(bricksPerRow);
        shouldSpawnEnlargementBonus = false;
        shouldSpawnSpeedBonus = false;
    }

    @Override
    public void setup(){
        super.setup();
        AudioManager.playLevelBackground(0);
    }
}
