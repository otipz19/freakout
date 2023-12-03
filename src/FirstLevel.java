/**
 * The FirstLevel class represents the first level of the game.
 */
public class FirstLevel extends Level {
    /**
     * Creates a new FirstLevel with the specified dimensions.
     *
     * @param width  The width of the level.
     * @param height The height of the level.
     */
    public FirstLevel(int width, int height) {
        super(width, height);
        paddleWidth = 200;
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
        ballSpeed = 8;
    }

    /**
     * Sets up the initial state of the first level, including creating graphical elements.
     */
    @Override
    public void setup() {
        super.setup();
        AudioManager.playLevelBackground(0);
    }
}
