package game;

import java.io.IOException;
import java.util.Random;

import javax.microedition.lcdui.*;

public class PlayingField extends Canvas {
	private BaseGame baseGame;
	private int width, height;
	private int fieldWidth;
	private Image x, o;
	public PlayingField() {
		width = getWidth();
		height = getHeight();
		if (width <= height)
			fieldWidth = (int) (width * 0.8);
		else
			fieldWidth = (int) (height * 0.8);
		try {
			x = Image.createImage("/x.png");
			o = Image.createImage("/o.png");
			x = resizeImage(x, (int)((fieldWidth / 3) * 0.7),
					(int)((fieldWidth / 3) * 0.7));
			o = resizeImage(o, (int)((fieldWidth / 3) * 0.7),
					(int)((fieldWidth / 3) * 0.7));
		} catch (IOException e) {
			throw new Error("Failed to load images!");
		}
	}
	protected void paint(Graphics g) {
		g.setColor(0xC7DDFE);
		g.fillRect(0, 0, width, height);
		g.setColor(0x69AAC4);
		g.translate((width - fieldWidth) / 2, (height - fieldWidth) / 2);
		g.fillRect(fieldWidth / 3 - 2, 0, 5, fieldWidth);
		g.fillRect(fieldWidth * 2 / 3 - 2, 0, 5, fieldWidth);
		g.fillRect(0, fieldWidth / 3 - 2, fieldWidth, 5);
		g.fillRect(0, fieldWidth * 2 / 3 - 2, fieldWidth, 5);
		for (byte i = 0; i < 3; ++i) {
			for (byte j = 0; j < 3; ++j) {
				if (baseGame.signs[i][j] != -1) {
					if (baseGame.signs[i][j] == 0) {
						drawXO(i, j, 0, g);
					} else if (baseGame.signs[i][j] == 1) {
						drawXO(i, j, 1, g);
					}
				}
			}
		}
		if (baseGame.winLine != 0) {
			int d = (int)(fieldWidth * 0.1);
			g.setColor(0xFF0000);
			if (baseGame.winLine < 0) {
				if (baseGame.winLine == -4) {
					g.drawLine(d, d, fieldWidth - d, fieldWidth - d);
					g.drawLine(d + 1, d, fieldWidth - d, fieldWidth - d - 1);
					g.drawLine(d, d + 1, fieldWidth - d - 1, fieldWidth - d);
				} else {
					int y = fieldWidth / 6 + fieldWidth * (-baseGame.winLine - 1) / 3 - 1;
					g.fillRect(d, y, fieldWidth - 2 * d, 3);
				}
			}
			else if (baseGame.winLine > 0) {
				if (baseGame.winLine == 4) {
					g.drawLine(fieldWidth - d, d, d, fieldWidth - d);
					g.drawLine(fieldWidth - d - 1, d, d, fieldWidth - d - 1);
					g.drawLine(fieldWidth - d, d + 1, d + 1, fieldWidth - d);
				} else {
					int x = fieldWidth / 6 + fieldWidth * (baseGame.winLine - 1) / 3 - 1;
					g.fillRect(x, d, 3, fieldWidth - 2 * d);
				}
			}
		}
	}
	private Image resizeImage(Image image, int newWidth, int newHeight)	{  
		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();
		Image tmpImage = Image.createImage(newWidth, newHeight);
		Graphics g = tmpImage.getGraphics();
		for (int y = 0; y < newHeight; y++)
		{
			for (int x = 0; x < newWidth; x++) {
				g.setClip(x, y, 1, 1);
				int dx = x * sourceWidth / newWidth;
				int dy = y * sourceHeight / newHeight;
				g.drawImage(image, x - dx, y - dy,
						Graphics.LEFT | Graphics.TOP);
			}
		}
		return Image.createImage(tmpImage);
	}
	public void keyReleased(int key) {
		baseGame.processKey(key);
	}
	private void drawXO(byte row, byte column, int sign, Graphics g) {
		/*
		 * o: sign = 0
		 * x: sign = 1 
		 */
		if (sign == 0) {
			g.drawImage(o, fieldWidth / 6 + fieldWidth * column / 3,
					fieldWidth / 6 + fieldWidth * row / 3,
					Graphics.HCENTER | Graphics.VCENTER);
		} else if (sign == 1) {
			g.drawImage(x, fieldWidth / 6 + fieldWidth * column / 3,
					fieldWidth / 6 + fieldWidth * row / 3,
					Graphics.HCENTER | Graphics.VCENTER);
		}
	}
	public void setBaseGame(BaseGame baseGame) {
		this.baseGame = baseGame;
	}
}
