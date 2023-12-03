import java.awt.*;

import acm.graphics.*;

public class SimpleBrick extends BaseBrick {
    private GImage image;

    public SimpleBrick(double x, double y, double width, double height, Color color) {
        super(x, y, SIMPLE_BRICK_SCORE, SIMPLE_BRICK_LIVES);
        image = new GImage(0, 0, width, height);
        image.setFillColor(color);
        image.setColor(color);
        image.setFilled(true);
        add(image);
    }

}
