import acm.graphics.GLabel;

public class SecondLevel extends Level {
    public SecondLevel(int width, int height){
        super(width, height);
        paddleWidth = 200;
        paddleHeight = 50;
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
    public boolean update(){
        if(isEnded()){
            end();
            return false;
        } else {
            ball.update();
            return true;
        }
    }

    @Override
    public void result() {
        if(haveWon()){
            GLabel result = new GLabel("VICTORY in level 2!!!!");
            result.setFont("-36");
            program.add(result, Breakout.WIDTH/3, Breakout.HEIGHT/3);
        } else {
            GLabel result = new GLabel("LOOOOOSE in level 2!!!!");
            result.setFont("-36");
            program.add(result, Breakout.WIDTH/3, Breakout.HEIGHT/3);
        }
    }

}

