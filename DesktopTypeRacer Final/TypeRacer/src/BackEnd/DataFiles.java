package BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataFiles {
	private String name;
	private String content;
	private int numOfWords;
	private int numOfLetters;

	public DataFiles(String name) {
		this.name = name;
		this.content = " ";
		this.numOfWords = 0;
		this.numOfLetters = 0;
	}

	public int getNumOfLetters() {
		return numOfLetters;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	public String[] fromStringToArray() {
		return content.split(" ");
	}

	public int getNumOfWords() {
		return numOfWords;
	}

	public String loadTheText() throws IOException {
		String line = null;

		@SuppressWarnings("resource")
		BufferedReader buffRdr = new BufferedReader(new FileReader(this.name));
		line = buffRdr.readLine();
		content = null;
		while (line != null) {
			this.content += line;
			line = buffRdr.readLine();
		}
		return this.content;
	}

	public ArrayList<String> drawTheText(String con, ArrayList<String> words, double LENGTH) {
		con = con.substring(1, con.length());
		String[] text = con.split(" ");
		String correct = null;
		boolean firstTime = false;
		int letterCount = 0;
		content = null;
		for (String s : text) {
			correct += s + " ";
			letterCount += s.length() + 1;
			numOfWords += 1;
			if (letterCount > LENGTH) {
				letterCount = 0;
				if (firstTime != true) {
					words.add(correct.substring(10, correct.length()));
					firstTime = true;
				} else {
					words.add(correct.substring(4, correct.length()));
				}
				correct = null;
			}
		}
		if (letterCount != 0) {
			words.add(correct.substring(4, correct.length()));
		}
		return words;
	}

	public ArrayList<String> parseTheText(ArrayList<String> word) {
		ArrayList<String> parsed = new ArrayList<String>();

		for (String s : word) {
			String[] text = s.split(" ");
			for (String st : text) {
				parsed.add(st);
			}
		}
		return parsed;
	}
}
