package FrontEnd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import BackEnd.Chronometer;
import BackEnd.DataFiles;
import BackEnd.SaveUserScore;

//moje da se napravi nov
// class koito da ti
// otvarq tam menuto sas
// slojnostite i ot tam
// da ti vrashta imeto
// na faila koito trqbva
// da otvorish i veche
// da se zarejda nov
// class koito realno da
// ti pravi tam
// hronometar i celiq
// nov prozorec. Sled
// koeto shte ima da se
// otvarq nov class
// koito shte e lastest
// ranking results ili
// neshto takova.

public class Window {
	public Display d;
	public Shell shell;
	public Rectangle bds;
	private int width;
	private int height;
	private int speed;
	private int letterCounter;
	private int currentId;
	private boolean wasSubmitted;
	private Color answer;
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> parse = new ArrayList<String>();
	ArrayList<SaveUserScore> results = new ArrayList<SaveUserScore>();
	SaveUserScore data;
	private String currentWord;

	public Window() {
		d = new Display();
		shell = new Shell(d);

		bds = shell.getDisplay().getBounds();
		data = new SaveUserScore();
		width = bds.width;
		height = bds.height;
		speed = 0;
		letterCounter = 0;
		currentId = 0;
		wasSubmitted = false;
		answer = d.getSystemColor(SWT.COLOR_GREEN);
	}

	public void createCanvas() {

		shell.setMaximized(true);
		shell.setText("Desktop TypeRacer");

		final Button startGame = new Button(shell, SWT.PUSH);
		startGame.setText("Start Game");
		startGame
				.setBounds((width / 2) - 50, (height / 2) - 50 - 200, 100, 100);
		startGame.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				clearScreen();
				startGame();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		final Button rankList = new Button(shell, SWT.PUSH);
		rankList.setText("Rank List");
		rankList.setBounds((width / 2) - 50, (height / 2) - 50 - 100, 100, 100);
		rankList.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				clearScreen();
				try {
					rankList();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		final Button about = new Button(shell, SWT.PUSH);
		about.setText("About");
		about.setBounds((width / 2) - 50, (height / 2) - 50, 100, 100);
		about.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				clearScreen();
				about();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		shell.open();

		while (!shell.isDisposed()) {
			if (!d.readAndDispatch()) {
				d.sleep();
			}
		}
	}

	public void clearScreen() {
		for (Control kid : shell.getChildren()) {
			kid.dispose();
		}
		// Composite c = new Composite(shell, SWT.NONE);
	}

	public int wpmCounter(int numberOfLetters, long timeSinceNow) {
		int wordsCount = (int) (numberOfLetters / 5);
		float minute = (float) timeSinceNow / 60;
		int newWPM = (int) (wordsCount / minute);

		return newWPM;
	}

	public void startGame() {
		final Button easy = new Button(shell, SWT.PUSH);
		easy.setText("150");
		easy.setBounds((width / 2) - 50, (height / 2) - 50 - 200, 100, 100);
		easy.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				clearScreen();
				try {
					levelSelected(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		final Button medium = new Button(shell, SWT.PUSH);
		medium.setText("250");
		medium.setBounds((width / 2) - 50, (height / 2) - 50 - 100, 100, 100);
		medium.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				clearScreen();
				try {
					levelSelected(2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		final Button hard = new Button(shell, SWT.PUSH);
		hard.setText("350");
		hard.setBounds((width / 2) - 50, (height / 2) - 50, 100, 100);
		hard.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				clearScreen();
				try {
					levelSelected(3);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		shell.open();

		while (!shell.isDisposed()) {
			if (!d.readAndDispatch()) {
				d.sleep();
			}
		}
		d.dispose();
	}

	public void rankList() throws IOException {
		GC gc = new GC(shell);
		Font font = new Font(d, "Arial", 60, SWT.NORMAL);
		gc.setFont(font);

		gc.drawText("RankingList: ", 200, 100);

		results = data.getFromFile();
		results = data.getTheScourFromBestToWorst(results);
		for (SaveUserScore s : results) {
			System.out.println(s.getUsername() + " " + s.getScore());
		}
	}

	public boolean checkWord(String word) {
		word.replaceAll(" ", "");
		// return (currentWord.substring(0, word.length()).equals(word));
		return (currentWord.equals(word));
	}

	public void about() {
		GC gc = new GC(shell);
		Font font = new Font(d, "Arial", 60, SWT.NORMAL);
		gc.setFont(font);

		gc.drawText("Instructions: ", 200, 100);
	}

	public void changeTheWord(ArrayList<String> parsed) {
		currentWord = parsed.get(currentId);
	}

	public void userData(int wpm, int numOfWords, int numOfLetters, int time,
			int level) {

		// sled kato ot formichka sa vevede informaciqta, shte se predava na
		// druga funkciq, koqto shte vizualizira poslednite 30 zapisa kato
		// posledniq zapis se zapisva nai otgore, za da moje po lesno da se
		// getva v posledstvie
	}

	public ArrayList<SaveUserScore> lastestResults() throws IOException {
		results = data.getFromFile();
		for (SaveUserScore s : results) {
			System.out.println(s.getUsername() + " " + s.getScore());
		}

		return results;
	}

	public void levelSelected(int i) throws IOException {
		Random rand = new Random();
		Font font;
		DataFiles df = null;
		final GC gc = new GC(shell);
		currentWord = null;
		lastestResults();
		final Color defaultColor = gc.getBackground();
		final Chronometer ch = new Chronometer();
		int widthHelper = (((width / 20) + (width - (width / 4)) + 50) - ((width / 20)
				+ width - (width / 4))) / 2;
		int chWidthHelper = (((width / 20) + width - (width / 4)) + widthHelper)
				+ ((width - (((width / 20) + width - (width / 4)) + widthHelper)) / 2);
		int chHeightHelper = height / 20;
		int wpmHeightHelper = height / 6;
		int num = rand.nextInt(10);
		ch.startTime();
		switch (i) {
		case 1:
			df = new DataFiles("Book10.txt"); // + num
			break;
		case 2:
			df = new DataFiles("Book20.txt"); // + num
			break;
		case 3:
			df = new DataFiles("Book30.txt"); // + num
			break;
		default:
			break;
		}
		df.loadTheText();
		String con = df.getContent();
		words = df.drawTheText(con, words);
		parse = df.parseTheText(words);

		gc.drawRectangle(-1, -1, ((width / 20) + width - (width / 4))
				+ widthHelper, height - (height / 4));

		int rows = 1;
		for (String w : words) {
			gc.drawText(w, 10, rows);
			rows += 25;
		}

		double width1 = ((width - ((width / 20) + (width - (width / 4)))) / 2) * 0.66;
		final Rectangle space = new Rectangle((width / 20)
				+ (width - (width / 4)) + 50, (height - (height / 5)) - 50,
				(width / 20) + (width - (width / 4)) + 50 + (int) width1 * 2,
				((height - (height / 5)) - 50) + 130);
		gc.setBackground(answer);
		gc.fillRectangle(space);
		gc.setBackground(defaultColor);

		final Text text = new Text(shell, SWT.BORDER);
		text.setBounds(width / 20, height - (height / 5), width - (width / 4),
				50);
		text.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (wasSubmitted) {
					text.setText("");
					wasSubmitted = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.character == 32) || (e.character == 13)) {
					if (checkWord(text.getText())) {
						if (currentWord.length() <= text.getText().length()) {
							text.setText("");
							currentId += 1;
							wasSubmitted = true;
						} else {
							text.setText("");
							wasSubmitted = true;
						}
					}
				}
				if (e.character != 8) {
					letterCounter += 1;
				} else if (letterCounter > 0) {
					letterCounter -= 1;
				}
			}
		});
		text.setFont(new Font(d, "Arial", 25, SWT.NORMAL));
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (checkWord(text.getText())) {
					answer = d.getSystemColor(SWT.COLOR_GREEN);
				} else {
					answer = d.getSystemColor(SWT.COLOR_RED);
				}
				gc.setBackground(answer);
				gc.fillRectangle(space);
				gc.setBackground(defaultColor);
			}
		});

