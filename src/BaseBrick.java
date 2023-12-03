import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.Color;

public abstract class BaseBrick extends GCompound implements ICollidable {
    private static final RandomGenerator rng = RandomGenerator.getInstance();

    protected static final int SIMPLE_BRICK_SCORE = 5;
    protected static final int SIMPLE_BRICK_LIVES = 1;
    protected static final int REINFORCED_BRICK_SCORE = 20;
    protected static final int REINFORCED_BRICK_LIVES = 2;

    private final int score;
    protected int lives;
    private final GRect rect;

    public BaseBrick(double x, double y, int score, int lives, double width, double height, Color color) {
        this.setLocation(x, y);
        this.score = score;
        this.lives = lives;
        rect = new GRect(0, 0, width, height);
        rect.setColor(color);
        rect.setFillColor(color);
        rect.setFilled(true);
        add(rect);
    }

    public void onCollision(ICollidable other) {
        if (other instanceof BreakerBall) {
            lives--;
            onLiveDecrease();
            if(lives <= 0){
                Breakout.getLevel().addScore(score);
                BricksManager.getInstance().brickDestroyed();
                switch (rng.nextInt(2)){
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
                AudioManager.playBrickBreak();
            }
        }
    }

    protected abstract void onLiveDecrease();

    protected void changeColor(Color color){
        rect.setColor(color);
        rect.setFillColor(color);
    }
}
