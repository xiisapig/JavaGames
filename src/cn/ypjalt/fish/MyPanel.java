package cn.ypjalt.fish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Created by 袁鹏竣 on 2017/11/14 0014.
 */
public class MyPanel extends JPanel implements Runnable {

	public static int score;

	BufferedImage bg;
	Fish fish;

	int fishCount = 0;
	int fishStep = 30;
	Vector<Fish> fishs = new Vector<Fish>();
	Vector<Fish> fishs2 = new Vector<Fish>();
	Net net = new Net();

	Random r = new Random();

	public MyPanel() throws IOException {
		bg = ImageIO.read(getClass().getResource("images/bg.jpg"));
		creatFish();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.setColor(Color.WHITE);
		Font font = new Font(Font.MONOSPACED, Font.BOLD, 28);
		g.setFont(font);
		g.drawString("分数:" + score, 5, 30);
		for (int i = 0; i < fishs.size(); i++) {
			fishs.get(i).paint(g);
		}
		net.paint(g);

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			addFish();
			for (int i = 0; i < fishs.size(); i++) {
				fishs.get(i).step(fishs, fishs2);
			}
			System.out.println(fishs.size());
			System.out.println(fishs2.size());
			repaint();
		}
	}

	public void creatFish() {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 14 };
		for (int i = 0; i < 55; i++) {
			Fish fish = new Fish(arr[i % arr.length]);
			fishs2.add(fish);
		}

	}

	void addFish() {
		fishCount++;
		if (fishCount % fishStep == 0 && fishs2.size() > 0) {
			int pos = r.nextInt(fishs2.size());
			Fish fish = fishs2.get(pos);
			fishs2.remove(fish);
			fishs.add(fish);
			fishCount = 0;
		}
	}

}
