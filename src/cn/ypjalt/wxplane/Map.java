package cn.ypjalt.wxplane;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/8 0008.
 */
public class Map {
	int x;
	int y;
	int w;
	int h;
	int step = 2;
	BufferedImage img;

	// 构造方法
	public Map() {

		x = y = 0;
		try {
			img = ImageIO.read(getClass().getResource("images/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		w = img.getWidth();
		h = img.getHeight();

	}

	// 绘制方法
	public void paint(Graphics g) {
		g.drawImage(img, x, y - MyGame.height, null);
		g.drawImage(img, x, y, null);

	}

	// 移动方法
	public void move() {
		y += step;
		if (y > MyGame.height)
			y = 0;

	}

}
