import acm.program.GraphicsProgram;

public class FirstLevel extends Level {
    public FirstLevel(int width, int height){
        super(width, height);
        paddleWidth = 200;
        paddleHeight = 20;
        paddleYOffset = paddleHeight * 3;
        bricksPerRow = 2;
        bricksRows = 1;
        bricksGap = 4;
        brickHeight = 20;
        ballRadius = 20;
        brickYOffset = 70;
        lives = 3;
        brickWidth = (width - (bricksPerRow - 1) * bricksGap) / bricksPerRow;
    }

    @Override
    public void setup(){
        bricksManager = new BricksManager(0, height / 10 + brickYOffset, bricksGap, bricksGap, bricksPerRow, bricksRows, brickWidth, brickHeight);
        healthBar = new HealthBar(0, 0, width / 4, height / 10, lives);
        container = new BoxContainer(0, height / 10, width, height);
        ball = new BreakerBall(10, 10, 200, 200, ballRadius * 2, ballRadius * 2);
        paddle = new Paddle(paddleWidth, paddleHeight);
        program.add(ball);
        program.add(paddle, (width - paddleWidth) / 2, height - paddleYOffset);
        program.add(healthBar);
        super.setup();
    }

    @Override
    public void update(){
        if(isStarted && !isEnded()){
            ball.update();
        }
        else if(isStarted && isEnded){
            end();
        }
    }
}
