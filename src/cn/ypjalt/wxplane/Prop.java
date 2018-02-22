package cn.ypjalt.wxplane;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * Created by ypjalt on 2017/11/10.
 */
public class Prop {
	int x;
	int y;
	int w;
	int h;
	BufferedImage[] images = new BufferedImage[2];
	Random r = new Random();
	int type;
	int step = 4;

	boolean remove;

	/**
	 * 构造方法 初始化xywh及加载图片
	 */
	public Prop() {
		try {
			images[0] = ImageIO.read(getClass().getResource("images/blue.png"));
			images[1] = ImageIO.read(getClass().getResource("images/red.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		type = r.nextInt(2);
		w = images[type].getWidth();
		h = images[type].getHeight();
		x = r.nextInt(MyGame.width - w);
		y = 0 - h;
	}

	// 绘制方法
	public void paint(Graphics g) {
		g.drawImage(images[type], x, y, null);
	}

	// 移动方法
	public void move(Vector<Prop> props, Plane plane) {
		y += step;
		if (y > MyGame.height)
			props.remove(this);
		// 💥检测
		if (!remove) {
			if (x > plane.x - w && x < plane.x + plane.w) {
				if (y > plane.y - h && y < plane.y + plane.h) {
					// 通知道具移除
					props.remove(this);
					remove = true;
					// 根据type，配置飞机的相应的道具
					if (type == 0) {
						plane.double_bullet = true;
					} else {
						plane.bomb++;
					}
				}
			}
		}
	}

}
