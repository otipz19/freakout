import java.awt.*;

import acm.graphics.*;
import acm.util.RandomGenerator;

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
        RandomGenerator r = new RandomGenerator();
        switch (r.nextInt(2)){
            case 0:
                if(Breakout.getLevel().firstEnlargementBonus == null){
                    EnlagementBonus a = new EnlagementBonus(getX(),getY());
                    Breakout.getLevel().firstEnlargementBonus = a;
                    Breakout.addObject(a);
                }
                break;
            case 1:
                if(Breakout.getLevel().firstSpeedBonus == null){
                    SpeedBonus a = new SpeedBonus(getX(),getY());
                    Breakout.getLevel().firstSpeedBonus = a;
                    Breakout.addObject(a);
                }
                break;
        }
        Breakout.removeObject(this);
    }
}
