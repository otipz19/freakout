import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class RestartMenu extends BaseMenu {
    private static final String LEVEL_MENU_BTN_LABEL = "LEVELS";
    public static final String RESTART_BTN_LABEL = "RESTART";
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
        drawBackground(ColorPalette.FIRST_PALETTE.getBackground());
        drawResultLabel();
        drawRestartBtn();
        drawLevelMenuBtn();
    }

    @Override
    public boolean isStarted() {
        return restartBtn != null && levelMenuBtn != null && resultLabel != null;
    }

    public void mouseClicked(GObject object){
        if(object == restartBtn){
            isClicked = true;
            Breakout.setActiveScene(Breakout.getLastLevelType());
        }
        else if(object == levelMenuBtn){
            isClicked = true;
            Breakout.setActiveScene(SceneType.LEVEL_MENU);
        }
    }

    private void drawResultLabel(){
        double width = xSection * 4;
        double height = ySection * 2;
        double x = (this.width - width) / 2;
        double y = ySection * 3;
        resultLabel = new SmartLabel(x, y, width, height, labelText, FONT);
        resultLabel.setColor(ColorPalette.LIGHT_GRAY);
        Breakout.addObject(resultLabel);
    }

    private void drawLevelMenuBtn() {
        levelMenuBtn = drawBtn(xSection * 6, LEVEL_MENU_BTN_LABEL);
    }

    private void drawRestartBtn() {
        restartBtn = drawBtn(xSection, RESTART_BTN_LABEL);
    }

    private Button drawBtn(double x, String text) {
        double width = xSection * 3;
        double height = ySection * 2;
        double y = ySection * 8;
        Button btn = new Button(x, y, width, height, text, FONT);
        Breakout.addObject(btn);
        btn.setTextColor(ColorPalette.LIGHT_GRAY);
        btn.setBackgroundColor(ColorPalette.FIRST_PALETTE.getPaddle());
        return btn;
    }
}
