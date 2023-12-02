import java.awt.event.MouseEvent;

public interface IScene {
    public void setup();
    public void update();
    public boolean isStarted();
    public boolean isEnded();
    public void mouseClicked(MouseEvent e);
    public void mouseMoved(MouseEvent e);
}
