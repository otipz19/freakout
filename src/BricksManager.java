import acm.util.RandomGenerator;

import java.awt.*;

public class BricksManager {
    private static final RandomGenerator rng = RandomGenerator.getInstance();
    private static BricksManager instance;
    private int bricksCount;
    public void incrementBricksCount(int i){
        bricksCount += i;
    }
    public double HORIZ_OFF;
    public double VERT_OFF;

    public static BricksManager getInstance(){
        return instance;
    }

    public BricksManager(double x, double y, double brickHorizontalOffset, double brickVerticalOffset, int bricksInRow, int rows, double brickWidth, double brickHeight){
        HORIZ_OFF = brickHorizontalOffset;
        VERT_OFF = brickVerticalOffset;
        instance = this;
        this.bricksCount = bricksInRow * rows;
        for(int row = 0; row < rows; row++){
            for(int brickIndex = 0; brickIndex < bricksInRow; brickIndex++){
                double brickX = x + (brickHorizontalOffset + brickWidth) * brickIndex;
                double brickY = y + (brickVerticalOffset + brickHeight) * row;
                ColorPalette palette = Breakout.getLevel().getPalette();
                BaseBrick brick;
                int i = rng.nextInt(0, 100);
                if(i>15){
                    brick = new SimpleBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                }
                else if(i>5){
                    brick = new ReinforcedBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                }
                else{
                    brick = new UltraBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
                }
                //brick = new UltraBrick(brickX, brickY, brickWidth, brickHeight, palette.getBrickColor(row));
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
}
