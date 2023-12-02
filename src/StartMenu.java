import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class StartMenu extends BaseMenu{
    private SmartLabel title;
    private SmartLabel authors;
    private Button playBtn;

    public StartMenu(double width, double height){
        super(width, height);
    }

    @Override
    public void setup() {
        title = new SmartLabel(xSection * 3, ySection * 3, xSection * 4, ySection * 2, "BREAKOUT", FONT);
        program.add(title);
        playBtn = new Button(xSection * 3, ySection * 7, xSection * 4, ySection * 2, "Play", FONT);
        program.add(playBtn);
        authors = new SmartLabel(xSection * 2, ySection * 13, xSection * 6, ySection * 2, "Made by Alex, Orest and Max", FONT);
        program.add(authors);
    }

    @Override
    public boolean isStarted() {
        return playBtn != null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GObject object = program.getElementAt(e.getX(), e.getY());
        if(object != null && object == playBtn){
            isClicked = true;
            Breakout.setActiveScene(SceneType.LEVEL_MENU);
        }
    }
}
