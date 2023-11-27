import acm.program.GraphicsProgram;
import java.awt.*;

public class BrickGenerator {
    public BrickGenerator(double x, double y, double brickHorizontalOffset, double brickVerticalOffset, int bricksInRow, int rows, double brickWidth, double brickHeight, GraphicsProgram program){
        for(int row = 0; row < rows; row++){
            for(int brickIndex = 0; brickIndex < bricksInRow; brickIndex++){
                double brickX = x + (brickHorizontalOffset + brickWidth) * brickIndex;
                double brickY = y + (brickVerticalOffset + brickHeight) * row;
                Brick brick = new Brick(brickX, brickY, brickWidth, brickHeight, getColor(row));
                program.add(brick);
            }
        }
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
