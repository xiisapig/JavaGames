package cn.ypjalt.fish;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/14 0014.
 */
public class Fish {
	int x, y, w, h, index, step;
	Random r = new Random();
	BufferedImage[] imgs;
	boolean hit;
	int hitCount = 0;
	int num;
	int time = 0;
	int score = 0;

	public Fish(int num) {
		this.num = num;
		score = num * 10;

		step = r.nextInt(5) + 1;
		if (num < 8)
			imgs = new BufferedImage[12];
		else
			imgs = new BufferedImage[14];

		String _name;
		if (num < 10) {
			_name = "0" + num;
		} else {
			_name = "" + num;
		}
		for (int i = 0; i < imgs.length; i++) {
			if (i < 10)
				try {
					imgs[i] = ImageIO.read(getClass().getResource("images/fish" + _name + "_0" + i + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			else
				try {
					imgs[i] = ImageIO
							.read(getClass().getResource("images/fish" + _name + "_catch_0" + (i - 9) + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}

		}

		index = 0;
		w = imgs[index].getWidth();
		h = imgs[index].getHeight();
		x = 800;
		y = r.nextInt(480 - h);

	}

	public void paint(Graphics g) {
		if (!hit) {
			g.drawImage(imgs[index++ / (6 - step) % 10], x, y, null);
		} else {
			// 挣扎的图片
			g.drawImage(imgs[index++ / (6 - step) % (imgs.length - 10) + 10], x, y, null);
			time++;
		}

	}

	public void step(Vector<Fish> fish, Vector<Fish> fish2) {
		if (time > 50 && hitCount > num - 8) {
			x = -w - 10;
			time = 0;
			MyPanel.score += score;
		}
		if (!hit || time > 50) {
			x -= step;
			hit = false;
			time = 0;
		}
		if (x < -w) {
			fish.remove(this);
			fish2.add(this);
			x = 800;
			y = r.nextInt(480 - h);
			hit = false;// 捕捉状态为false
			hitCount = 0;
		}

	}

}
