import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

public class Paddle extends GCompound {
    private static final String PADDLE_IMAGE = "images/paddle.jpg";
    private GImage body;

    private int width,height;
    public Paddle(int width, int height) {
        body = new GImage(PADDLE_IMAGE);
        this.body.setSize(width, height);
        add(this.body);
    }

}
