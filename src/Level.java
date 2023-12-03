import acm.graphics.*;

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

    /** Radius of the ball in pixels */
    protected double ballRadius;

    /** Offset of the top brick row from the top */
    protected double brickYOffset;
    protected double width;
    protected double height;

    protected Paddle paddle;
    public BreakerBall ball;
    protected BricksManager bricksManager;
    protected BoxContainer container;
    protected int lives;
    protected boolean isStarted;
    protected boolean isEnded;
    protected HealthBar healthBar;
    protected ScoreBoard scoreBoard;
    protected ColorPalette palette;
    protected int score;
    public EnlargementBonus firstEnlargementBonus = null;
    public SpeedBonus firstSpeedBonus = null;

    protected boolean shouldSpawnEnlargementBonus;
    protected boolean shouldSpawnSpeedBonus;

    private GRect background;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setup(){
        drawBackground();
        healthBar = new HealthBar(0, 0, width / 2, height / 10, lives);
        scoreBoard = new ScoreBoard(3 * width / 4, 0, width / 4, height / 10);
        container = new BoxContainer(0, height / 10, width, height);
        ball = new BreakerBall(10, width / 2, height / 2, ballRadius * 2, ballRadius * 2);
        paddle = new Paddle((width - paddleWidth) / 2, height - paddleYOffset, paddleWidth, paddleHeight, palette.getPaddle());
        bricksManager.spawn();
        Breakout.addObject(ball);
        Breakout.addObject(paddle);
        Breakout.addObject(healthBar);
        Breakout.addObject(scoreBoard);
        isStarted = true;
    }

    public boolean isShouldSpawnEnlargementBonus() {
        return shouldSpawnEnlargementBonus;
    }

    public boolean isShouldSpawnSpeedBonus() {
        return shouldSpawnSpeedBonus;
    }

    public void update() {
        if (isStarted && !isEnded()) {
            ball.update();
            if(firstEnlargementBonus!=null){
                firstEnlargementBonus.update();
            }
            if(firstSpeedBonus!=null){
                firstSpeedBonus.update();
            }
        } else if (isStarted && isEnded) {
            end();
        }
    }

    public ColorPalette getPalette(){
        return palette;
    }

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

    protected void drawBackground(){
        background = new GRect(0, 0, width, height);
        background.setColor(palette.getBackground());
        background.setFillColor(palette.getBackground());
        background.setFilled(true);
        Breakout.addObject(background);
    }

    protected void createBricksManager() {
        bricksManager = new BricksManager(0, height / 10 + brickYOffset, width);
    }
}
