package game;

import java.util.Random;

import javax.microedition.lcdui.Canvas;

public class BaseGame {
	private Canvas canvas;
	byte[][] signs; // o: 0; x: 1
	private byte lastAIRow, lastAIColumn;
	private byte playerSign = 1;
	private boolean win = false;
	int winLine = 0;
	/*
	 * -4: главная диагональ
	 * -3: третья строка
	 * -2: вторая строка
	 * -1: первая строка
	 *  1: первый столбец
	 *  2: второй столбец
	 *  3: третий столбец
	 *  4: побочная диагональ
	 */
	public BaseGame(Canvas canvas) {
		this.canvas = canvas;
		signs = new byte[3][3];
		resetField();
	}
	public void processKey(int key) {
		if (!win) {
			boolean flag = false;
			switch (key) {
			case Canvas.KEY_NUM1:
				if (signs[0][0] == -1) {
					signs[0][0] = playerSign;
					if (isWin(0, 0)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			case Canvas.KEY_NUM2:
				if (signs[0][1] == -1) {
					signs[0][1] = playerSign;
					if (isWin(0, 1)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			case Canvas.KEY_NUM3:
				if (signs[0][2] == -1) {
					signs[0][2] = playerSign;
					if (isWin(0, 2)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			case Canvas.KEY_NUM4:
				if (signs[1][0] == -1) {
					signs[1][0] = playerSign;
					if (isWin(1, 0)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			case Canvas.KEY_NUM5:
				if (signs[1][1] == -1) {
					signs[1][1] = playerSign;
					if (isWin(1, 1)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			case Canvas.KEY_NUM6:
				if (signs[1][2] == -1) {
					signs[1][2] = playerSign;
					if (isWin(1, 2)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			case Canvas.KEY_NUM7:
				if (signs[2][0] == -1) {
					signs[2][0] = playerSign;
					if (isWin(2, 0)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			case Canvas.KEY_NUM8:
				if (signs[2][1] == -1) {
					signs[2][1] = playerSign;
					if (isWin(2, 1)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			case Canvas.KEY_NUM9:
				if (signs[2][2] == -1) {
					signs[2][2] = playerSign;
					if (isWin(2, 2)) {
						win = true;
					} else {
						moveAI();
						if (isWin(lastAIRow, lastAIColumn)) {
							win = true;
						}
					}
					flag = true;
				}
				break;
			default:
				break;
			}
			if (flag) {
				canvas.repaint();
			}
		}
	}
	private void moveAI() {
		Random random = new Random();
		int move = Math.abs(random.nextInt() % 8);
		int count = -1;
		while (count != move) {
			for (byte i = 0; i < 3; ++i) {
				for (byte j = 0; j < 3; ++j) {
					if (signs[i][j] == -1) {
						++count;
						if (count == move) {
							signs[i][j] = (byte)((playerSign + 1) % 2);
							lastAIRow = i;
							lastAIColumn = j;
							canvas.repaint();
							return;
						}
					}
				}
			}
			if (count == -1)
				return;
		}
	}
	private boolean isWin(int row, int column) {
		if (signs[row][column] != -1) {
			if (signs[row][0] == signs[row][1] && signs[row][1] == signs[row][2]) {
				winLine = -(row + 1);
				return true;
			} else if (signs[0][column] == signs[1][column] &&
					signs[1][column] == signs[2][column]) {
				winLine = column + 1;
				return true;
			} else if ((row == 0 && column == 0 || row == 2 && column == 2 || row == 1 && column == 1) &&
					(signs[0][0] == signs[1][1] && signs[1][1] == signs[2][2])) {
				winLine = -4;
				return true;
			} else if ((row == 0 && column == 2 || row == 2 && column == 0 || row == 1 && column == 1) &&
					(signs[0][2] == signs[1][1] && signs[1][1] == signs[2][0])) {
				winLine = 4;
				return true;
			}
		}
		return false;
	}
	private void resetField() {
		for (byte i = 0; i < 3; ++i) {
			for (byte j = 0; j < 3; ++j) {
				signs[i][j] = -1;
			}
		}
	}
	public void startNewGame() {
		resetField();
		winLine = 0;
		win = false;
		canvas.repaint();
	}
}
