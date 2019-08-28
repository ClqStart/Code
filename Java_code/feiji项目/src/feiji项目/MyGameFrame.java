package feiji项目;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MyGameFrame extends JFrame {
	
	Image ball = GameUtil.getImage("imag/timg1.jpg");
	
	@Override
	public void paint(Graphics g) {
		Color  c = g.getColor();
		Font  f = g.getFont();
		g.setColor(Color.RED);
		g.drawLine(100, 100, 300, 300);
		g.drawRect(100, 100, 300, 300);
		g.drawOval(100, 100, 300, 300);
		g.setFont(new Font("宋体",Font.BOLD,50));
		g.drawString("hello", 200,200);
		g.fillOval(100, 100, 30, 30);
		g.drawImage(ball, 0, 0, null);
		
		
		g.setColor(c);
		g.setFont(f);
	}

	
	public void launchFrame() {
		this.setTitle("陈玲琦");
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocation(100,100);
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		}
	public static void main(String[] args) {
		MyGameFrame  f = new MyGameFrame();
		f.launchFrame();
		
	}
}
