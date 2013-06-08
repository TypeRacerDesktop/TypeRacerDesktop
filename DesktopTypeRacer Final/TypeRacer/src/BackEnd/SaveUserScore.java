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
	private int wpm;
	private int maxWpm;
	private int time;
	private int currentId;
	private int level;
	private int total;
	private BufferedWriter bw;

	public SaveUserScore(String username, int wpm, int maxWpm, int time,
			int currentId, int level, int total) {
		this.username = username;
		this.wpm = wpm;
		this.maxWpm = maxWpm;
		this.time = time;
		this.currentId = currentId;
		this.level = level;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public int getMaxWpm() {
		return maxWpm;
	}

	public int getTime() {
		return time;
	}

	public int getCurrentId() {
		return currentId;
	}

	public int getLevel() {
		return level;
	}

	public SaveUserScore() {

	}

	public boolean loadFileOpenIt() throws IOException {
		File file = new File("./User\\savedInfo.txt");

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

	public int getWpm() {
		return wpm;
	}

	public void setInFile() throws IOException {
		bw.write(this.getUsername() + " ");
		bw.write(this.wpm + " ");
		bw.write(this.maxWpm + " ");
		bw.write(this.time + " ");
		bw.write(this.currentId + " ");
		bw.write(this.total + " ");
		bw.write(this.level + " ");
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
				"./User\\savedInfo.txt"));
		line = buffRdr.readLine();
		while (line != null) {
			String content[] = line.split(" ");
			SaveUserScore s = new SaveUserScore(content[0],
					Integer.parseInt(content[1]), Integer.parseInt(content[2]),
					Integer.parseInt(content[3]), Integer.parseInt(content[4]),
					Integer.parseInt(content[6]), Integer.parseInt(content[5]));
			savedInfo.add(s);
			line = buffRdr.readLine();
		}
		return savedInfo;
	}

	public ArrayList<SaveUserScore> setForLastest(
			ArrayList<SaveUserScore> userData2, int totalLength) {
		ArrayList<SaveUserScore> newArray = new ArrayList<SaveUserScore>();
		int newInt = totalLength;
		if (totalLength > 10) {
			newInt = totalLength - 10;
		}

		for (int i = totalLength; i >= newInt; i--) {
			newArray.add(userData2.get(i - 1));
		}
		return newArray;
	}

	public ArrayList<SaveUserScore> getTheScourFromBestToWorst(
			ArrayList<SaveUserScore> userData) {
		ArrayList<SaveUserScore> scoreRating = new ArrayList<SaveUserScore>();

		for (SaveUserScore s : userData) {
			scoreRating.add(s);
		}

		Collections.sort(scoreRating, new Comparator<SaveUserScore>() {
			public int compare(SaveUserScore s1, SaveUserScore s2) {
				return s2.getWpm() - s1.getWpm();
			}
		});

		return scoreRating;
	}

}
