import acm.program.GraphicsProgram;

public class SecondLevel extends Level {
    public SecondLevel(int width, int height) {
        super(width, height);
        paddleWidth = 150;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 10;
        bricksRows = 4;
        ballRadius = 10;
        brickYOffset = 70;
        lives = 3;
        palette = new SecondPalette();
        createBricksManager();
        bricksManager.setRows(bricksRows);
        bricksManager.setBricksInRow(bricksPerRow);
        bricksManager.addReinforcedBricks(20);
        shouldSpawnEnlargementBonus = true;
        shouldSpawnSpeedBonus = false;
        ballSpeed = 10;
    }

    @Override
    public void setup(){
        super.setup();
        AudioManager.playLevelBackground(1);
    }
}
