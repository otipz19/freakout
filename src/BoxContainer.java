import acm.graphics.GPoint;

public class BoxContainer {
    public static BoxContainer getContainer() {
        return Container;
    }

    static private BoxContainer Container;
    private float TopX;
    private float TopY;
    private float BottomX;
    private float BottomY;
    /**
     * creates a basic box container(xTop, yTop,xBottom,yBottom)
     */
    BoxContainer(float x1, float y1, float x2, float y2){
        TopX=x1;
        TopY=y1;
        BottomX=x2;
        BottomY=y2;
        Container = this;
    }
    public GPoint reflect(float x, float y, float w, float h){
        return(new GPoint((x<=TopX||x+w>=BottomX)?-1:1,(y<=TopY||y+h>=BottomY)?-1:1));
    }
}
