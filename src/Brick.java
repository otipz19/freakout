import java.awt.*;
import acm.graphics.*;

public class Brick extends GCompound {
    private GRect rect;

    public Brick(double x, double y, double width, double height, Color color){
        this.setLocation(x, y);
        rect = new GRect(0, 0, width, height);
        rect.setFillColor(color);
        rect.setColor(color);
        rect.setFilled(true);
        add(rect);
    }
}
