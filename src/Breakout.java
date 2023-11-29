import acm.program.*;

import java.awt.event.*;

public class Breakout extends GraphicsProgram {
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 900;
	private static final int DELTA_TIME = 25;

	private static GraphicsProgram instance;

	private FirstLevel level;

	public static GraphicsProgram getInstance(){
		return instance;
	}

	public void init(){
		instance = this;
		addMouseListeners();
	}

	public void run() {
		level = new FirstLevel(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		level.start();
		while (!level.isEnded()) {
			level.update();
			pause(DELTA_TIME);
		}
	}

	public void mouseMoved(MouseEvent e){
		if(level != null && level.isStarted() && !level.isEnded()){
			level.mouseMoved(e);
		}
	}
}
