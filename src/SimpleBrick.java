import java.awt.*;

import acm.graphics.*;

public class SimpleBrick extends BaseBrick {
    public SimpleBrick(double x, double y, double width, double height, Color color) {
        super(x, y, SIMPLE_BRICK_SCORE, SIMPLE_BRICK_LIVES, width, height, color);
    }

    @Override
    protected void onLiveDecrease() {

    }
}
