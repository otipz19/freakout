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
	private static final int DELTA_TIME = 10;

	private static Breakout instance;

	private static IScene scene;

	private static GameResult lastGameResult;
	private static GameResult bestGameResult;

	public static void setLastGameResult(GameResult lastGameResult) {
		Breakout.lastGameResult = lastGameResult;
		if(bestGameResult == null || bestGameResult.getScore() < lastGameResult.getScore()){
			bestGameResult = lastGameResult;
		}
	}

	public static Breakout getInstance(){
		return instance;
	}

	private static SceneType lastLevelType = SceneType.FIRST_LEVEL;

	public static Level getLevel(){
		if(scene instanceof Level){
			return (Level) scene;
		}
		return null;
	}

	public static SceneType getLastLevelType(){
		return lastLevelType;
	}

	public static void setActiveScene(SceneType sceneType){
		switch (sceneType){
			case START_MENU:
				scene = new StartMenu(APPLICATION_WIDTH, APPLICATION_HEIGHT);
				break;
			case LEVEL_MENU:
				scene = new LevelMenu(APPLICATION_WIDTH, APPLICATION_HEIGHT);
				break;
			case RESTART_MENU:
				String label = lastGameResult.isWon() ? "YOU WON!" : "YOU LOST...";
				scene = new RestartMenu(APPLICATION_WIDTH, APPLICATION_HEIGHT, label);
				break;
			case FIRST_LEVEL:
				scene = new FirstLevel(APPLICATION_WIDTH, APPLICATION_HEIGHT);
				lastLevelType = sceneType;
				break;
			case SECOND_LEVEL:
				//TODO
				break;
			case THIRD_LEVEL:
				//TODO
				break;
		}
	}

	public void init(){
		instance = this;
		addMouseListeners();
	}

	public void run() {
		setActiveScene(SceneType.START_MENU);
		while(true){
			playScene();
		}
	}

	private void playScene(){
		removeAll();
		IScene scene = this.scene;
		scene.setup();
		while (!scene.isEnded()) {
			scene.update();
			pause(DELTA_TIME);
		}
	}

	public void mouseMoved(MouseEvent e){
		if(scene != null && scene.isStarted() && !scene.isEnded()){
			scene.mouseMoved(e);
		}
	}
	public void mouseClicked(MouseEvent e){
		if(scene != null && scene.isStarted() && !scene.isEnded()){
			scene.mouseClicked(e);
		}
	}
}
