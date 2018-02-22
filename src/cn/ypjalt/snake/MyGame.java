package cn.ypjalt.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 * Created by 袁鹏竣 on 2017/11/16 0016.
 */
public class MyGame extends JFrame {
	MyPanel mp = null;

	static int width = 400;
	static int height = 600;

	public MyGame() {
		mp = new MyPanel(this);
		this.setTitle("贪吃蛇");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);// 居中显示
		this.setResizable(false);// 不能重置大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mp);
		this.setVisible(true);
		new Thread(mp).start();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
					mp.opt = e.getKeyCode();
					break;
				case KeyEvent.VK_ENTER:
					if (mp.snake.isStop)
						mp.snake.reset();
				}

			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + "," + e.getY());
				int x = e.getX();
				int y = e.getY();
				if (mp.snake.isStop) {
					if (x > 120 && x < 270)
						if (y > 350 && y < 435)
							mp.snake.reset();
				}
			}
		});

	}

	public static void main(String[] args) {
		MyGame g = new MyGame();

	}

}
