import acm.graphics.GObject;

import java.awt.event.MouseEvent;

public interface IScene {
    public void setup();
    public void update();
    public boolean isStarted();
    public boolean isEnded();
    public void mouseClicked(GObject object);
    public void mouseMoved(MouseEvent e);
}
