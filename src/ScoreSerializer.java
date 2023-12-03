import java.io.*;

/**
 * The ScoreSerializer class is responsible for saving and loading the best score.
 */
public class ScoreSerializer {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/save.txt";

    /**
     * Sets the best score in the save file.
     *
     * @param value The best score to be saved.
     */
    public static void setBestScore(int value) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("BEST SCORE:\n" + Integer.toString(value));
            writer.close();
            fileWriter.close();
        } catch (Exception e) {

        }
    }

    /**
     * Retrieves the best score from the save file.
     *
     * @return The best score, or 0 if an error occurs during reading.
     */
    public static int getBestScore() {
        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine(); // Skip the line "BEST SCORE:"
            String value = reader.readLine();
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
}
