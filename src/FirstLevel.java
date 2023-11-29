import java.awt.event.MouseEvent;

public class FirstLevel {
    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 200;
    private static final int PADDLE_HEIGHT = 50;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = PADDLE_HEIGHT * 3;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 5;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 20;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 20;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    private Paddle paddle;
    private BreakerBall ball;
    private BricksManager bricksManager;
    private BoxContainer container;

    /** Dimensions of game board */
    private int width;
    private int height;

    private int brickWidth;
    private boolean isStarted;

    public FirstLevel(int width, int height){
        this.width = width;
        this.height = height;
        this.brickWidth = (width - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
    }

    public void start(){
        bricksManager = new BricksManager(0, BRICK_Y_OFFSET, BRICK_SEP,  BRICK_SEP, NBRICKS_PER_ROW, NBRICK_ROWS, brickWidth, BRICK_HEIGHT);
        container = new BoxContainer(0, 0, width, height);
        ball = new BreakerBall(10, 10, 200, 200, BALL_RADIUS * 2, BALL_RADIUS * 2);
        paddle = new Paddle(PADDLE_WIDTH, PADDLE_HEIGHT);
        Breakout.getInstance().add(ball);
        Breakout.getInstance().add(paddle, (width - PADDLE_WIDTH) / 2, height - PADDLE_Y_OFFSET);
        isStarted = true;
    }

    public void update(){
        ball.update();
    }

    public boolean isStarted(){
        return isStarted;
    }

    public boolean isEnded(){
        return !bricksManager.anyBricksPresent();
    }

    public void mouseMoved(MouseEvent e){
        int x;
        if ((e.getX()+PADDLE_WIDTH/2) > width)
            x = width - PADDLE_WIDTH;
        else if ((e.getX()-PADDLE_WIDTH/2) < 0)
            x = 0;
        else
            x = e.getX()-PADDLE_WIDTH/2;
        paddle.setLocation(x, paddle.getY());
    }
}
