import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.awt.*;

public class EnlargementBonus extends Bonus{
    private double AccelerationX = 0;
    private double AccelerationY = 0;
    private double VelocityX = 0;
    private double VelocityY = 3;
    private double PositionX;
    private double PositionY;
    private double Width =20;
    private double Height=20;
    private boolean isActive;

    /**
     * Basic constructor.(PosX,PosY)
     */
    EnlargementBonus(double Px, double Py) {
        PositionX = Px;
        PositionY = Py;
        setLocation(PositionX, PositionY);
        isActive = true;
        construct();
    }
    private void construct() {
        GRect ou = new GRect(0, 0, Width, Height);
        ou.setFilled(false);
        ou.setColor(Color.RED);
        add(ou);
        GRect in = new GRect(5, 5, 10, 10);
        in.setFilled(true);
        in.setFillColor(Color.RED);
        add(in);
    }
    public void update() {
        if(isActive) {
            move();
            collideWithActivator();
            move(VelocityX, VelocityY);
            checkIfOutOfBounds();
        }
    }
    @Override
    void collideWithActivator() {
        Paddle P = Paddle.getInstance();
        if(P != null && (P.getX()<PositionX+Width&&P.getX()+P.getWidth()>PositionX)&&(P.getY()<PositionY+Height&&P.getY()+P.getHeight()>PositionY)){
            use();
            Breakout.removeObject(this);
            Breakout.getLevel().firstEnlargementBonus=null;
        }
    }

    @Override
    void move() {
        VelocityX += AccelerationX;
        VelocityY += AccelerationY;
        PositionX += VelocityX;
        PositionY += VelocityY;
    }

    @Override
    void use() {
        Paddle P = Paddle.getInstance();
        RandomGenerator r = new RandomGenerator();
        P.scale(r.nextDouble(1,1.1),1);
    }
    @Override
    void checkIfOutOfBounds() {
        if(PositionY>Breakout.getLevel().height){
            Breakout.removeObject(Breakout.getLevel().firstEnlargementBonus);
            Breakout.getLevel().firstEnlargementBonus=null;
        }
    }
}
