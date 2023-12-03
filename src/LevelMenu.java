import acm.graphics.GObject;

/**
 * The LevelMenu class represents a menu for selecting game levels.
 */
public class LevelMenu extends BaseMenu {
    private Button firstBtn;
    private Button secondBtn;
    private Button thirdBtn;

    /**
     * Creates a new LevelMenu with the specified width and height.
     *
     * @param width  The width of the menu.
     * @param height The height of the menu.
     */
    public LevelMenu(double width, double height) {
        super(width, height);
    }

    @Override
    public void setup() {
        drawBackground(ColorPalette.FIRST_PALETTE.background);
        firstBtn = drawBtn(ySection * 3, "LEVEL 1", ColorPalette.FIRST_PALETTE);
        secondBtn = drawBtn(ySection * 7, "LEVEL 2", ColorPalette.SECOND_PALETTE);
        thirdBtn = drawBtn(ySection * 11, "LEVEL 3", ColorPalette.THIRD_PALETTE);
        AudioManager.playMenuBackground();
    }

    @Override
    public boolean isStarted() {
        return firstBtn != null && secondBtn != null && thirdBtn != null;
    }

    /**
     * Handles mouse click events in the LevelMenu.
     *
     * @param object The GObject that was clicked.
     */
    @Override
    public void mouseClicked(GObject object) {
        if (object == firstBtn) {
            isClicked = true;
            Breakout.setActiveScene(SceneType.FIRST_LEVEL);
        } else if (object == secondBtn) {
            isClicked = true;
            Breakout.setActiveScene(SceneType.SECOND_LEVEL);
        } else if (object == thirdBtn) {
            isClicked = true;
            Breakout.setActiveScene(SceneType.THIRD_LEVEL);
        }
    }

    /**
     * Draws a button on the LevelMenu with the specified parameters.
     *
     * @param y       The y-coordinate of the button.
     * @param text    The text to display on the button.
     * @param palette The color palette to use for the button.
     * @return The created Button object.
     */
    private Button drawBtn(double y, String text, ColorPalette palette) {
        double x = xSection * 3;
        double width = xSection * 4;
        double height = ySection * 2;
        Button btn = new Button(x, y, width, height, text, FONT);
        btn.setTextColor(ColorPalette.LIGHT_GRAY);
        btn.setBackgroundColor(palette.getPaddle());
        Breakout.addObject(btn);
        return btn;
    }
}
