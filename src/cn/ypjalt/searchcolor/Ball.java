package cn.ypjalt.searchcolor;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by 袁鹏竣 on 2017/11/17 0017.
 */
public class Ball extends JPanel {
	int width = 600;
	int height = 600;
	int ball_x, ball_y;
	int x, y;

	void reset() {
		ball_x = width / 2 - 30;
		ball_y = height / 2 + 60;
		x = width;
		y = ball_y + 30;
	}

	public Ball() {
		reset();

	}

	@Override
	public void paint(Graphics g) {
		g.fillOval(ball_x, ball_y, 60, 60);
		g.fillRect(x, y, 100, 20);

	}

	public static void main(String[] args) {
		JFrame j = new JFrame();
		Ball ball = new Ball();
		j.setSize(ball.width, ball.height);
		j.setLocationRelativeTo(null);// 居中显示
		j.setResizable(false);// 不能重置大小
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(ball);
		j.setVisible(true);
	}
}
