package crossword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CrosswordGenerator {
    public static Crossword generate(int rows, int cols) {
        try {
            Process process = Runtime.getRuntime().exec("python C://Users/Bogdi/git/crossword/src/crossword/helper.py " + rows + " " + cols);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println(exitCode);
            int numWords = Integer.parseInt(reader.readLine());
            String[] words = new String[numWords];
            for (int i = 0; i<numWords; i++) {
            	words[i] = reader.readLine();
            }
            reader.readLine();
            String[] solution = new String[rows];
            for (int i = 0; i<rows; i++) {
            	solution[i] = reader.readLine();
            }
            reader.readLine();
            String[] crossword = new String[rows];
            for (int i = 0; i<rows; i++) {
            	crossword[i] = reader.readLine();
            }
            reader.readLine();
            String[] legend = new String[numWords];
            for (int i = 0; i<numWords; i++) {
            	legend[i] = reader.readLine();
            }
            return new Crossword(rows, cols, numWords, words, legend, crossword, solution);
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
        
    }
}

class Crossword {
	private int cols, rows, numWords;
	private char[][] solution;
	private char[][] crossword;
	private String[] legend;
	private String[] words;
	private HashMap<String, Coordinates> coords;
	
	public Crossword(int cols, int rows, int numWords, String[] words, String[] legend, String[] cross, String[] sol) {
		this.cols = cols;
		this.rows = rows;
		this.numWords = numWords;
		this.legend = legend;
		this.words = words;
		solution = new char[rows][cols];
		for (int i = 0; i<sol.length; i++) {
			solution[i] = sol[i].replaceAll("\\s", "").toCharArray();
		}
		crossword = new char[rows][cols];
		for (int i = 0; i<cross.length; i++) {
			crossword[i] = cross[i].replaceAll("\\s", "").toCharArray();
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
						coords.put(word, new Coordinates(x1, y1, x1, j-1, across));
						break;
					} else {
						word += solution[x1][j];
					}
				}
			} else {
				for (int j = x1; j<solution.length; j++) {
					if (solution[j][y1] == '-') {
						coords.put(word, new Coordinates(x1, y1, j-1, y1, across));
						break;
					} else {
						word += solution[j][y1];
					}
				}
			}
		}
	}
	
	public char[][] getSolution() {
		return solution;
	}
	public char[][] getCrossword() {
		return crossword;
	}
	public String[] getLegend() {
		return legend;
	}
	public String[] getWordList() {
		return words;
	}
	public HashMap<String, Coordinates> getLocations() {
		return coords;
	}
	public int getNumRows() {
		return rows;
	}
	public int getNumCols() {
		return cols;
	}
	public int getNumWords() {
		return numWords;
	}
}

class Coordinates {
	private int x1, x2, y1, y2;
	private boolean across;
	public Coordinates(int x1, int y1, int x2, int y2, boolean a) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		across = a;
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
	public boolean isAcross() {
		return across;
	}
	public boolean isDown() {
		return !across;
	}
	public String toString() {
		return Integer.toString(x1) + " " + Integer.toString(y1) + " " + Integer.toString(x2) + " " + Integer.toString(y2) + " " + Boolean.toString(across);
	}
}