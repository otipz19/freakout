import acm.graphics.*;
import java.awt.event.MouseEvent;

/**
 * The Level class represents an abstract game level with common properties and behaviors.
 */
public abstract class Level implements IScene {
    /** Dimensions of the paddle */
    protected double paddleWidth;
    protected double paddleHeight;

    /** Offset of the paddle up from the bottom */
    protected double paddleYOffset;

    /** Number of bricks per row */
    protected int bricksPerRow;

    /** Number of rows of bricks */
    protected int bricksRows;

    /** Radius of the ball in pixels */
    protected double ballRadius;

    /** Offset of the top brick row from the top */
    protected double brickYOffset;
    protected double width;
    protected double height;

    protected Paddle paddle;
    public BreakerBall ball;
    protected BricksManager bricksManager;
    protected BoxContainer container;
    protected int lives;
    protected boolean isStarted;
    protected boolean isEnded;
    protected HealthBar healthBar;
    protected ScoreBoard scoreBoard;
    protected ColorPalette palette;
    protected int score;
    public EnlargementBonus firstEnlargementBonus = null;
    public SpeedBonus firstSpeedBonus = null;

    protected boolean shouldSpawnEnlargementBonus;
    protected boolean shouldSpawnSpeedBonus;
    protected double ballSpeed = 5;

    private GRect background;

    /**
     * Creates a new Level with the specified dimensions.
     *
     * @param width  The width of the level.
     * @param height The height of the level.
     */
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Sets up the initial state of the level, including creating graphical elements.
     */
    public void setup() {
        drawBackground();
        healthBar = new HealthBar(0, 0, width / 2, height / 10, lives);
        scoreBoard = new ScoreBoard(3 * width / 4, 0, width / 4, height / 10);
        container = new BoxContainer(0, height / 10, width, height);
        ball = new BreakerBall(ballSpeed, width / 2, height / 2, ballRadius * 2, ballRadius * 2);
        paddle = new Paddle((width - paddleWidth) / 2, height - paddleYOffset, paddleWidth, paddleHeight, palette.getPaddle());
        bricksManager.spawn();
        Breakout.addObject(ball);
        Breakout.addObject(paddle);
        Breakout.addObject(healthBar);
        Breakout.addObject(scoreBoard);
        isStarted = true;
    }

    /**
     * Checks if the level has started.
     *
     * @return True if the level has started, false otherwise.
     */
    public boolean isStarted() {
        return isStarted;
    }

    /**
     * Checks if the level has ended.
     *
     * @return True if the level has ended, false otherwise.
     */
    public boolean isEnded() {
        if (bricksManager == null) {
            return false;
        }
        isEnded = isEnded || !bricksManager.anyBricksPresent() || lives <= 0;
        if (isEnded) {
            end();
        }
        return isEnded;
    }

    /**
     * Checks if the player has won the level.
     *
     * @return True if the player has won, false otherwise.
     */
    public boolean isWon() {
        return isEnded && !bricksManager.anyBricksPresent();
    }

    /**
     * Decrements the player's life count.
     */
    public void decrementLife() {
        lives--;
        healthBar.decrementLife();
        if (lives <= 0) {
            end();
        }
    }

    /**
     * Handles mouse movement events to control the paddle.
     *
     * @param e The MouseEvent representing the mouse movement.
     */
    public void mouseMoved(MouseEvent e) {
        double x;
        if ((e.getX() + paddleWidth / 2) > width)
            x = width - paddleWidth;
        else if ((e.getX() - paddleWidth / 2) < 0)
            x = 0;
        else
            x = e.getX() - paddleWidth / 2;
        paddle.setLocation(x, paddle.getY());
    }

    /**
     * Handles mouse click events.
     *
     * @param object The GObject that was clicked.
     */
    public void mouseClicked(GObject object) {
        ball.setActive(true);
    }

    /**
     * Adds the specified score to the current score.
     *
     * @param score The score to add.
     */
    public void addScore(int score) {
        this.score += score;
        scoreBoard.setScore(this.score);
    }

    /**
     * Updates the state of the level, including checking for bonus updates and ball movement.
     */
    public void update() {
        if (isStarted && !isEnded()) {
            if (firstEnlargementBonus != null) {
                firstEnlargementBonus.update();
            }
            if (firstSpeedBonus != null) {
                firstSpeedBonus.update();
            }
            ball.update();
        } else if (isStarted && isEnded) {
            end();
        }
    }

    /**
     * Gets the color palette used for the level.
     *
     * @return The ColorPalette object.
     */
    public ColorPalette getPalette() {
        return palette;
    }

    /**
     * Checks if the level should spawn enlargement bonuses.
     *
     * @return True if enlargement bonuses should be spawned, false otherwise.
     */
    public boolean isShouldSpawnEnlargementBonus() {
        return shouldSpawnEnlargementBonus;
    }

    /**
     * Checks if the level should spawn speed bonuses.
     *
     * @return True if speed bonuses should be spawned, false otherwise.
     */
    public boolean isShouldSpawnSpeedBonus() {
        return shouldSpawnSpeedBonus;
    }

    /**
     * Ends the level and transitions to the appropriate scene.
     */
    protected void end() {
        Breakout.setLastGameResult(new GameResult(isWon(), score));
        Breakout.setActiveScene(SceneType.RESTART_MENU);
    }

    /**
     * Draws the background of the level.
     */
    protected void drawBackground() {
        background = new GRect(0, 0, width, height);
        background.setColor(palette.getBackground());
        background.setFillColor(palette.getBackground());
        background.setFilled(true);
        Breakout.addObject(background);
    }

    /**
     * Creates the bricks manager for the level.
     */
    protected void createBricksManager() {
        bricksManager = new BricksManager(0, height / 10 + brickYOffset, width);
    }
}
