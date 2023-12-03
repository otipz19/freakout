import acm.util.RandomGenerator;

import java.awt.*;

public class BricksManager {
    private static final RandomGenerator rng = RandomGenerator.getInstance();
    public static final double HORIZ_OFF = 4;
    public static final double VERT_OFF = 4;
    private static final double brickHeight = 20;

    private static BricksManager instance;

    private final double x;
    private final double y;
    private final double width;

    private double brickWidth;
    private int rows = 2;
    private int bricksInRow = 10;
    private int bricksCount;
    private boolean shouldSpawnReinforced;
    private int reinforcedSpawnChance;
    private boolean shouldSpawnUltra;
    private int ultraSpawnChance;

    public static BricksManager getInstance(){
        return instance;
    }

    public BricksManager(double x, double y, double width){
        instance = this;
        this.x = x;
        this.y = y;
        this.width = width - HORIZ_OFF * 2;
        recalculateBricks();
    }

    public void setRows(int rows){
        this.rows = rows;
        recalculateBricks();
    }

    public void setBricksInRow(int bricksInRow){
        this.bricksInRow = bricksInRow;
        recalculateBricks();
    }

    public void addReinforcedBricks(int spawnChance){
        shouldSpawnReinforced = true;
        reinforcedSpawnChance = spawnChance;
    }

    public void addUltraBricks(int spawnChance){
        shouldSpawnUltra = true;
        ultraSpawnChance = spawnChance;
    }

    public void incrementBricksCount(int i){
        bricksCount += i;
    }

    public void spawn(){
        for(int row = 0; row < rows; row++){
            double yOffset = (row / 2) * brickHeight;
            for(int brickIndex = 0; brickIndex < bricksInRow; brickIndex++){
                double brickX = x + (HORIZ_OFF + brickWidth) * brickIndex;
                double brickY = y + (VERT_OFF + brickHeight) * row + yOffset;
                ColorPalette palette = Breakout.getLevel().getPalette();
                BaseBrick brick;
                int i = rng.nextInt(0, 100);
                if(shouldSpawnUltra && i < ultraSpawnChance){
                    brick = new UltraBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                }
                else if(shouldSpawnReinforced && i < reinforcedSpawnChance){
                    brick = new ReinforcedBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                }
                else{
                    brick = new SimpleBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                }
                Breakout.addObject(brick);
            }
        }
    }

    public void brickDestroyed(){
        bricksCount--;
    }

    public boolean anyBricksPresent(){
        return bricksCount > 0;
    }

    private void recalculateBricks() {
        this.bricksCount = bricksInRow * rows;
        this.brickWidth = (width - (bricksInRow - 1) * HORIZ_OFF) / bricksInRow;
    }
}
