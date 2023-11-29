public class FirstLevel extends Level {
    public FirstLevel(int width, int height){
        super(width, height);
        paddleWidth = 200;
        paddleHeight = 50;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 10;
        bricksRows = 1;
        bricksGap = 4;
        brickHeight = 20;
        ballRadius = 20;
        brickYOffset = 70;
    }

    @Override
    public void start(){
        bricksManager = new BricksManager(0, brickYOffset, bricksGap, bricksGap, bricksPerRow, bricksRows, brickWidth, brickHeight);
        container = new BoxContainer(0, 0, width, height);
        ball = new BreakerBall(10, 10, 200, 200, ballRadius * 2, ballRadius * 2);
        paddle = new Paddle(paddleWidth, paddleHeight);
        Breakout.getInstance().add(ball);
        Breakout.getInstance().add(paddle, (width - paddleWidth) / 2, height - paddleYOffset);
        isStarted = true;
    }

    @Override
    public void update(){
        ball.update();
    }
}
