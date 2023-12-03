import acm.graphics.*;

import java.awt.*;

public class StartMenu extends BaseMenu{
    private SmartLabel title;
    private SmartLabel authors;
    private Button playBtn;
    private final ColorPalette palette = ColorPalette.FIRST_PALETTE;

    public StartMenu(double width, double height){
        super(width, height);
    }

    @Override
    public void setup() {
       drawBackground(palette.background);
        title = new SmartLabel(xSection * 3, ySection * 3, xSection * 4, ySection * 2, "BREAKOUT", FONT);
        title.setColor(palette.LIGHT_GRAY);
        Breakout.addObject(title);
        playBtn = new Button(xSection * 3, ySection * 7, xSection * 4, ySection * 2, "Play", FONT);
        playBtn.setTextColor(palette.LIGHT_GRAY);
        playBtn.setBackgroundColor(palette.getPaddle());
        Breakout.addObject(playBtn);
        authors = new SmartLabel(xSection * 2, ySection * 13, xSection * 6, ySection * 2, "Made by Alex, Orest and Max", FONT);
        authors.setColor(palette.LIGHT_GRAY);
        Breakout.addObject(authors);
    }

    @Override
    public boolean isStarted() {
        return playBtn != null;
    }

    @Override
    public void mouseClicked(GObject object) {
        if(object == playBtn){
            isClicked = true;
            Breakout.setActiveScene(SceneType.LEVEL_MENU);
        }
    }
}
