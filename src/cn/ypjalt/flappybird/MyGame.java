package cn.ypjalt.flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by 袁鹏竣 on 2017/11/13 0013.
 */
public class MyGame extends JPanel {

	BufferedImage background;
	BufferedImage imgRestart;
	BufferedImage imgStart;
	Ground ground;
	Bird bird;
	Column column1;
	Column column2;

	int score = 0;

	boolean gameover;
	boolean start;

	// 初始化内类数据成员
	public MyGame() throws IOException {
		background = ImageIO.read(getClass().getResource("Images/bg.png"));
		imgRestart = ImageIO.read(getClass().getResource("Images/gameover.png"));
		imgStart = ImageIO.read(getClass().getResource("Images/start.png"));
		start();

	}

	public void start() throws IOException {
		ground = new Ground();
		bird = new Bird();
		column1 = new Column(0);
		column2 = new Column(1);
		gameover = false;
		start = false;
		score = 0;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		// 绘制地面
		ground.paint(g);
		// 绘制小鸟
		bird.paint(g);
		// 绘制柱子
		column1.paint(g);
		column2.paint(g);
		if (gameover) {
			g.drawImage(imgRestart, 0, 0, null);
		}
		g.setColor(Color.WHITE);
		Font font = new Font(Font.MONOSPACED, Font.BOLD, 28);
		g.setFont(font);
		g.drawString("分数:" + score, 5, 30);
		if (!start) {
			g.drawImage(imgStart, 0, 0, null);
		}
	}

	public void action() throws InterruptedException {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bird.flappy();
				if (!start)
					try {
						start();
						start = true;
						gameover = false;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				if (start && gameover) {
					if (e.getX() > 130 && e.getX() < 285)
						if (e.getY() > 325 && e.getY() < 415)
							try {
								start();
								start = true;
							} catch (IOException e1) {
								e1.printStackTrace();
							}
				}
			}
		});

		while (true) {
			Thread.sleep(1000 / 60);
			if (!gameover)
				bird.fly();
			if (start && !gameover) {
				ground.step();
				bird.step();
				column1.step();
				column2.step();
			}
			if (bird.hit(ground, column1, column2))
				gameover = true;
			if (bird.pass(column1, column2))
				score += 100;
			repaint();
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		JFrame j = new JFrame();
		j.setTitle("FlayppyBird");
		j.setSize(432, 644 + 30);
		j.setLocationRelativeTo(null);// 居中显示
		j.setResizable(false);// 不能重置大小
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyGame w = new MyGame();
		j.add(w);
		j.setVisible(true);
		w.action();

	}

}
