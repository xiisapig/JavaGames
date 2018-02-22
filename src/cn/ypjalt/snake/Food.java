package cn.ypjalt.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

/**
 * Created by 袁鹏竣 on 2017/11/16 0016.
 */
public class Food {
	int x;
	int y;
	Random r = new Random();

	public Food(Snake snake) {
		creatNew(snake);
	}

	public void creatNew(Snake snake) {
		Vector<Point> pts = new Vector<Point>();
		Point pt = new Point(snake.x, snake.y);
		pts.add(pt);
		for (int i = 0; i < snake.rts.size(); i++) {
			Point pt2 = new Point();
			pt2.x = snake.rts.get(i).x;
			pt2.y = snake.rts.get(i).y;
			pts.add(pt2);
		} // 蛇的所有坐标都放到pts中

		boolean flag;
		do {
			flag = false;
			x = r.nextInt(20) * 20;
			y = r.nextInt(30) * 20;
			for (int i = 0; i < pts.size(); i++) {
				if (x == pts.get(i).x && y == pts.get(i).y)
					flag = true;
			}

		} while (flag);

	}

	public void paint(Graphics g) {
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.drawRect(x + 2, y + 2, 16, 16);
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.drawRect(x + 5, y + 5, 11, 11);
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.fillRect(x + 9, y + 9, 3, 3);
	}

}
