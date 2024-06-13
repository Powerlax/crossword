package crossword;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighScoreManager {

    private static final String HIGH_SCORE_FILE = HighScoreManager.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"\\high_scores.txt";

    public static void saveHighScore(String playerName, int score) {
    	
        try (PrintWriter writer = new PrintWriter(new FileWriter(HIGH_SCORE_FILE, true))) {
            writer.println(playerName + "," + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadHighScores() {
        List<String> highScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort high scores in descending order (highest to lowest)
        Collections.sort(highScores, Comparator.comparingInt(s -> -Integer.parseInt(s.split(",")[1])));

        return highScores;
    }
    public static String getHighestScore() {
        List<String> highScores = loadHighScores();
        if (highScores.isEmpty()) {
            return "No high scores available.";
        }

        String highestScoreLine = highScores.get(0); // Assuming the list is sorted
        String[] parts = highestScoreLine.split(",");
        String playerName = parts[0];
        int score = Integer.parseInt(parts[1]);

        return score + " set by " + playerName;
    }
}
