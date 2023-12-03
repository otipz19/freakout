import java.awt.*;

public class ColorPalette {
    public static final Color LIGHT_GRAY = new Color(0xf9f9f9);

    public static final ColorPalette FIRST_PALETTE = new FirstPalette();
    public static final ColorPalette SECOND_PALETTE = new SecondPalette();
    public static final ColorPalette THIRD_PALETTE = new ThirdPalette();

    protected Color background;
    protected Color paddle;
    protected Color ball;
    protected Color brick0;
    protected Color brick1;
    protected Color brick2;
    protected Color brick3;
    protected Color brick4;
    protected Color brick5;

    public Color getBackground() {
        return background;
    }

    public Color getPaddle() {
        return paddle;
    }

    public Color getBall() {
        return ball;
    }

    public Color getBrickColor(int index){
        switch (index){
            case 0:
                return brick0;
            case 1:
                return brick1;
            case 2:
                return brick2;
            case 3:
                return brick3;
            case 4:
                return brick4;
            default:
                return brick5;
        }
    }
}
