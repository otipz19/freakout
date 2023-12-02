import acm.graphics.*;

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
        Breakout.addObject(title);
        playBtn = new Button(xSection * 3, ySection * 7, xSection * 4, ySection * 2, "Play", FONT);
        Breakout.addObject(playBtn);
        authors = new SmartLabel(xSection * 2, ySection * 13, xSection * 6, ySection * 2, "Made by Alex, Orest and Max", FONT);
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
