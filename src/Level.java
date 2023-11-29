import java.awt.event.MouseEvent;

public abstract class Level {
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
    protected int bricksGap;

    /** Height of a brick */
    protected int brickHeight;

    /** Radius of the ball in pixels */
    protected int ballRadius;

    /** Offset of the top brick row from the top */
    protected int brickYOffset;
    protected int brickWidth;
    protected int width;
    protected int height;

    protected Paddle paddle;
    protected BreakerBall ball;
    protected BricksManager bricksManager;
    protected BoxContainer container;
    protected boolean isStarted;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.brickWidth = (width - (bricksPerRow - 1) * bricksGap) / bricksPerRow;
    }

    public abstract void start();

    public abstract void update();

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isEnded() {
        return !bricksManager.anyBricksPresent();
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
}