		font = new Font(d, "Arial", 40, SWT.NORMAL);
		gc.setFont(font);

		while (!shell.isDisposed()) {
			if (!d.readAndDispatch()) {
				gc.setForeground(shell.getDisplay().getSystemColor(
						SWT.COLOR_BLACK));
				gc.drawText(
						Integer.toString(currentId) + " - "
								+ Integer.toString(df.getNumOfWords()),
						chWidthHelper - 100, wpmHeightHelper + 100);
				changeTheWord(parse);
				gc.drawText(ch.getTillNow() + "  ", chWidthHelper
						- (ch.getTillNow().length() * 15), chHeightHelper);
				if (speed < 30) {
					gc.setForeground(shell.getDisplay().getSystemColor(
							SWT.COLOR_GREEN));
				} else if (speed > 65) {
					gc.setForeground(shell.getDisplay().getSystemColor(
							SWT.COLOR_RED));
				} else {
					gc.setForeground(shell.getDisplay().getSystemColor(
							SWT.COLOR_YELLOW));
				}
				speed = wpmCounter(letterCounter,
						Integer.parseInt(ch.getTillNow()));
				if (speed > 99) {
					gc.drawText(Integer.toString(speed) + " wpm  ",
							chWidthHelper - 100, wpmHeightHelper);
				} else {
					gc.drawText(Integer.toString(speed) + " wpm  ",
							chWidthHelper - 100, wpmHeightHelper);
				}
			}
		}
		d.dispose();
	}
}
