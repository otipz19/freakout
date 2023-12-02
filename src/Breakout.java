import acm.program.*;

import java.awt.event.*;

public class Breakout extends GraphicsProgram {
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 900;
	private static final int DELTA_TIME = 10;

	private static Breakout instance;

	private static Level level;

	public static Breakout getInstance(){
		return instance;
	}

	private static RestartMenu restartMenu;
	private static LevelMenu levelMenu;
	private static LevelType lastLevelType = LevelType.FIRST;

	public static Level getLevel(){
		return level;
	}

	public static LevelType getLastLevelType(){
		return lastLevelType;
	}

	public static void setLevelType(LevelType levelType){
		switch (levelType){
			case FIRST:
				level = new FirstLevel(APPLICATION_WIDTH, APPLICATION_HEIGHT);
				break;
			case SECOND:
				//TODO
				break;
			case THIRD:
				//TODO
				break;
		}
		lastLevelType = levelType;
	}

	public void init(){
		instance = this;
		addMouseListeners();
	}

	public void run() {
		drawLevelMenu();
		while(!levelMenu.isClicked()){
			pause(100);
		}
		while(true){
			if(level != null){
				playLevel(level);
				drawRestartMenu();
				while(!restartMenu.isClicked()){
					pause(100);
				}
			}
		}
	}

	private void drawRestartMenu() {
		removeAll();
		restartMenu = new RestartMenu(APPLICATION_WIDTH, APPLICATION_HEIGHT, "Breakout", "Restart");
	}

	public static void drawLevelMenu() {
		instance.removeAll();
		levelMenu = new LevelMenu(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	private void playLevel(Level level){
		removeAll();
		level.setup();
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
	public void mouseClicked(MouseEvent e){
		if(level != null && level.isStarted() && !level.isEnded()){
			level.mouseClicked(e);
		}
		else if(restartMenu != null){
			restartMenu.mouseClicked(e);
		}
		else if(levelMenu != null){
			levelMenu.mouseClicked(e);
		}
	}
}
