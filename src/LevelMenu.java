import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class LevelMenu extends BaseMenu{
    private Button firstBtn;
    private Button secondBtn;
    private Button thirdBtn;

    public LevelMenu(double width, double height){
        super(width, height);
    }

    @Override
    public void setup() {
        drawBackground(ColorPalette.FIRST_PALETTE.background);
        firstBtn = drawBtn(ySection * 3, "LEVEL 1", ColorPalette.FIRST_PALETTE);
        secondBtn = drawBtn(ySection * 7, "LEVEL 2", ColorPalette.SECOND_PALETTE);
        thirdBtn = drawBtn(ySection * 11, "LEVEL 3", ColorPalette.THIRD_PALETTE);
        AudioManager.playMenuBackground();
    }

    @Override
    public boolean isStarted() {
        return firstBtn != null && secondBtn != null && thirdBtn != null;
    }

    public void mouseClicked(GObject object){
        if(object == firstBtn){
            isClicked = true;
            Breakout.setActiveScene(SceneType.FIRST_LEVEL);
        }
        else if(object == secondBtn){
            isClicked = true;
            Breakout.setActiveScene(SceneType.SECOND_LEVEL);
        }
        else if(object == thirdBtn){
            isClicked = true;
            Breakout.setActiveScene(SceneType.THIRD_LEVEL);
        }
    }

    private Button drawBtn(double y, String text, ColorPalette palette){
        double x = xSection * 3;
        double width = xSection * 4;
        double height = ySection * 2;
        Button btn = new Button(x, y, width, height, text, FONT);
        btn.setTextColor(ColorPalette.LIGHT_GRAY);
        btn.setBackgroundColor(palette.getPaddle());
        Breakout.addObject(btn);
        return btn;
    }
}
