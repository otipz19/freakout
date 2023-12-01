/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.program.*;

import java.awt.event.*;

public class Breakout extends GraphicsProgram {
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 900;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 5;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 20;
	private static final int DELTA_TIME = 25;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 20;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	private Paddle paddle;
	private int paddleWidth = 200;
	private int paddleHeight = 100;
	private int paddleY = HEIGHT*5/6;
	private String paddleImage = "images/paddle.jpg";

	private static GraphicsProgram instance;

	public static GraphicsProgram getInstance(){
		return instance;
	}

	public void init(){
		instance = this;
	}

	public void run() {
		// other change
		//lolololool
		BricksManager bricksManager = new BricksManager(0, 0, BRICK_SEP,  BRICK_SEP, NBRICKS_PER_ROW, NBRICK_ROWS, BRICK_WIDTH, BRICK_HEIGHT);
		BoxContainer cont = new BoxContainer(0, 0, APPLICATION_WIDTH, APPLICATION_HEIGHT);
		BreakerBall ball = new BreakerBall(10, 10, 200, 200, BALL_RADIUS * 2, BALL_RADIUS * 2);
		add(ball);
		paddle = new Paddle(paddleImage, paddleWidth, paddleHeight);
		add(paddle, (WIDTH - paddleWidth) / 2, paddleY);
		addMouseListeners();
		while (bricksManager.anyBricksPresent()) {
			ball.update();
			pause(DELTA_TIME);
		}
	}

	public void mouseMoved(MouseEvent e){
		int x;
		if ((e.getX()+paddleWidth/2) > WIDTH)
			x = WIDTH - paddleWidth;
		else if ((e.getX()-paddleWidth/2) < 0)
			x = 0;
		else
			x = e.getX()-paddleWidth/2;
		paddle.setLocation(x, paddleY);
	}
}
