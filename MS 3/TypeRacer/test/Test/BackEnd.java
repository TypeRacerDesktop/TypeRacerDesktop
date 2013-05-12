package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import BackEnd.Chronometer;
import BackEnd.DataFiles;
import BackEnd.SaveUserScore;
import FrontEnd.Window;

public class BackEnd {

	@Before
	public void initObjects() {
		Chronometer ch = new Chronometer();
		SaveUserScore sus = new SaveUserScore();
		DataFiles df = new DataFiles("./Data\\Book11.txt");

	}

	@Test
	public void testStartTime() {
		Chronometer ch = new Chronometer();
		assertEquals(System.currentTimeMillis(), ch.startTime());
	}

	@Test
	public void testEndTime() {
		Chronometer ch = new Chronometer();
		assertEquals(System.currentTimeMillis(), ch.endTime());
	}

	@Test
	public void testWpmCounter() {
		Chronometer ch = new Chronometer();
		assertEquals(4, ch.wpmCounter(20, 60));
	}

	@Test
	public void testGetTillNow() throws InterruptedException {
		Chronometer ch = new Chronometer();
		ch.startTime();
		Thread.sleep(600);
		assertEquals("0", ch.getTillNow());
		Thread.sleep(500);
		assertEquals("1", ch.getTillNow());

	}

	@Test
	public void testSUSGets() {
		SaveUserScore sus = new SaveUserScore("Ico", 60, 120, 40, 150, 1, 150);
		assertEquals("Ico", sus.getUsername());
		assertEquals(150, sus.getCurrentId());
		assertEquals(1, sus.getLevel());
		assertEquals(120, sus.getMaxWpm());
		assertEquals(60, sus.getWpm());
		assertEquals(40, sus.getTime());
		assertEquals(150, sus.getTotal());
	}

	@Test
	public void testSUSWrite() throws IOException {
		SaveUserScore sus = new SaveUserScore("Ico", 60, 120, 40, 150, 1, 150);
		assertEquals(true, sus.loadFileOpenIt());
		sus.setInFile();
		sus.stopWrite();
	}
	
	@Test 
	public void testSUSRead() throws IOException {
		ArrayList<SaveUserScore> arr = new ArrayList<SaveUserScore>();
		SaveUserScore sus = new SaveUserScore();
		arr = sus.getFromFile();
		arr = sus.getTheScourFromBestToWorst(arr);
		arr = sus.setForLastest(arr, 11);
		arr = sus.setForLastest(arr, 1);
	}
	
	@Test
	public void testDFGets() {
		DataFiles df = new DataFiles("./Data\\Book21.txt");
		assertEquals("./Data\\Book21.txt", df.getName());
		assertEquals(" ", df.getContent());
		assertEquals(0, df.getNumOfLetters());
		assertEquals(0, df.getNumOfWords());
		
	}
	
	@Test
	public void testDFRead() throws IOException {
		DataFiles df = new DataFiles("./Data\\Book21.txt");
		ArrayList<String> words = new ArrayList<String>();
		String cont = df.loadTheText();
		String[] test = df.fromStringToArray();
		df.parseTheText(words);
		df.drawTheText(cont, words, 160);
	}
	
	@Test
	public void testWindowRun() throws IOException {
		Window win = new Window();
		assertEquals(1, win.calculate(4, 6, 8));
		win.createCanvas(false);
//		win.levelSelected(1);
	}
}
