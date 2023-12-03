import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

import java.awt.*;

public class Paddle extends GCompound implements ICollidable{
    private GRect rect;

    static private Paddle Padd;
    public static Paddle getPaddle() {
        return Padd;
    }

    private int width,height;
    public Paddle(double x, double y, double width, double height, Color color) {
        this.setLocation(x, y);
        rect = new GRect(0, 0, width, height);
        rect.setColor(color);
        rect.setFillColor(color);
        rect.setFilled(true);
        add(rect);
    }

    @Override
    public void onCollision(ICollidable other) {

    }
}
