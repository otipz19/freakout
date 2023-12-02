import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public abstract class BaseMenu implements IScene {
    protected static final String FONT = "comicsans-";
    protected final GraphicsProgram program = Breakout.getInstance();
    protected double width;
    protected double height;
    protected double ySection;
    protected double xSection;
    protected boolean isClicked;

    public BaseMenu(double width, double height) {
        this.width = width;
        this.height = height;
        this.ySection = height / 16;
        this.xSection = width / 10;
    }

    public boolean isEnded() {
        return isClicked;
    }

    @Override
    public void update() {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
