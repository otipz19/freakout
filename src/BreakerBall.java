import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GPoint;

import java.security.PrivateKey;

public class BreakerBall extends GCompound {
    private float AccelerationX = 0;
    private float AccelerationY = 0;
    private float VelocityX;
    private float VelocityY;
    private float PositionX;
    private float PositionY;
    private float Width;
    private float Height;
    /**
     * Full constructor.(AccelerationX,AccelerationY,VelocityX,VelocityY,PosX,PosY,Width,Height
     */
    BreakerBall(float Ax,float Ay,float Vx,float Vy,float Px,float Py,float W,float H){
        AccelerationX=Ax;
        AccelerationY=Ay;
        VelocityX=Vx;
        VelocityY=Vy;
        PositionX=Px;
        PositionY=Py;
        Width=W;
        Height=H;
        construct();
    }
    /**
     * Basic constructor.(VelocityX,VelocityY,PosX,PosY,Width,Height
     */
    BreakerBall(float Vx,float Vy,float Px,float Py,float W,float H){
        VelocityX=Vx;
        VelocityY=Vy;
        PositionX=Px;
        PositionY=Py;
        setLocation(PositionX,PositionY);
        Width=W;
        Height=H;
        construct();
    }

    /**
     * Updates speeds and positions of self
     */
    private void updateStatesNoCollision(){
        VelocityX+=AccelerationX;
        VelocityY+=AccelerationY;
        PositionX+=VelocityX;
        PositionY+=VelocityY;
    }
    private void collideWithContainer(){
        GPoint ReflectVec = BoxContainer.getContainer().reflect(PositionX,PositionY,Width,Height);
        VelocityX*=ReflectVec.getX();
        VelocityY*=ReflectVec.getY();
    }
    private void construct(){
        GOval ov = new GOval(0,0,Width,Height);
        ov.setFilled(true);
        add(ov);
    }
    public void update() {
        updateStatesNoCollision();
        collideWithContainer();
        move(VelocityX,VelocityY);
    }
}
