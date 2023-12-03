import acm.graphics.*;

public class SmartLabel extends GLabel {
    private static final int BASE_FONT_SIZE = 44;

    private double x;
    private double y;
    private double width;
    private double height;
    private final String font;

    public SmartLabel(double x, double y, double width, double height, String text, String font) {
        super(text);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.font = font;
        setLabel(text);
    }

    @Override
    public void setLabel(String text){
        super.setLabel(text);
        matchFontSizeWithBlockBounds();
        centerLabel();
    }

    private void centerLabel() {
        GRectangle bounds = this.getBounds();
        double blockCenterX = x + width / 2;
        double blockCenterY = y + height / 2;
        double labelX = blockCenterX - bounds.getWidth() / 2;
        double labelY = blockCenterY + bounds.getHeight() / 4;
        this.setLocation(labelX, labelY);
    }

    private void matchFontSizeWithBlockBounds() {
        int fontSize = BASE_FONT_SIZE;
        setLabelFontSize(fontSize);
        while(this.getBounds().getWidth() >= width || this.getBounds().getHeight() >= height){
            fontSize--;
            setLabelFontSize(fontSize);
        }
    }

    private void setLabelFontSize(int size){
        this.setFont(font + size);
    }
}
