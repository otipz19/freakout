import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

public class Paddle extends GCompound {
    private static final String PADDLE_IMAGE = "images/paddle.jpg";
    private GImage body;

    static private Paddle Padd;
    public static Paddle getPaddle() {
        return Padd;
    }

    private int width,height;
    public Paddle(double width, double height) {
        body = new GImage(PADDLE_IMAGE);
        this.body.setSize(width, height);
        add(this.body);
        Padd = this;
    }

}
