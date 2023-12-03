import acm.graphics.GCompound;

public class BaseBrick extends GCompound implements ICollidable {
    protected static final int SIMPLE_BRICK_SCORE = 5;
    protected static final int SIMPLE_BRICK_LIVES = 1;

    private int score;
    private int lives;

    public BaseBrick(double x, double y, int score, int lives) {
        this.setLocation(x, y);
        this.score = score;
        this.lives = lives;
    }

    public void onCollision(ICollidable other) {
        if (other instanceof BreakerBall) {
            lives--;
            if(lives <= 0){
                Breakout.getLevel().addScore(score);
                BricksManager.getInstance().brickDestroyed();
                Breakout.removeObject(this);
            }
        }
    }
}
