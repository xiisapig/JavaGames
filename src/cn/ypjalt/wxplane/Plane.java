package cn.ypjalt.wxplane;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by 袁鹏竣 on 2017/11/8 0008.
 */
public class Plane {
	int x;
	int y;
	int w;
	int h;
	BufferedImage[] imgs = new BufferedImage[6];
	int index = 0;
	int planeCount = 0;
	int planeStep = 5;

	boolean left, right, up, down;// 默认值false

	boolean destroy;
	boolean remove;

	boolean stop;// 飞机坠毁后，子弹射出的无效

	boolean double_bullet;// 两个子弹的标志
	int bomb;
	int type;// 吃到道具的类型

	// 构造方法
	public Plane() {
		try {
			for (int i = 0; i < 6; i++) {
				imgs[i] = ImageIO.read(getClass().getResource("images/plane0" + (i + 1) + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		w = imgs[index].getWidth();
		h = imgs[index].getHeight();
		x = MyGame.width / 2 - w / 2;
		y = 400;

	}

	// 绘制方法
	public void paint(Graphics g) {
		if (remove) {
			stop = true;
			return;
		}
		g.drawImage(imgs[index], x, y, null);
		planeCount++;
		if (planeCount % planeStep == 0) {
			index++;
			planeCount = 0;
		}
		if (index >= 2 && !destroy) {
			index = 0;
		}
		// 进入销毁状态并索引到最后一张
		if (destroy && index >= imgs.length - 1) {
			try {
				readData();
			} catch (IOException e) {
				e.printStackTrace();
			}
			remove = true;
		}

	}

	// 移动方法
	public void move() {
		if (left)
			x -= 15;
		if (up)
			y -= 15;
		if (right)
			x += 15;
		if (down)
			y += 15;
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x > MyGame.width - w)
			x = MyGame.width - w;
		if (y > MyGame.height - h - 20)
			y = MyGame.height - h - 20;
	}

	// 读取数据
	public void readData() throws IOException {
		File file = new File("Count.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		// 保证文件存在
		FileReader fr = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fr);
		String strNum = bufferedReader.readLine();
		int num = 0;
		if (strNum != null) {
			num = Integer.parseInt(strNum);
		}
		bufferedReader.close();
		fr.close();

		MyPanel.maxScore = MyPanel.score > num ? MyPanel.score : num;
		FileWriter fw = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fw);
		bufferedWriter.write(MyPanel.maxScore + "");
		bufferedWriter.close();
		fw.close();

	}

}
