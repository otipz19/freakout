import acm.graphics.GCompound;

import java.awt.*;

public class ScoreBoard extends GCompound {
    private static final String FONT = "comicsans-";
    private SmartLabel label;
    private double width;
    private double height;

    public ScoreBoard(double x, double y, double width, double height) {
        this.setLocation(x, y);
        this.width = width;
        this.height = height;
        label = new SmartLabel(0, 0, width, height, "0", FONT);
        label.setColor(Color.WHITE);
        setScore(0);
        add(label);
    }

    public void setScore(int score) {
        label.setLabel(Integer.toString(score));
        label.setLocation(width - label.getWidth(), label.getY());
    }
}
