import acm.graphics.GObject;

import java.awt.event.MouseEvent;

public abstract class Level implements IScene{
    /** Dimensions of the paddle */
    protected double paddleWidth;
    protected double paddleHeight;

    /** Offset of the paddle up from the bottom */
    protected double paddleYOffset;

    /** Number of bricks per row */
    protected int bricksPerRow;

    /** Number of rows of bricks */
    protected int bricksRows;

    /** Separation between bricks */
    protected double bricksGap;

    /** Height of a brick */
    protected double brickHeight;

    /** Radius of the ball in pixels */
    protected double ballRadius;

    /** Offset of the top brick row from the top */
    protected double brickYOffset;
    protected double brickWidth;
    protected double width;
    protected double height;

    protected Paddle paddle;
    protected BreakerBall ball;
    protected BricksManager bricksManager;
    protected BoxContainer container;
    protected int lives;
    protected boolean isStarted;
    protected boolean isEnded;
    protected HealthBar healthBar;
    protected ScoreBoard scoreBoard;

    protected int score;
    public EnlagementBonus firstEnlargementBonus = null;
    public SpeedBonus firstSpeedBonus = null;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setup(){
        isStarted = true;
    }

    public abstract void update();

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isEnded() {
        if(bricksManager == null){
            return false;
        }
        isEnded = isEnded || !bricksManager.anyBricksPresent() || lives <= 0;
        if(isEnded){
            end();
        }
        return isEnded;
    }

    public boolean isWon(){
        return isEnded && !bricksManager.anyBricksPresent();
    }

    public void decrementLife(){
        lives--;
        healthBar.decrementLife();
        if(lives <= 0){
            end();
        }
    }

    public void mouseMoved(MouseEvent e) {
        double x;
        if ((e.getX() + paddleWidth / 2) > width)
            x = width - paddleWidth;
        else if ((e.getX() - paddleWidth / 2) < 0)
            x = 0;
        else
            x = e.getX() - paddleWidth / 2;
        paddle.setLocation(x, paddle.getY());
    }

    public void addScore(int score){
        this.score += score;
        scoreBoard.setScore(this.score);
    }

    public void mouseClicked(GObject object){
        ball.setActive(true);
    }

    protected void end(){
        Breakout.setLastGameResult(new GameResult(isWon(), score));
        Breakout.setActiveScene(SceneType.RESTART_MENU);
    }
}
