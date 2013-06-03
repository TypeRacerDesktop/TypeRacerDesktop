package BackEnd;

public class Chronometer {
	private long startTime;
	private long endTime;

	public long startTime() {
		startTime = System.currentTimeMillis();
		return startTime;
	}

	public void endTime() {
		endTime = System.currentTimeMillis();
	}

	public String getTillNow() {
		long timeNow = System.currentTimeMillis();
		long untilnow = timeNow - startTime;
		String s = "0";
		if ((untilnow > 1000)) {
			s = Long.toString(untilnow / 1000);
		}
		return s;
	}

	public String reverseTime(int time) {
		long timeNow = System.currentTimeMillis();
		long difference = (startTime + time) - timeNow;
		String s = "0";
		if (difference > 1000) {
			s = Long.toString(difference / 1000);
		}

		return s;

	}

	public int wpmCounter(int numberOfLetters, long timeSinceNow) {
		int wordsCount = (int) (numberOfLetters / 5);
		float minute = (float) timeSinceNow / 60;
		int newWPM = (int) (wordsCount / minute);

		return newWPM;
	}
}
