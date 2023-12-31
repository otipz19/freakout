import acm.program.GraphicsProgram;

public class ThirdLevel extends Level {
    public ThirdLevel(int width, int height) {
        super(width, height);
        paddleWidth = 100;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 10;
        bricksRows = 6;
        ballRadius = 10;
        brickYOffset = 70;
        lives = 3;
        palette = new ThirdPalette();
        createBricksManager();
        bricksManager.setRows(bricksRows);
        bricksManager.setBricksInRow(bricksPerRow);
        bricksManager.addReinforcedBricks(40);
        bricksManager.addUltraBricks(10);
        shouldSpawnEnlargementBonus = true;
        shouldSpawnSpeedBonus = true;
        ballSpeed = 10;
    }

    @Override
    public void setup(){
        super.setup();
        AudioManager.playLevelBackground(2);
    }
}
