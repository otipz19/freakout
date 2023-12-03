import acm.util.RandomGenerator;

/**
 * The BricksManager class manages the creation and handling of bricks in the Breakout game.
 */
public class BricksManager {

    private static final RandomGenerator rng = RandomGenerator.getInstance(); // Random number generator instance

    public static final double HORIZ_OFF = 4; // Horizontal offset between bricks
    public static final double VERT_OFF = 4; // Vertical offset between bricks
    public static final double brickHeight = 20; // Height of a brick

    private static BricksManager instance; // Singleton instance of BricksManager

    private final double x; // X-coordinate of the starting position of bricks
    private final double y; // Y-coordinate of the starting position of bricks
    public final double width; // Width of the brick manager area

    private double brickWidth; // Width of a brick
    private int rows = 2; // Number of rows of bricks
    private int bricksInRow = 10; // Number of bricks in a row
    private int bricksCount; // Total count of bricks
    private boolean shouldSpawnReinforced; // Flag indicating whether reinforced bricks should be spawned
    private int reinforcedSpawnChance; // Spawn chance for reinforced bricks
    private boolean shouldSpawnUltra; // Flag indicating whether ultra bricks should be spawned
    private int ultraSpawnChance; // Spawn chance for ultra bricks

    /**
     * Gets the singleton instance of BricksManager.
     *
     * @return The singleton instance of BricksManager.
     */
    public static BricksManager getInstance() {
        return instance;
    }

    /**
     * Constructor for the BricksManager.
     *
     * @param x     The X-coordinate of the starting position of bricks.
     * @param y     The Y-coordinate of the starting position of bricks.
     * @param width The width of the brick manager area.
     */
    public BricksManager(double x, double y, double width) {
        instance = this;
        this.x = x;
        this.y = y;
        this.width = width - HORIZ_OFF * 2;
        recalculateBricks();
    }

    /**
     * Sets the number of rows of bricks.
     *
     * @param rows The number of rows to set.
     */
    public void setRows(int rows) {
        this.rows = rows;
        recalculateBricks();
    }

    /**
     * Sets the number of bricks in a row.
     *
     * @param bricksInRow The number of bricks in a row to set.
     */
    public void setBricksInRow(int bricksInRow) {
        this.bricksInRow = bricksInRow;
        recalculateBricks();
    }

    /**
     * Adds the possibility of spawning reinforced bricks with a specified spawn chance.
     *
     * @param spawnChance The spawn chance for reinforced bricks.
     */
    public void addReinforcedBricks(int spawnChance) {
        shouldSpawnReinforced = true;
        reinforcedSpawnChance = spawnChance;
    }

    /**
     * Adds the possibility of spawning ultra bricks with a specified spawn chance.
     *
     * @param spawnChance The spawn chance for ultra bricks.
     */
    public void addUltraBricks(int spawnChance) {
        shouldSpawnUltra = true;
        ultraSpawnChance = spawnChance;
    }

    /**
     * Increments the count of bricks by the specified amount.
     *
     * @param i The amount to increment the bricks count.
     */
    public void incrementBricksCount(int i) {
        bricksCount += i;
    }

    /**
     * Spawns the bricks based on the specified configuration.
     */
    public void spawn() {
        for (int row = 0; row < rows; row++) {
            double yOffset = (row / 2) * brickHeight;
            for (int brickIndex = 0; brickIndex < bricksInRow; brickIndex++) {
                double brickX = x + (HORIZ_OFF + brickWidth) * brickIndex;
                double brickY = y + (VERT_OFF + brickHeight) * row + yOffset;
                ColorPalette palette = Breakout.getLevel().getPalette();
                BaseBrick brick;
                int i = rng.nextInt(0, 100);
                if (shouldSpawnUltra && i < ultraSpawnChance) {
                    brick = new UltraBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                } else if (shouldSpawnReinforced && i < reinforcedSpawnChance) {
                    brick = new ReinforcedBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                } else {
                    brick = new SimpleBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                }
                Breakout.addObject(brick);
            }
        }
    }

    /**
     * Handles the destruction of a brick.
     */
    public void brickDestroyed() {
        bricksCount--;
    }

    /**
     * Checks if there are any bricks present.
     *
     * @return True if there are any bricks present, false otherwise.
     */
    public boolean anyBricksPresent() {
        return bricksCount > 0;
    }

    /**
     * Recalculates the brick parameters based on the current configuration.
     */
    private void recalculateBricks() {
        this.bricksCount = bricksInRow * rows;
        this.brickWidth = (width - (bricksInRow - 1) * HORIZ_OFF) / bricksInRow;
    }
}
