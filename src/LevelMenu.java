import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class LevelMenu implements IScene{
    private static final String FONT = "comicsans-";

    private final GraphicsProgram program = Breakout.getInstance();

    private double ySection;
    private double xSection;

    private Button firstBtn;
    private Button secondBtn;
    private Button thirdBtn;

    private boolean isClicked;

    public LevelMenu(double width, double height){
        xSection = width / 10;
        ySection = height / 16;
    }

    @Override
    public void setup() {
        program.removeAll();
        firstBtn = drawBtn(ySection * 3, "First level");
        secondBtn = drawBtn(ySection * 7, "Second level");
        thirdBtn = drawBtn(ySection * 11, "Third level");
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isStarted() {
        return firstBtn != null && secondBtn != null && thirdBtn != null;
    }

    @Override
    public boolean isEnded() {
        return isClicked;
    }

    public void mouseClicked(MouseEvent e){
        GObject object = program.getElementAt(e.getX(), e.getY());
        if(object != null){
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
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private Button drawBtn(double y, String text){
        double x = xSection * 3;
        double width = xSection * 4;
        double height = ySection * 2;
        Button btn = new Button(x, y, width, height, text, FONT);
        program.add(btn);
        return btn;
    }
}
