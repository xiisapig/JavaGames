package cn.ypjalt.wxplane;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/9 0009.
 */
public class Bullet {
	int x;
	int y;
	int w;
	int h;
	int step = 8;
	BufferedImage[] imgs = new BufferedImage[2];
	int index = 0;

	// 构造方法
	public Bullet() {
		try {
			imgs[0] = ImageIO.read(getClass().getResource("images/bullet.png"));
			imgs[1] = ImageIO.read(getClass().getResource("images/bullet1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		w = imgs[0].getWidth();
		h = imgs[0].getHeight();

	}

	// 绘制方法
	public void paint(Graphics g) {
		g.drawImage(imgs[index], x, y, null);

	}

	// 移动方法
	public void move(Vector<Bullet> bullets, Vector<Npc> npcs) {
		y -= step;
		if (y < -h) {
			bullets.remove(this);
		}
		// 碰撞检测
		for (int i = 0; i < npcs.size(); i++) {
			Npc npc = npcs.get(i);
			int nx = npc.x;
			int ny = npc.y;
			int nw = npc.w;
			int nh = npc.h;
			if (x > nx - w && x < nx + nw) {
				if (y < ny + nh && y > ny - h) {
					// 移除子弹
					bullets.remove(this);
					npc.hitCount++;
					npc.hit = true;
				}
			}
		}

	}
}
