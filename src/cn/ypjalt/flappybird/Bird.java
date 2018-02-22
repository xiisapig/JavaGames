package cn.ypjalt.flappybird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/13 0013.
 */
public class Bird {
	int x;
	int y;
	int size = 40;
	BufferedImage[] images;
	BufferedImage image;
	int index;
	// 倾斜的角度
	double angle;
	// 重力加速度
	double g;
	// 时间
	double t;
	// 初始加速度
	double v0;
	// 当前速度
	double speed;
	// 移动距离
	double s;

	public Bird() throws IOException {
		images = new BufferedImage[8];
		for (int i = 0; i < images.length; i++) {
			images[i] = ImageIO.read(getClass().getResource("Images/" + i + ".png"));
		}
		image = images[0];
		index = 0;
		x = 150;
		y = 300;
		g = 4;
		t = 0.25;
		v0 = 20;

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(angle, x, y);
		g.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
		g2.rotate(-angle, x, y);

	}

	// 扇翅膀
	public void fly() {
		index++;
		image = images[index / 8 % 8];
		if (index > Integer.MAX_VALUE)
			index = 0;
	}

	// 上抛加自由落体
	public void step() {
		// 本次的初始速度 是上一次运动的速度
		double v0 = speed;
		// 上抛速度计算v0-gt
		double v = v0 - g * t;
		// 上抛距离 s= v0*t-1/2g*t*t
		s = v0 * t - 1.0 / 2 * g * t * t;
		// 计算坐标
		y = y - (int) s;
		angle = -Math.atan(s / 8);
		// 记录当前速度
		speed = v;
	}

	public void flappy() {
		speed = v0;
	}

	// 是否通过柱子
	public boolean pass(Column col1, Column col2) {
		return x == col1.x || x == col2.x;
	}

	// 与柱子碰撞
	public boolean hit(Column col) {
		if (x > col.x - col.w / 2 - size / 2 && x < col.x + col.w / 2 + size / 2) {
			if (y > col.y - col.gap / 2 + size / 2 && y < col.y + col.gap / 2 - size / 2) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	// 与地面碰撞
	public boolean hit(Ground ground, Column col1, Column col2) {
		if (y > ground.y - size / 2)
			return true;
		return hit(col1) || hit(col2);
	}

}
