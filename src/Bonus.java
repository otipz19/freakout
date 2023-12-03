import acm.graphics.GCompound;
import acm.graphics.GObject;


public abstract class Bonus extends GCompound {
    private double AccelerationX = 0;
    private double AccelerationY = 0;
    private double VelocityX;
    private double VelocityY;
    private double PositionX;
    private double PositionY;
    private double Width;
    private double Height;
    private boolean isActive;
    abstract void move();
    abstract void collideWithActivator();
    abstract void use();
}
