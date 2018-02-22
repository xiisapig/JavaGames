package cn.ypjalt.wxplane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Created by 袁鹏竣 on 2017/11/8 0008.
 */
public class MyGame extends JFrame {
	static int width = 480;
	static int height = 652;

	MyPanel mp = new MyPanel();

	public MyGame() {
		this.setTitle("微信飞机大战");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);// 居中显示
		this.setResizable(false);// 不能重置大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(mp);

		this.setVisible(true);

		Thread t = new Thread(mp);
		t.start();

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// 开始游戏图片的坐标
				if (mp.plane.stop)
					if (x > 170 && x < 310) {
						if (y > 330 && y < 380) {
							mp.restart();
						}
					}
			}

		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					mp.plane.up = true;

					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					mp.plane.down = true;
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					mp.plane.left = true;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					mp.plane.right = true;
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					mp.plane.up = false;
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					mp.plane.down = false;
					break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					mp.plane.left = false;
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					mp.plane.right = false;
					break;
				case KeyEvent.VK_CONTROL:
					// 释放炸弹
					if (mp.plane.bomb > 0 && !mp.plane.remove) {
						mp.plane.bomb--;
						for (int i = 0; i < mp.npcs.size(); i++) {
							mp.npcs.get(i).destroy = true;
						}
					}
					break;
				}

			}
		});

	}

	public static void main(String[] args) {
		MyGame game = new MyGame();
		game.mp.plane.remove = true;
		try {
			game.mp.plane.readData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
