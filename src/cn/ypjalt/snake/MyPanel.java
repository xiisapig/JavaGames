package cn.ypjalt.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Created by 袁鹏竣 on 2017/11/16 0016.
 */
public class MyPanel extends JPanel implements Runnable {

	Snake snake = null;
	Food food = null;
	int opt = KeyEvent.VK_RIGHT;
	BufferedImage img;
	MyGame jf;

	public MyPanel(MyGame jf) {
		this.jf = jf;
		snake = new Snake(jf);
		food = new Food(snake);

		try {
			img = ImageIO.read(getClass().getResource("gameover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(0xdedede));
		g.fillRect(0, 0, MyGame.width, MyGame.height);
		// 绘制方格
		g.setColor(new Color(0xaaaaaa));
		for (int i = 0; i < 20; i++) {
			g.drawLine(i * 20, 0, i * 20, MyGame.height);
		}
		for (int i = 0; i < 30; i++) {
			g.drawLine(0, i * 20, MyGame.width, i * 20);
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 30; j++) {
				g.setColor(new Color(0xababab));
				g.drawRect(i * 20 + 2, j * 20 + 2, 16, 16);
				g.drawRect(i * 20 + 5, j * 20 + 5, 11, 11);
				g.setColor(Color.WHITE);
				g.drawRect(i * 20 + 9, j * 20 + 9, 3, 3);
			}
		}
		snake.paint(g);
		food.paint(g);
		if (snake.isStop)
			g.drawImage(img, 0, 0, 400, 600, 0, 0, img.getWidth(), img.getHeight(), null);
	}

	@Override
	public void run() {
		while (true) {
			int speedTime = 200 - snake.level * 5;
			if (speedTime < 20)
				speedTime = 20;
			try {
				Thread.sleep(speedTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			snake.step(opt, food);
			repaint();
		}

	}
}
