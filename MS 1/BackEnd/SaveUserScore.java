package BackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SaveUserScore {
	private String username;
	private int score;
	// (int wpm, int numOfWords, int numOfLetters, int time,
	// int level)

	private BufferedWriter bw;

	public SaveUserScore(String username, int score) {
		this.username = username;
		this.score = score;
	}

	public SaveUserScore() {

	}

	public boolean loadFileOpenIt() throws IOException {
		File file = new File("D:\\eclipse\\TypeRacerDoc\\savedInfo.txt");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		this.bw = new BufferedWriter(fw);
		return true;
	}

	public String getUsername() {
		return username;
	}

	public int getScore() {
		return score;
	}

	public void setInFile(SaveUserScore user) throws IOException {
		bw.write(user.getUsername() + " " + user.getScore());
		bw.newLine();
	}

	public void stopWrite() throws IOException {
		bw.close();
	}

	public ArrayList<SaveUserScore> getFromFile() throws IOException {
		ArrayList<SaveUserScore> savedInfo = new ArrayList<SaveUserScore>();
		String line = null;

		@SuppressWarnings("resource")
		BufferedReader buffRdr = new BufferedReader(new FileReader(
				"D:\\eclipse\\TypeRacerDoc\\savedInfo.txt"));
		line = buffRdr.readLine();
		while (line != null) {
			String content[] = line.split(" ");
			SaveUserScore s = new SaveUserScore(content[0],
					Integer.parseInt(content[1]));
			savedInfo.add(s);
			line = buffRdr.readLine();
		}
		return savedInfo;
	}

	public ArrayList<SaveUserScore> getTheScourFromBestToWorst(
			ArrayList<SaveUserScore> userData) {
		ArrayList<SaveUserScore> scoreRating = new ArrayList<SaveUserScore>();

		for (SaveUserScore s : userData) {
			scoreRating.add(s);
		}

		Collections.sort(scoreRating, new Comparator<SaveUserScore>() {
			public int compare(SaveUserScore s1, SaveUserScore s2) {
				return s2.getScore() - s1.getScore();
			}
		});

		return scoreRating;
	}

}
