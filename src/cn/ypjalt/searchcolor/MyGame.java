package cn.ypjalt.searchcolor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by 袁鹏竣 on 2017/11/17 0017.
 */
public class MyGame extends JPanel {
	int n = 2;
	Random random = new Random();
	int w = 600;
	int h = 600;
	Rectangle rt = new Rectangle();

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w + 25, h + 40);
		int[][] arr = new int[n][n];
		int r1 = random.nextInt(180) + 50;
		int g1 = random.nextInt(180) + 50;
		int b1 = random.nextInt(180) + 50;
		int r2 = r1 + 25;
		int g2 = g1 + 25;
		int b2 = b1 + 25;
		int pos = random.nextInt(n * n);
		int num = 0;
		g.setColor(new Color(r1, g1, b1));
		int xw = w / n;
		int xh = h / n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (pos == num) {
					g.setColor(new Color(r2, g2, b2));
					rt.x = j * xw + 10;
					rt.y = i * xh + 10;
					rt.width = xw - 10;
					rt.height = xh - 10;
				} else {
					g.setColor(new Color(r1, g1, b1));
				}
				g.fillRect(j * xw + 10, i * xh + 10, xw - 10, xh - 10);
				num++;
			}
		}

	}

	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setSize(628, 650);
		j.setLocationRelativeTo(null);// 居中显示
		// j.setResizable(false);//不能重置大小
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyGame g = new MyGame();
		j.setTitle("看你有多色_等级:" + (g.n - 1));
		j.add(g);
		j.setVisible(true);
		j.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX() - 7;
				int y = e.getY() - 30;
				if (x > g.rt.getX() && x < g.rt.getX() + g.rt.getWidth()) {
					if (y > g.rt.getY() && y < g.rt.getY() + g.rt.getHeight()) {
						g.n++;
						j.setTitle("看你有多色_等级:" + (g.n - 1));
						g.repaint();
					}
				}
			}
		});

	}

}
