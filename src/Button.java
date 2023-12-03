import acm.graphics.*;

import java.awt.*;

public class Button extends GCompound {
    private GRect rect;
    private SmartLabel label;

    public Button(double x, double y, double width, double height, String text, String font){
        this.setLocation(x, y);
        rect = new GRect(0, 0, width, height);
        add(rect);
        label = new SmartLabel(0, 0, width, height, text, font);
        add(label);
    }

    public void setTextColor(Color color){
        label.setColor(color);
    }

    public void setBackgroundColor(Color color){
        rect.setFillColor(color);
        rect.setFilled(true);
    }
}
