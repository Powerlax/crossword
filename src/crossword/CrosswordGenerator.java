package crossword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CrosswordGenerator {
    public static Crossword generate() {
        try {
            String[] command = {"python", "your_script.py"};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println("Python script executed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Crossword {
	int cols, rows, numWords;
	char[][] solution;
	char[][] crossword;
	String[] legend;
	HashMap<String, Coordinates> coords;
	
	public Crossword(int cols, int rows, int numWords, String[] words, String[] legend, String[] cross, String[] sol) {
		this.cols = cols;
		this.rows = rows;
		this.numWords = numWords;
		this.legend = legend;
		solution = new char[rows][cols];
		for (int i = 0; i<sol.length; i++) {
			solution[i] = sol[i].toCharArray();
		}
		crossword = new char[rows][cols];
		for (int i = 0; i<cross.length; i++) {
			crossword[i] = cross[i].toCharArray();
		}
		coords = new HashMap<>();
		for (int i = 0; i<numWords; i++) {
			boolean across = legend[i].substring(legend[i].indexOf('.')+2).charAt(6) == 'a';
			int y1 = Integer.parseInt(legend[i].substring(legend[i].indexOf('(')+1, legend[i].indexOf(',')))-1;
			int x1 = Integer.parseInt(legend[i].substring(legend[i].indexOf(',')+1, legend[i].indexOf(')')))-1;
			String word = "";
			if (across) {
				for (int j = y1; j<solution[x1].length; j++) {
					if (solution[x1][j] == '-') {
						coords.put(word, new Coordinates(x1, y1, x1, j));
						break;
					} else {
						word += solution[x1][j];
					}
				}
			} else {
				for (int j = x1; j<solution.length; j++) {
					if (solution[j][y1] == '-') {
						coords.put(word, new Coordinates(x1, y1, j, y1));
						break;
					} else {
						word += solution[j][y1];
					}
				}
			}
		}
	}
}

class Coordinates {
	int x1, x2, y1, y2;
	public Coordinates(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public int getBeginingX() { //0 indexed
		return x1;
	}
	public int getEndingX() { //0 indexed
		return x2;
	}
	public int getBeginingY() { //0 indexed
		return y1;
	}
	public int getEndingY() { //0 indexed
		return y2;
	}
}