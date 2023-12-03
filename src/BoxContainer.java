import acm.graphics.GPoint;

public class BoxContainer {
    public static BoxContainer getContainer() {
        return Container;
    }

    static private BoxContainer Container;
    private double leftX;
    private double topY;
    private double rightX;
    private double bottomY;
    public double getLeftX(){return leftX;};
    public double getTopY(){return topY;};
    public double getRightX(){return rightX;};
    public double getBottomY(){return bottomY;};
    public GPoint getCenter(){
        return new GPoint((leftX+rightX)/2, (topY+bottomY)/2);
    }
    public GPoint getRespawnPoint() {
        return new GPoint((leftX + rightX) / 2, (topY + bottomY) / 3);
    }
    /**
     * creates a basic box container(xTop, yTop,xBottom,yBottom)
     */
    BoxContainer(double x1, double y1, double x2, double y2){
        leftX=x1;
        topY=y1;
        rightX=x2;
        bottomY=y2;
        Container = this;
    }
    public GPoint reflect(double x, double y, double w, double h){
        if(y+h>=bottomY) return null;                                    //out of bounds
        else return new GPoint((x<=leftX||x+w>=rightX)?-1:1, (y<=topY)?-1:1);
    }
}
