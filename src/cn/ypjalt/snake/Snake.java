package cn.ypjalt.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * Created by 袁鹏竣 on 2017/11/16 0016.
 */
public class Snake {
	int x;
	int y;
	int w = 20;
	int h = 20;
	int direction = KeyEvent.VK_RIGHT;
	Vector<Rectangle> rts = new Vector<Rectangle>();
	boolean isStop;
	int level = 1;

	MyGame jf = null;

	void reset() {
		isStop = false;

		direction = KeyEvent.VK_RIGHT;
		rts.clear();
		x = 10 * w;
		y = 15 * h;
		Rectangle rt1 = new Rectangle(x - w, y, 16, 16);
		Rectangle rt2 = new Rectangle(x - w * 2, y, 16, 16);
		rts.add(rt1);
		rts.add(rt2);
		level = 1;
		jf.setTitle("贪吃蛇Level:" + level);

	}

	public Snake(MyGame jf) {
		this.jf = jf;
		reset();
		isStop = true; // 第一次运行游戏是停止状态
	}

	public void paint(Graphics g) {
		// 绘制蛇头
		g.setColor(Color.BLACK);
		g.drawRect(x + 2, y + 2, 16, 16);
		;
		g.setColor(Color.RED);
		g.drawRect(x + 5, y + 5, 11, 11);
		g.setColor(Color.WHITE);
		g.fillRect(x + 9, y + 9, 3, 3);
		// 绘制蛇节
		for (int i = 0; i < rts.size(); i++) {
			Rectangle rt = rts.get(i);
			g.setColor(new Color(0x444444));
			g.drawRect(rt.x + 2, rt.y + 2, 16, 16);
			g.drawRect(rt.x + 5, rt.y + 5, 11, 11);
			g.setColor(Color.WHITE);
			g.drawRect(rt.x + 9, rt.y + 9, 3, 3);

		}

	}

	public void step(int opt, Food food) {
		if (isStop)
			return;
		if (x < 0 || x > MyGame.width - w || y < 0 || y > MyGame.height - h) {
			isStop = true;
			return;
		}
		// 碰撞检测
		if (x == food.x && y == food.y) {
			int newX = 0, newY = 0;
			// 当前方向是左
			if (direction == KeyEvent.VK_LEFT) {
				newX = rts.get(rts.size() - 1).x + w;
				newY = rts.get(rts.size() - 1).y;
			}
			if (direction == KeyEvent.VK_RIGHT) {
				newX = rts.get(rts.size() - 1).x + w;
				newY = rts.get(rts.size() - 1).y;
			}
			if (direction == KeyEvent.VK_UP) {
				newX = rts.get(rts.size() - 1).x;
				newY = rts.get(rts.size() - 1).y + h;
			}
			if (direction == KeyEvent.VK_DOWN) {
				newX = rts.get(rts.size() - 1).x;
				newY = rts.get(rts.size() - 1).y - h;
			}
			rts.add(new Rectangle(newX, newY, 16, 16));
			level++;
			food.creatNew(this);
			jf.setTitle("贪吃蛇Level:" + level);

		}

		int oldx = x;
		int oldy = y;

		if (opt == KeyEvent.VK_LEFT && direction == KeyEvent.VK_RIGHT)
			opt = direction;
		if (opt == KeyEvent.VK_UP && direction == KeyEvent.VK_DOWN)
			opt = direction;
		if (opt == KeyEvent.VK_RIGHT && direction == KeyEvent.VK_LEFT)
			opt = direction;
		if (opt == KeyEvent.VK_DOWN && direction == KeyEvent.VK_UP) {
			opt = direction;
		}

		// 蛇头坐标
		switch (opt) {
		case KeyEvent.VK_LEFT:
			x -= w;
			break;
		case KeyEvent.VK_UP:
			y -= h;
			break;
		case KeyEvent.VK_RIGHT:
			x += w;
			break;
		case KeyEvent.VK_DOWN:
			y += w;
			break;
		}
		// 保存本次移动后的方向
		direction = opt;
		// 移动蛇节
		for (int i = rts.size() - 1; i > 0; i--) {
			rts.get(i).x = rts.get(i - 1).x;
			rts.get(i).y = rts.get(i - 1).y;
		}
		// 移动第一节 坐标为蛇头坐标
		rts.get(0).x = oldx;
		rts.get(0).y = oldy;
	}
}
