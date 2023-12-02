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
        brickWidth = (width - (bricksPerRow - 1) * bricksGap) / bricksPerRow;
    }

    @Override
    public void setup(){
        lives = 5;
        bricksManager = new BricksManager(0, brickYOffset, bricksGap, bricksGap, bricksPerRow, bricksRows, brickWidth, brickHeight);
        container = new BoxContainer(0, 0, width, height);
        ball = new BreakerBall(10, 10, 200, 200, ballRadius * 2, ballRadius * 2);
        paddle = new Paddle(paddleWidth, paddleHeight);
        program.add(ball);
        program.add(paddle, (width - paddleWidth) / 2, height - paddleYOffset);
        super.setup();
    }

    @Override
    public void update(){
        if(isEnded()){
            end();
        } else {
            ball.update();
        }
    }

    @Override
    public void end() {
        super.end();
    }
}
