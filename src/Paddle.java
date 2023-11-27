import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

public class Paddle extends GCompound {
    //private GImage body;
    private GRect body;
    private int width,height;
    public Paddle(String image, int width, int height) {
        //body = new GImage(image);
        body = new GRect(width, height);
        //this.body.setSize(width, height);
        add(this.body);
    }

}
