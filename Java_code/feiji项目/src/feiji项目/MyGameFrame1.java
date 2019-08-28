package feiji项目;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;



public class MyGameFrame1 extends JFrame {
	
	Image bgImg = GameUtil.getImage("images/bg.jpg");
	Image planeImg = GameUtil.getImage("images/plane.png");
	
	int  plane_x=250,plane_y=250;
	
	@Override
	public void paint(Graphics g) {
	
		g.drawImage(bgImg, 0, 0, null);
		
		g.drawImage(planeImg,plane_x,plane_y,null);
		
		plane_x++;
		
		
	}
	class PaintThread extends Thread{
		
		public void run() {
			
			while(true) {
				repaint();
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
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
		
		new PaintThread().start();
		}
	public static void main(String[] args) {
		MyGameFrame1  f = new MyGameFrame1();
		f.launchFrame();
		
	}
}
