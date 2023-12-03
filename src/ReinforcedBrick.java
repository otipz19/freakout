import java.awt.*;

public class ReinforcedBrick extends BaseBrick{
    private static final Color reinforcedColor = Color.BLACK;
    private final Color plainColor;

    public ReinforcedBrick(double x, double y, double width, double height, Color color) {
        super(x, y, REINFORCED_BRICK_SCORE, REINFORCED_BRICK_LIVES, width, height, reinforcedColor);
        this.plainColor = color;
    }

    @Override
    protected void onLiveDecrease() {
        if(lives > 0){
            changeColor(plainColor);
        }
    }
}
