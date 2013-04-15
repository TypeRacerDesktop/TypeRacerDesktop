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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import BackEnd.Chronometer;
import BackEnd.DataFiles;
import BackEnd.SaveUserScore;

public class Window {
	public Display d;
	public Shell shell;
	public GC gc;
	public Font font;
	public Rectangle bds;
	public Image background;
	private int width;
	private int height;
	private int letterCounter;
	private int currentId;
	private boolean wasSubmitted;
	private boolean pause;
	private Color answer;
	private Color defaultColor;
	private ArrayList<String> words;
	private ArrayList<String> parse;
	ArrayList<SaveUserScore> results = new ArrayList<SaveUserScore>();
	SaveUserScore data;
	private String currentWord;

	public Window() {
		d = new Display();
		shell = new Shell(d, SWT.NONE);
		gc = new GC(shell);

		bds = shell.getDisplay().getBounds();
		data = new SaveUserScore();
		width = bds.width;
		height = bds.height;
		defaultColor = gc.getBackground();
		background = new Image(d, "./Background\\ico.jpg");
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

		startGame.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					System.exit(0);
				}
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

		rankList.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					System.exit(0);
				}
			}
		});

		final Button about = new Button(shell, SWT.PUSH);
		about.setText("About");
		about.setBounds((width / 2) - 50, (height / 2) - 50, 100, 100);
		about.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				clearScreen();
				try {
					about();
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

		about.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					System.exit(0);
				}
			}
		});

		escButton();

		shell.open();

		while (!shell.isDisposed()) {
			if (!d.readAndDispatch()) {
				gc.drawImage(background, 0, 0);
			}
		}
	}

	public void clearScreen() {
		for (Control kid : shell.getChildren()) {
			kid.dispose();
		}
		Rectangle blank = shell.getDisplay().getBounds();
		gc.setBackground(defaultColor);
		gc.fillRectangle(blank);
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

		easy.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					clearScreen();
					createCanvas();
				}

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

		medium.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					clearScreen();
					createCanvas();
				}
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

		hard.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					clearScreen();
					createCanvas();
				}
			}
		});

		escButton();

		shell.open();

		while (!shell.isDisposed()) {
			if (!d.readAndDispatch()) {
				gc.drawImage(background, 0, 0);
			}
		}
		d.dispose();
	}

	public void rankList() throws IOException {
		escButton();

		results = data.getFromFile();
		results = data.getTheScourFromBestToWorst(results);

		font = new Font(d, "Arial", 60, SWT.NORMAL);
		gc.setFont(font);
		Point textSize = gc.textExtent("RankingList:");
		int wid = (width - textSize.x) / 2;

		shell.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					clearScreen();
					createCanvas();
				}
			}
		});
		while (!shell.isDisposed()) {
			if (!d.readAndDispatch()) {
				printUserData(1);
				gc.drawText("RankingList: ", wid, 50);
			}
		}
	}

	public boolean checkWord(String word) {
		word.replaceAll(" ", "");

		return (currentWord.substring(0,
				word.length() > currentWord.length() ? currentWord.length()
						: word.length()).equals(word));
	}

	public void about() throws IOException {
		font = new Font(d, "Arial", 60, SWT.NORMAL);
		gc.setFont(font);
		Point textSize = gc.textExtent("About:");
		int wid = (width - textSize.x) / 2;

		escButton();
		DataFiles df = new DataFiles("./About\\about.txt");
		df.loadTheText();
		words = new ArrayList<String>();
		parse = new ArrayList<String>();

		String con = df.getContent();
		words = df.drawTheText(con, words, 70);
		parse = df.parseTheText(words);

		shell.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					clearScreen();
					createCanvas();
				}
			}
		});

		while (!shell.isDisposed()) {
			if (!d.readAndDispatch()) {
				drawAbout();
				gc.drawText("About: ", wid, 100);
			}
		}

	}

	private void escButton() {
		final Button esc = new Button(shell, SWT.PUSH);
		esc.setText("Quit");
		esc.setBounds(width - 70, 0, 70, 50);
		esc.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.exit(0);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void changeTheWord(ArrayList<String> parsed, int currentId) {
		currentWord = parsed.get(currentId);
	}

	public void userData(final int wpm, final int maxWpm, final int time,
			final int level, final int total) throws IOException {

		results = data.getFromFile();
		
		int counterForData = 0;
		for (SaveUserScore s : results) {
			counterForData++;
		}
		results = data.setForLastest(results, counterForData);
		
		escButton();

		final Text username = new Text(shell, SWT.BORDER);
		username.setBounds(width / 10, height / 10, (width - (width / 4)) / 2,
				50);
		username.setFocus();
		username.setFont(new Font(d, "Arial", 35, SWT.NORMAL));
		username.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 27) {
					clearScreen();
					createCanvas();
				} else if (e.character == 13) {
					if (username.getText() != "") {
						String newName = username.getText();
						if (username.getText().length() > 12) {
							newName = username.getText().substring(0, 12);
						}
						SaveUserScore saved = new SaveUserScore(newName, wpm,
								maxWpm, time, currentId, level, total);
						try {
							saved.loadFileOpenIt();
							saved.setInFile();
							saved.stopWrite();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						clearScreen();
						createCanvas();
					}
				}
			}
		});

		while (!shell.isDisposed()) {
			if (!d.readAndDispatch()) {
				printUserData(2);
			}
		}
		d.dispose();

	}

	public void levelSelected(final int i) throws IOException {
		Random rand = new Random();

		DataFiles df = null;
		int topSpeed = 0;
		int speed = 0;
		letterCounter = 0;
		currentId = 0;
		pause = false;
		wasSubmitted = false;
		answer = d.getSystemColor(SWT.COLOR_GREEN);
		final Chronometer ch = new Chronometer();
		int widthHelper = (((width / 20) + (width - (width / 4)) + 50) - ((width / 20)
				+ width - (width / 4))) / 2;
		int chWidthHelper = (((width / 20) + width - (width / 4)) + widthHelper)
				+ ((width - (((width / 20) + width - (width / 4)) + widthHelper)) / 2);
		int chHeightHelper = height / 20;
		int wpmHeightHelper = height / 6;
		int num = rand.nextInt(8);
		ch.startTime();

		gc.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.drawRectangle(-1, -1, ((width / 20) + width - (width / 4))
				+ widthHelper, height - (height / 4));
		double width1 = ((width - ((width / 20) + (width - (width / 4)))) / 2) * 0.66;
		final Rectangle space = new Rectangle((width / 20)
				+ (width - (width / 4)) + 50, (height - (height / 5)) - 50,
				(width / 20) + (width - (width / 4)) + 50 + (int) width1 * 2,
				((height - (height / 5)) - 50) + 130);
		gc.setBackground(answer);
		gc.fillRectangle(space);
		gc.setBackground(defaultColor);

		df = new DataFiles("./Data\\Book" + i + num + ".txt");
		df.loadTheText();
		words = new ArrayList<String>();
		parse = new ArrayList<String>();

		String con = df.getContent();
		words = df.drawTheText(con, words, 163);
		parse = df.parseTheText(words);

		drawBackgroundOnCurrentWord();

		escButton();
		final Text text = new Text(shell, SWT.BORDER);
		text.setBounds(width / 20, height - (height / 5), width - (width / 4),
				50);
		text.setFocus();
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
						if (currentWord.length() == text.getText().length()) {
							text.setText("");
							currentId += 1;
							wasSubmitted = true;
							drawBackgroundOnCurrentWord();
						}
					}
				} else if (e.character == 27) {
					if (currentId > 0) {
						if (pause != true) {
							pause = true;
						}
					} else {
						clearScreen();
						startGame();
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
				changeTheWord(parse, currentId);
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
				speed = ch.wpmCounter(letterCounter,
						Integer.parseInt(ch.getTillNow()));
				if (speed > topSpeed) {
					topSpeed = speed;
				}
				if (speed > 99) {
					gc.drawText(Integer.toString(speed) + " wpm  ",
							chWidthHelper - 100, wpmHeightHelper);
				} else {
					gc.drawText(Integer.toString(speed) + " wpm  ",
							chWidthHelper - 100, wpmHeightHelper);
				}
				if (((pause) || (Integer.parseInt(ch.getTillNow()) == 500))
						|| (currentId > df.getNumOfWords())) {
					clearScreen();
					if (currentId < 1) {
						createCanvas();
					}
					userData(speed, topSpeed,
							Integer.parseInt(ch.getTillNow()), i,
							df.getNumOfWords());
				}
			//	drawBackgroundOnCurrentWord();
			}
		}
		d.dispose();
	}

	public void printUserData(int caseOfCall) {
		int rows = 1;
		int cols = 650;
		int cont = 0;
		int counter = 1;
		font = new Font(d, "Calibri", 30, SWT.NORMAL);
		gc.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.setFont(font);
		Point spaceSize = gc.textExtent(" ");

		if ((caseOfCall == 1) || (caseOfCall == 2)) {
			rows = width / 7;
			cont = height / 10;
		} else if (caseOfCall == 3) {
			rows = 450;
			cont = 150;
		}
		int maxUserName = 0;
		for (SaveUserScore r : results) {
			if (maxUserName < r.getUsername().length()) {
				Point size = gc.textExtent(r.getUsername());
				maxUserName = size.x;
			}
		}

		ArrayList<Integer> ColsData = drawTable(cont, rows, maxUserName);
		rows += 80;
		for (SaveUserScore r : results) {
			cols = cont;
			if (counter < 10) {
				gc.drawText(Integer.toString(counter) + "  .", cols, rows);
				Point size = gc.textExtent(Integer.toString(counter) + "  .");
				cols += size.x + spaceSize.x;
			} else {
				gc.drawText(Integer.toString(counter) + ".", cols, rows);
				Point size = gc.textExtent(Integer.toString(counter) + ".");
				cols += size.x + spaceSize.x;
			}
			Point size = gc.textExtent(r.getUsername());
			gc.drawText(r.getUsername(),
					calculate(ColsData.get(0), ColsData.get(1), size.x), rows);
			cols += size.x + spaceSize.x;
			size = gc.textExtent(Integer.toString(r.getWpm()));
			gc.drawText(Integer.toString(r.getWpm()),
					calculate(ColsData.get(1), ColsData.get(2), size.x), rows);
			cols += size.x + spaceSize.x;
			size = gc.textExtent(Integer.toString(r.getMaxWpm()));
			gc.drawText(Integer.toString(r.getMaxWpm()),
					calculate(ColsData.get(2), ColsData.get(3), size.x), rows);
			cols += size.x + spaceSize.x;
			size = gc.textExtent(Integer.toString(r.getCurrentId()) + " /"
					+ Integer.toString(r.getTotal()));
			gc.drawText(
					Integer.toString(r.getCurrentId()) + " /"
							+ Integer.toString(r.getTotal()),
					calculate(ColsData.get(3), ColsData.get(4), size.x), rows);
			cols += size.x + spaceSize.x;
			size = gc.textExtent(Integer.toString(r.getTime()));
			gc.drawText(Integer.toString(r.getTime()),
					calculate(ColsData.get(4), ColsData.get(5), size.x), rows);
			cols += size.x + spaceSize.x;
			size = gc.textExtent(Integer.toString(r.getLevel()));
			gc.drawText(Integer.toString(r.getLevel()),
					calculate(ColsData.get(5), ColsData.get(6), size.x), rows);
			cols += size.x + spaceSize.x;
			size = gc.textExtent(r.getUsername());
			rows += size.y;
			counter++;
		}
		cols = 10;
		font = new Font(d, "Arial", 60, SWT.NORMAL);
		gc.setFont(font);
	}

	public void drawBackgroundOnCurrentWord() {
		int rows = 1;
		int cols = 10;
		int countForDraw = 0;
		font = new Font(d, "Calibri", 11, SWT.NORMAL);
		gc.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.setFont(font);
		Point spaceSize = gc.textExtent(" ");
		for (String w : words) {
			String[] text2 = w.split(" ");
			for (String s : text2) {
				Point size = gc.textExtent(s);
				if (countForDraw == currentId) {
					gc.setBackground(shell.getDisplay().getSystemColor(
							SWT.COLOR_GREEN));
					if (currentId > 0) {
						gc.drawText(s, cols, rows);
						gc.setBackground(defaultColor);
						font = new Font(d, "Arial", 40, SWT.NORMAL);
						gc.setFont(font);
						return;
					}
				} else {
					gc.setBackground(defaultColor);
				}
				gc.drawText(s, cols, rows);
				cols += size.x + spaceSize.x;
				countForDraw += 1;
			}
			cols = 10;
			rows += 25;
		}
		gc.setBackground(defaultColor);
		font = new Font(d, "Arial", 40, SWT.NORMAL);
		gc.setFont(font);
	}

	public int calculate(int a, int b, int c) {
		b = (a + ((b - a) / 2)) - (c / 2);
		return b;
	}

	public void drawAbout() {
		int rows = width / 6;
		int cols = 0;
		int maxRows = 0;
		font = new Font(d, "Calibri", 30, SWT.NORMAL);
		gc.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.setFont(font);
		Point spaceSize = gc.textExtent(" ");
		for (String w : words) {
			cols = width / 18;
			String[] text2 = w.split(" ");
			for (String s : text2) {
				Point size = gc.textExtent(s);
				gc.drawText(s, cols, rows);
				cols += size.x + spaceSize.x;
				if (maxRows < size.y) {
					maxRows = size.y;
				}
			}
			rows += maxRows;
		}
		gc.setBackground(defaultColor);
		font = new Font(d, "Arial", 53, SWT.NORMAL);
		gc.setFont(font);
	}

	public ArrayList<Integer> drawTable(int cols, int rows, int usernameLength) {
		String table = "Rank , Name , Average Wpm , Top Wpm , Completed , Time , Level ";
		ArrayList<Integer> dataCols = new ArrayList<Integer>();

		font = new Font(d, "Calibri", 30, SWT.NORMAL);
		gc.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.setFont(font);
		int counter = 0;
		Point spaceSize = gc.textExtent(" ");
		String[] text2 = table.split(",");
		for (String s : text2) {
			if (counter == 1) {
				Point size = gc.textExtent(s);
				if (usernameLength > size.x) {
					cols += usernameLength + spaceSize.x;
				} else {
					cols += size.x + spaceSize.x;
				}
				gc.drawText(s, calculate(dataCols.get(0), cols, size.x), rows);
				dataCols.add(cols);
				cols += spaceSize.x;
			} else {
				gc.drawText(s, cols, rows);
				Point size = gc.textExtent(s);
				cols += size.x + spaceSize.x;
				dataCols.add(cols);
				cols += spaceSize.x;
			}
			counter++;
		}

		for (Integer i : dataCols) {
			gc.drawLine(i, rows, i, height);
		}
		return dataCols;
	}
}
