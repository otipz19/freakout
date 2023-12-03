import java.awt.*;

import acm.graphics.*;

public class SimpleBrick extends BaseBrick {
    private GRect rect;

    public SimpleBrick(double x, double y, double width, double height, Color color) {
        super(x, y, SIMPLE_BRICK_SCORE, SIMPLE_BRICK_LIVES);
        rect = new GRect(0, 0, width, height);
        rect.setColor(color);
        rect.setFillColor(color);
        rect.setFilled(true);
        add(rect);
    }

}
