package cn.ypjalt.flappybird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/13 0013.
 */
public class Column {
	int x;
	int y;
	int w;
	int h;
	int gap = 144;
	int distance = 245;
	BufferedImage img;
	Random r = new Random();

	// 构造方法
	public Column(int index) throws IOException {
		img = ImageIO.read(getClass().getResource("Images/column.png"));
		w = img.getWidth();
		h = img.getHeight();
		x = 432 + index * distance;
		y = r.nextInt(220) + 120;

	}

	// 绘制方法
	public void paint(Graphics g) {
		g.drawImage(img, x - w / 2, y - h / 2, null);

	}

	// 移动方法
	public void step() {
		x--;
		if (x < -w / 2) {
			x = distance * 2;
			y = r.nextInt(220) + 120;
		}
	}

}
