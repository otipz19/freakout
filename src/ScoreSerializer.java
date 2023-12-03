import java.io.*;

public class ScoreSerializer {
    private static final String filePath = System.getProperty("user.dir") + "/save.txt";

    public static void setBestScore(int value) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("BEST SCORE:\n" + Integer.toString(value));
            writer.close();
            fileWriter.close();
        } catch (Exception e) {

        }
    }

    public static int getBestScore() {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine();
            String value = reader.readLine();
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
}
