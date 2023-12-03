import acm.graphics.GCompound;
import acm.graphics.GImage;

/**
 * The HealthBar class represents the health bar that displays the player's remaining lives.
 */
public class HealthBar extends GCompound {
    private GImage firstHeart;
    private GImage secondHeart;
    private GImage thirdHeart;

    private int currentHealth;
    private double heartSize;
    private double heartGap;
    private double height;

    /**
     * Creates a new HealthBar with the specified position, size, and initial health.
     */
    public HealthBar(double x, double y, double width, double height, int currentHealth) {
        this.setLocation(x, y);
        this.currentHealth = currentHealth;
        this.height = height;
        heartSize = width / 4;
        heartGap = width / 16;
        drawHearts();
    }

    /**
     * Decrements the player's life and updates the displayed hearts accordingly.
     */
    public void decrementLife() {
        currentHealth--;
        drawHearts();
    }

    private void drawHearts() {
        firstHeart = drawHeart(0);
        secondHeart = drawHeart(1);
        thirdHeart = drawHeart(2);
    }

    private GImage drawHeart(int index) {
        GImage heart = new GImage(currentHealth >= index + 1 ? Images.HEART_FULL : Images.HEART_EMPTY);
        heart.setSize(heartSize, heartSize);
        heart.setLocation(heartGap * (index + 1) + heartSize * index, (height - heartSize) / 2);
        add(heart);
        return heart;
    }
}
