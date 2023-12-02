import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class LevelMenu {
    private static final String font = "comicsans-24";

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
        firstBtn = drawBtn(ySection * 3, "First level");
        secondBtn = drawBtn(ySection * 7, "Second level");
        thirdBtn = drawBtn(ySection * 11, "Third level");
    }

    public boolean isClicked(){
        return isClicked;
    }

    public void mouseClicked(MouseEvent e){
        GObject object = program.getElementAt(e.getX(), e.getY());
        if(object != null){
            if(object == firstBtn){
                Breakout.setLevelType(LevelType.FIRST);
                isClicked = true;
            }
            else if(object == secondBtn){
                Breakout.setLevelType(LevelType.SECOND);
                isClicked = true;
            }
            else if(object == thirdBtn){
                Breakout.setLevelType(LevelType.THIRD);
                isClicked = true;
            }
        }
    }

    private Button drawBtn(double y, String text){
        double x = xSection * 3;
        double width = xSection * 4;
        double height = ySection * 2;
        Button btn = new Button(x, y, width, height, text, font);
        program.add(btn);
        return btn;
    }
}
