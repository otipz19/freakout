import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import com.sun.imageio.plugins.gif.GIFImageReader;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BaseMenu implements IScene {
    protected static final String FONT = "comicsans-";

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

    protected void drawBackground(Color color){
        GRect background = new GRect(width, height);
        background.setColor(color);
        background.setFillColor(color);
        background.setFilled(true);
        Breakout.addObject(background);
    }
}
