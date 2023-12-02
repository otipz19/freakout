public class GameResult {
    private boolean isWon;
    private int score;

    public GameResult(boolean isWon, int score) {
        this.isWon = isWon;
        this.score = score;
    }

    public boolean isWon() {
        return isWon;
    }

    public int getScore() {
        return score;
    }
}
