package cn.ypjalt.fish;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Created by 袁鹏竣 on 2017/11/14 0014.
 */
public class MyGame extends JFrame {
	MyPanel mp;

	public MyGame() throws IOException {
		this.setTitle("捕鱼达人");
		this.setSize(800, 480 + 30);
		this.setLocationRelativeTo(null);// 居中显示
		this.setResizable(false);// 不能重置大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mp = new MyPanel();
		this.add(mp);
		this.setVisible(true);
		Thread t = new Thread(mp);
		t.start();

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX() - mp.net.w / 2 - 10;
				int y = e.getY() - mp.net.h / 2 - 30;
				if (!mp.net.Show)
					mp.net.show(x, y, mp.fishs, mp.fishs2);
			}
		});

	}

	public static void main(String[] args) throws IOException {
		MyGame game = new MyGame();

	}
}
