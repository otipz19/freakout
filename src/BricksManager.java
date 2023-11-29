import acm.program.GraphicsProgram;
import java.awt.*;

public class BricksManager {
    private static BricksManager instance;
    private int bricksCount;

    public static BricksManager getInstance(){
        return instance;
    }

    public BricksManager(double x, double y, double brickHorizontalOffset, double brickVerticalOffset, int bricksInRow, int rows, double brickWidth, double brickHeight){
        instance = this;
        this.bricksCount = bricksInRow * rows;
        for(int row = 0; row < rows; row++){
            for(int brickIndex = 0; brickIndex < bricksInRow; brickIndex++){
                double brickX = x + (brickHorizontalOffset + brickWidth) * brickIndex;
                double brickY = y + (brickVerticalOffset + brickHeight) * row;
                Brick brick = new Brick(brickX, brickY, brickWidth, brickHeight, getColor(row));
                Breakout.getInstance().add(brick);
            }
        }
    }

    public void brickDestroyed(){
        bricksCount--;
    }

    public boolean anyBricksPresent(){
        return bricksCount > 0;
    }

    private static Color getColor(int row){
        row %= 5;
        switch (row){
            case 0:
                return Color.RED;
            case 1:
                return Color.ORANGE;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            default:
                return Color.BLUE;
        }
    }
}
