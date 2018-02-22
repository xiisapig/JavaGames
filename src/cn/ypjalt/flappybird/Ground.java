package cn.ypjalt.flappybird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/13 0013.
 */
public class Ground {
	int x;
	int y;
	int w;
	int h;
	BufferedImage img;

	public Ground() throws IOException {
		x = 0;
		y = 500;
		img = ImageIO.read(getClass().getResource("Images/ground.png"));
		w = img.getWidth();
		h = img.getHeight();

	}

	public void paint(Graphics g) {
		g.drawImage(img, x, y, null);

	}

	public void step() {
		x--;
		if (x <= -110) {
			x = 0;
		}
	}
}
