import acm.program.*;

import java.awt.event.*;

public class Breakout extends GraphicsProgram {
/** Width and height of application window in pixels */
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 900;
	private static final int DELTA_TIME = 25;

	private static Breakout instance;

	private static Level level;
	public static Level getLevel(){ return level;}

	private boolean isReady;
	private static int lives;

	public static Breakout getInstance(){
		return instance;
	}

	public void init(){
		isReady = false;
		instance = this;
		addMouseListeners();
	}

	public void run() {
		while(true) {
			if (isReady) {
				level = new FirstLevel(WIDTH, HEIGHT);
				playLevel(level);
				isReady = false;
				break;
			}
		}
		while(true) {
			if (isReady) {
				level = new SecondLevel(WIDTH, HEIGHT);
				playLevel(level);
				isReady = false;
				break;
			}
		}
		while(true) {
			if (isReady) {
				level = new ThirdLevel(WIDTH, HEIGHT);
				playLevel(level);
				isReady = false;
				break;
			}
		}

	}

	public void playLevel(Level level){
		level.setup();
		while (level.update() == true) {
			pause(DELTA_TIME);
		}
	}

	public void mouseMoved(MouseEvent e){
		if(level != null && level.isStarted() && !level.isEnded()){
			level.mouseMoved(e);
		}
	}
	public void mouseClicked(MouseEvent e){
		if(level != null && level.isStarted() && !level.isEnded()){
			level.mouseClicked(e);
		}
		isReady = true;
	}
}
