package cn.ypjalt.fish;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/14 0014.
 */
public class Net {
	int x, y, w, h;
	BufferedImage img;
	boolean Show;
	int time = 0;

	public Net() {
		try {
			img = ImageIO.read(getClass().getResource("images/net09.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		w = img.getWidth();
		h = img.getHeight();

	}

	public void paint(Graphics g) {
		if (Show) {
			g.drawImage(img, x, y, null);
			time++;
		}
		if (time > 50) {
			Show = false;
			time = 0;
		}

	}

	public void show(int x, int y, Vector<Fish> fishs, Vector<Fish> fish2) {
		this.x = x;
		this.y = y;
		int x1 = this.x + w / 2 + 10;
		int y1 = this.y + h / 2 + 30;
		Show = true;

		for (int i = 0; i < fishs.size(); i++) {
			int fx = fishs.get(i).x + fishs.get(i).w / 2;
			int fy = fishs.get(i).y + fishs.get(i).h / 2;
			if (fx > x1 - 60 && fx < x1 + 60)
				if (fy > y1 - 60 && fy < y1 + 60) {
					// 换鱼挣扎的图片
					// 从容器fish中删除
					// 转移放到fish2中
					// 评分系统
					fishs.get(i).hitCount++;
					fishs.get(i).hit = true;
				}
		}

	}

}
