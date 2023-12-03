import java.awt.*;

public class ReinforcedBrick extends BaseBrick{
    private static final Color reinforcedColor = Color.RED;
    private final Color plainColor;

    public ReinforcedBrick(double x, double y, double width, double height, Color color) {
        super(x, y, SIMPLE_BRICK_SCORE, SIMPLE_BRICK_LIVES, width, height, reinforcedColor);
        this.plainColor = color;
    }

    @Override
    protected void onLiveDecrease() {
        if(lives > 0){
            changeColor(plainColor);
        }
    }
}
