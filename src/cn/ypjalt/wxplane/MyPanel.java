package cn.ypjalt.wxplane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Created by 袁鹏竣 on 2017/11/8 0008.
 */
public class MyPanel extends JPanel implements Runnable {
	Map map = new Map();
	Plane plane = new Plane();
	Vector<Bullet> bullets = new Vector<Bullet>();
	// 减少子弹创建密度
	int bulletCount = 0;
	int bulletStep = 5;

	Vector<Npc> npcs = new Vector<Npc>();
	// 敌机创建密度
	int npcCount = 0;
	static int npctStep = 50;
	int small = 0, middle = 0;

	Vector<Prop> props = new Vector<Prop>();
	int propCount = 0;
	int propStep = 150;

	static int score = 0;// 分数
	static int maxScore = 0;// 最高分数

	BufferedImage imageStart, en;

	int time = 0;

	public void restart() {
		plane.index = 0;
		plane.x = MyGame.width / 2 - plane.w / 2;
		plane.y = 400;
		plane.destroy = false;
		plane.remove = false;
		plane.double_bullet = false;
		plane.bomb = 0;
		plane.stop = false;
		score = 0;
		npctStep = 50;

	}

	public MyPanel() {
		try {
			imageStart = ImageIO.read(getClass().getResource("images/imgstart.png"));
			en = ImageIO.read(getClass().getResource("images/en.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void paint(Graphics g) {
		map.paint(g);
		plane.paint(g);
		if (!plane.remove) {
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).paint(g);
			}

			for (int i = 0; i < npcs.size(); i++) {
				npcs.get(i).paint(g);
			}
			for (int i = 0; i < props.size(); i++) {
				props.get(i).paint(g);
			}
		} else {
			bullets.clear();
			npcs.clear();
			props.clear();
		}
		g.setColor(Color.WHITE);
		Font font = new Font(Font.MONOSPACED, Font.BOLD, 28);
		g.setFont(font);
		maxScore = maxScore > score ? maxScore : score;
		g.drawString("当前分数:" + score, 5, 30);
		g.drawString("最高分:" + maxScore, 5, 60);

		// 游戏结束，显示开始游戏
		if (plane.remove) {
			g.drawImage(imageStart, MyGame.width / 2 - imageStart.getWidth() / 2,
					MyGame.height / 2 - imageStart.getHeight() / 2, null);
		}

		// 红色道具显示
		if (plane.bomb > 0) {
			g.drawImage(en, 10, 600, null);
			g.drawString("x" + plane.bomb, 10 + en.getWidth(), 620);
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			map.move();// 地图移动
			plane.move();// 飞机移动

			if (!plane.remove) {
				addBullet();// 创建子弹
				addNpc();// 创建敌机
				addProp();// 添加道具
			}
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).move(bullets, npcs);
			}
			for (int i = 0; i < npcs.size(); i++) {
				npcs.get(i).move(npcs, plane);

			}
			for (int i = 0; i < props.size(); i++) {
				props.get(i).move(props, plane);
			}
			// 双蛋计时器
			if (plane.double_bullet) {
				time++;
			}
			// 10s双蛋时间
			if (time > 500) {
				time = 0;
				plane.double_bullet = false;
			}
			repaint();
		}

	}

	public void addBullet() {
		bulletCount++;
		if (bulletCount % bulletStep == 0) {
			if (!plane.double_bullet) {
				Bullet bullet = new Bullet();
				bullet.x = plane.x + plane.w / 2 - bullet.w / 2;
				bullet.y = plane.y - 5;
				bullets.add(bullet);
				bullet.index = 0;
				bulletCount = 0;
			} else {
				Bullet bullet = new Bullet();
				bullet.x = plane.x + plane.w / 2 - bullet.w / 2 - 20;
				bullet.y = plane.y - 5;
				bullets.add(bullet);
				bullet = new Bullet();
				bullet.x = plane.x + plane.w / 2 - bullet.w / 2 + 20;
				bullet.y = plane.y - 5;
				bullets.add(bullet);
				bullet.index = 1;
				bulletCount = 0;

			}
		}

	}

	public void addNpc() {
		npcCount++;
		if (npcCount % npctStep == 0) {
			Npc npc = new Npc(0);
			small++;
			if (small > 5) {
				npc = new Npc(1);
				middle++;
				small = 0;
			}
			if (middle > 2) {
				npc = new Npc(2);
				small = 0;
				middle = 0;
			}
			npcs.add(npc);
			npcCount = 0;

		}
	}

	public void addProp() {
		propCount++;
		if (propCount % propStep == 0) {
			Prop prop = new Prop();
			props.add(prop);
			propCount = 0;
		}
	}
}
