package cn.ypjalt.wxplane;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/9 0009.
 */
public class Npc {
	int x;
	int y;
	int w;
	int h;
	static int step = 5;
	int type;
	BufferedImage[] imgs;
	int index = 0;
	Random random = new Random();

	int npcCount = 0;
	int npctStep = 3;

	int hitCount = 0;
	boolean hit;
	boolean normal;
	boolean destroy;
	boolean remove;

	// 构造方法

	public Npc(int type) {
		// type 0 1 2 0-小飞机
		this.type = type;
		imgs = new BufferedImage[5 + type * type];
		for (int i = 0; i < imgs.length; i++) {
			try {
				imgs[i] = ImageIO.read(getClass().getResource("images/npc" + type + "_" + i + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		w = imgs[index].getWidth();
		h = imgs[index].getHeight();
		x = random.nextInt(MyGame.width - w);
		y = 0 - random.nextInt(400) - h;

	}

	// 绘制方法
	public void paint(Graphics g) {
		if (remove)
			return;
		g.drawImage(imgs[index], x, y, null);
		if (type == 2 || destroy) {
			npcCount++;
			if (npcCount % npctStep == 0) {
				index++;
				npcCount = 0;
			}
		}
		if (index > 2 && !destroy) {
			index = 0;
		}
		if (destroy && index >= imgs.length - 1) {
			remove = true;
		}

	}

	// 移动方法
	public void move(Vector<Npc> npcs, Plane plane) {
		if (remove) {
			npcs.remove(this);
			if (!plane.stop) {
				MyPanel.score += type * 100 + 100;
				if (MyPanel.score != 0 && MyPanel.score % 1000 == 0) {
					if (step <= 15)
						step++;
					if (MyPanel.npctStep >= 20)
						MyPanel.npctStep -= 5;

				}
				System.out.println("MyPanel.npctStep:" + MyPanel.npctStep);
				System.out.println("Npc.step:" + step);
			}

			return;
		}
		y += step;
		if (y > MyGame.height) {
			npcs.remove(this);
		}
		/**
		 * Type = 0 击中5次 Type = 1 击中10次 Type = 2 击中15次
		 */
		if (hitCount >= 5 && hitCount % ((type + 1) * 5) == 0) {
			destroy = true;

		}
		// 敌机与飞机的碰撞检测
		if (!plane.remove && !destroy) {
			if (x > plane.x - w && x < plane.x + plane.w) {
				if (y > plane.y - h && y < plane.y + plane.h) {
					destroy = true;// 通知敌机销毁
					plane.destroy = true;// 通知飞机销毁
				}
			}
		}
	}

}
