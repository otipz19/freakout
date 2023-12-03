import java.awt.*;

public class UltraBrick extends BaseBrick {
    private static final Color ultraColor = Color.CYAN;
    private final Color plainColor;
    double x;
    double y;
    double width;
    double height;
    Color color;

    public UltraBrick(double x_, double y_, double width_, double height_, Color color_) {
        super(x_, y_, ULTRA_BRICK_SCORE, ULTRA_BRICK_LIVES, width_, height_, ultraColor);
        x = x_;
        y = y_;
        width = width_;
        height = height_;
        color = color_;
        this.plainColor = color_;
    }

    @Override
    protected void onLiveDecrease() {
        if (lives == 0) {
            try {
                wait(1000);
            } catch (Exception e) {

            }
            BricksManager man = Breakout.getLevel().bricksManager;
            Breakout.addObject(new ReinforcedBrick(x, y, width, height, color));
            for (int row = 0; row < 3; row++) {
                for (int brickIndex = 0; brickIndex < 3; brickIndex++) {
                    double brickX = x - width - man.HORIZ_OFF + (man.HORIZ_OFF + width) * brickIndex;
                    double brickY = y - man.VERT_OFF - height + (man.VERT_OFF + height) * row;
                    ColorPalette palette = Breakout.getLevel().getPalette();
                    BaseBrick brick;
                    brick = new SimpleBrick(brickX, brickY, width, height, palette.getBrickColor(row));
                    man.incrementBricksCount(1);
                    Breakout.addObject(brick);
                }
            }
        }
    }
}