import acm.graphics.GCompound;
import acm.graphics.GImage;

public class HealthBar extends GCompound {
    private GImage firstHeart;
    private GImage secondHeart;
    private GImage thirdHeart;

    private int currentHealth;
    private double heartWidth;
    private double heartGap;
    private double height;

    public HealthBar(double x, double y, double width, double height, int currentHealth){
        this.setLocation(x, y);
        this.currentHealth = currentHealth;
        this.height = height;
        heartWidth = width / 4;
        heartGap = width / 16;
        drawHearts();
    }


    public void decrementLife(){
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
        heart.setSize(heartWidth, height);
        heart.setLocation(heartGap * (index + 1) + heartWidth * index, 0);
        add(heart);
        return heart;
    }
}
