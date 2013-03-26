package game;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class TicTacToe extends MIDlet implements CommandListener {
	private Command exitCommand, newGameCommand;
	private PlayingField playingField;
	private BaseGame baseGame;
	
	public TicTacToe() {
		exitCommand = new Command("Exit", Command.EXIT, 1);
		newGameCommand = new Command("New game", Command.SCREEN, 1);
		playingField = new PlayingField();
		baseGame = new BaseGame(playingField);
		playingField.setBaseGame(baseGame);
		playingField.addCommand(exitCommand);
		playingField.addCommand(newGameCommand);
		playingField.setCommandListener(this);
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {}

	protected void pauseApp() {}

	protected void startApp() throws MIDletStateChangeException {
		Display.getDisplay(this).setCurrent(playingField);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == exitCommand) {
            try {
				destroyApp(false);
			} catch (MIDletStateChangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            notifyDestroyed();
        }
		else if (c == newGameCommand) {
			baseGame.startNewGame();
		}
	}

}
