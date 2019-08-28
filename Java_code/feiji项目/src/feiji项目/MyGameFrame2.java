package feiji项目;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;



public class MyGameFrame2 extends Frame {
	
	Image bgImg = GameUtil.getImage("images/bg.jpg");
	Image planeImg = GameUtil.getImage("images/plane.png");

	Plane plane = new Plane(planeImg, 250, 250);
	SShell[] shell = new SShell[50];
	Explode bao;
	
	Date startTime = new Date();
	Date  endTime;
	int period;
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
	
	
		g.drawImage(bgImg, 0, 0, null);
		
		plane.drawSelf(g);
		
		for(int i=0;i<shell.length;i++) {
			shell[i].draw(g);
			boolean peng = shell[i].getRect().intersects(plane.getRect());
			
			if(peng) {
				plane.live = false;
				if(bao ==null) {
					bao = new  Explode(plane.x, plane.y);
					endTime = new Date();
					System.out.println(endTime);
					
					period = (int)((endTime.getTime()-startTime.getTime())/1000);
					}
				bao.draw(g);
			}
			
			if(! plane.live) {
				g.setColor(Color.red);
				Font f = new Font("宋体",Font.BOLD,30); 
				g.setFont(f);
				g.drawString("时间："+period +"秒", (int)plane.x,(int)plane.y);
			}
		}
		
		
		
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
	
	class keyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
	}

	
	public void launchFrame() {
		this.setTitle("陈玲琦");
		this.setVisible(true);
		this.setSize(Contstant.GAME_WIDH,Contstant.GAME_HEIGHT);
		this.setLocation(100,100);
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();
		addKeyListener(new keyMonitor());
		
		//初始化50炮弹
		
		for(int i=0;i<shell.length;i++) {
			
			shell[i] = new SShell();
		}
		
		}
	public static void main(String[] args) {
		MyGameFrame2  f = new MyGameFrame2();
		f.launchFrame();
		
	}
	
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Contstant.GAME_WIDH,Contstant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
	
}
