import acm.graphics.*;

public class Button extends GCompound {
    private GRect rect;
    private GLabel label;

    public Button(double x, double y, double width, double height, String text, String font){
        this.setLocation(x, y);
        rect = new GRect(0, 0, width, height);
        add(rect);
        label = new GLabel(text);
        label.setFont(font);
        label.setLocation((width - label.getWidth()) / 2, label.getHeight() * 2);
        add(label);
    }
}
