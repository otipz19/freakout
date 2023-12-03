import acm.program.GraphicsProgram;

public class ThirdLevel extends Level {
    public ThirdLevel(int width, int height) {
        super(width, height);
        paddleWidth = 150;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 5;
        bricksRows = 3;
        bricksGap = 6;
        brickHeight = 10;
        ballRadius = 10;
        brickYOffset = 70;
        lives = 3;
        brickWidth = (width - (bricksPerRow - 1) * bricksGap) / bricksPerRow;
        palette = new ThirdPalette();
    }

    @Override
    public void setup() {
        drawBackground();
        bricksManager = new BricksManager(0, height / 10 + brickYOffset, bricksGap, bricksGap, bricksPerRow, bricksRows, brickWidth, brickHeight);
        healthBar = new HealthBar(0, 0, width / 4, height / 10, lives);
        scoreBoard = new ScoreBoard(3 * width / 4, 0, width / 4, height / 10);
        container = new BoxContainer(0, height / 10, width, height);
        ball = new BreakerBall(10, 200, 200, ballRadius * 2, ballRadius * 2);
        paddle = new Paddle((width - paddleWidth) / 2, height - paddleYOffset, paddleWidth, paddleHeight, palette.getPaddle());
        Breakout.addObject(ball);
        Breakout.addObject(paddle);
        Breakout.addObject(healthBar);
        Breakout.addObject(scoreBoard);
        super.setup();
    }

    @Override
    public void update() {
        if (isStarted && !isEnded()) {
            ball.update();
        } else if (isStarted && isEnded) {
            end();
        }
    }
}
