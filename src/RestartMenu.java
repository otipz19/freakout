import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class RestartMenu extends BaseMenu {
    private static final String LEVEL_MENU_BTN_LABEL = "Levels";
    public static final String RESTART_BTN_LABEL = "Restart";
    private GLabel resultLabel;
    private Button restartBtn;
    private Button levelMenuBtn;
    private final String labelText;

    public RestartMenu(double width, double height, String labelText){
        super(width, height);
        this.labelText = labelText;
    }

    @Override
    public void setup() {
        program.removeAll();
        drawResultLabel();
        drawRestartBtn();
        drawLevelMenuBtn();
    }

    @Override
    public boolean isStarted() {
        return restartBtn != null && levelMenuBtn != null && resultLabel != null;
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

    private void drawResultLabel(){
        double width = xSection * 4;
        double height = ySection * 2;
        double x = (this.width - width) / 2;
        double y = ySection * 3;
        resultLabel = new SmartLabel(x, y, width, height, labelText, FONT);
        program.add(resultLabel);
    }

    private void drawLevelMenuBtn() {
        double width = resultLabel.getWidth();
        double height = ySection * 2;
        double x = this.width / 2 + width / 2;
        double y = ySection * 8;
        levelMenuBtn = new Button(x, y, width, height, LEVEL_MENU_BTN_LABEL, FONT);
        program.add(levelMenuBtn);
    }

    private void drawRestartBtn() {
        double width = resultLabel.getWidth();
        double height = ySection * 2;
        double x = this.width / 2 - 3 * width / 2;
        double y = ySection * 8;
        restartBtn = new Button(x, y, width, height, RESTART_BTN_LABEL, FONT);
        program.add(restartBtn);
    }
}
