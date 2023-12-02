import java.awt.*;

import acm.graphics.*;

public class Brick extends GCompound {
    private GRect rect;
    private int score;

    public Brick(double x, double y, double width, double height, Color color, int score) {
        this.setLocation(x, y);
        this.score = score;
        rect = new GRect(0, 0, width, height);
        rect.setFillColor(color);
        rect.setColor(color);
        rect.setFilled(true);
        add(rect);
    }

    public void onCollision() {
        Breakout.getLevel().addScore(score);
        BricksManager.getInstance().brickDestroyed();
        Breakout.removeObject(this);
    }
}
