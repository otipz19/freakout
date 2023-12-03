import acm.program.GraphicsProgram;

public class FirstLevel extends Level {
    public FirstLevel(int width, int height) {
        super(width, height);
        paddleWidth = 200;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 10;
        bricksRows = 1;
        bricksGap = 4;
        brickHeight = 10;
        ballRadius = 10;
        brickYOffset = 70;
        lives = 3;
        brickWidth = (width - (bricksPerRow - 1) * bricksGap) / bricksPerRow;
    }

    @Override
    public void setup() {
        bricksManager = new BricksManager(0, height / 10 + brickYOffset, bricksGap, bricksGap, bricksPerRow, bricksRows, brickWidth, brickHeight);
        healthBar = new HealthBar(0, 0, width / 4, height / 10, lives);
        scoreBoard = new ScoreBoard(3 * width / 4, 0, width / 4, height / 10);
        container = new BoxContainer(0, height / 10, width, height);
        ball = new BreakerBall(10, 10, 200, 200, ballRadius * 2, ballRadius * 2);
        paddle = new Paddle(paddleWidth, paddleHeight);
        paddle.setLocation( (width - paddleWidth) / 2, height - paddleYOffset);
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
