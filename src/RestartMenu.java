import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class RestartMenu implements IScene{
    private static final String font = "comicsans-24";
    private final GraphicsProgram program = Breakout.getInstance();
    private GLabel resultLabel;
    private Button restartBtn;
    private Button levelMenuBtn;

    private double width;
    private double height;
    private String labelText;
    private String playBtnText;
    private double ySection;

    private boolean isClicked;

    public RestartMenu(double width, double height, String labelText, String playBtnText){
        this.width = width;
        this.height = height;
        this.labelText = labelText;
        this.playBtnText = playBtnText;
        this.ySection = height / 16;
    }

    @Override
    public void setup() {
        program.removeAll();
        drawResultLabel();
        drawRestartBtn();
        drawLevelMenuBtn();
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isStarted() {
        return restartBtn != null && levelMenuBtn != null && resultLabel != null;
    }

    @Override
    public boolean isEnded() {
        return isClicked;
    }

    public void mouseClicked(MouseEvent e){
        GObject object = program.getElementAt(e.getX(), e.getY());
        if (object != null){
            if(object == restartBtn){
                isClicked = true;
                Breakout.setActiveScene(Breakout.getLastLevelType());
            }
            else if(object == levelMenuBtn){
                isClicked = true;
                Breakout.setActiveScene(SceneType.LEVEL_MENU);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void drawResultLabel(){
        resultLabel = new GLabel(labelText);
        resultLabel.setFont(font);
        double x = (width - resultLabel.getWidth()) / 2;
        double y = ySection * 3;
        resultLabel.setLocation(x, y);
        program.add(resultLabel);
    }

    private void drawLevelMenuBtn() {
        double width = resultLabel.getWidth();
        double height = ySection * 2;
        double x = this.width / 2 + width / 2;
        double y = ySection * 8;
        levelMenuBtn = new Button(x, y, width, height, "Levels", font);
        program.add(levelMenuBtn);
    }

    private void drawRestartBtn() {
        double width = resultLabel.getWidth();
        double height = ySection * 2;
        double x = this.width / 2 - 3 * width / 2;
        double y = ySection * 8;
        restartBtn = new Button(x, y, width, height, playBtnText, font);
        program.add(restartBtn);
    }
}