import java.awt.*;

import acm.graphics.*;

public class Brick extends GCompound implements ICollidable{
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

    public void onCollision(ICollidable other) {
        if (other instanceof BreakerBall) {
            Breakout.getLevel().addScore(score);
            BricksManager.getInstance().brickDestroyed();
            if (Breakout.getLevel().firstEnlargementBonus == null) {
                EnlagementBonus a = new EnlagementBonus(getX(), getY());
                Breakout.getLevel().firstEnlargementBonus = a;
                Breakout.addObject(a);

            }
            Breakout.removeObject(this);
        }
    }
}
