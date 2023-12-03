import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.awt.*;

public class SpeedBonus extends Bonus{
    private double AccelerationX = 0;
    private double AccelerationY = 0.1;
    private double VelocityX = 0;
    private double VelocityY = 0;
    private double PositionX;
    private double PositionY;
    private double Width =20;
    private double Height=20;
    private boolean isActive;

    /**
     * Basic constructor.(PosX,PosY)
     */
    SpeedBonus(double Px, double Py) {
        PositionX = Px;
        PositionY = Py;
        setLocation(PositionX, PositionY);
        isActive = true;
        construct();
    }
    private void construct() {
        GRect ou = new GRect(0, 0, Width, Height);
        ou.setFilled(false);
        ou.setColor(Color.GREEN);
        add(ou);
        GRect in = new GRect(5, 5, 10, 10);
        in.setColor(Color.GREEN);
        in.setFilled(true);
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
            Breakout.getLevel().firstSpeedBonus=null;
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
        BreakerBall B = BreakerBall.getBall();
        RandomGenerator r = new RandomGenerator();
        B.setVelocity(B.getVelocityX()*r.nextDouble(0.8,1.2),B.getVelocityY()*r.nextDouble(0.8,1.2));
    }

    @Override
    void checkIfOutOfBounds() {
        if(PositionY>Breakout.getLevel().height){
            Breakout.removeObject(Breakout.getLevel().firstSpeedBonus);
            Breakout.getLevel().firstSpeedBonus=null;
        }
    }
}
