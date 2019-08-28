package feiji项目;

import java.awt.Color;
import java.awt.Graphics;

public class SShell extends GameObject{
	double degree;
	
	public SShell() {
		x=200;
		y=200;
		width =10;
		height = 10;
		speed=3;
		degree = Math.random()*Math.PI*2;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.yellow);
		
		
		g.fillOval((int)x,(int)y, width, height);
		
		//飞行方向
		x += speed*Math.cos(degree);
		y +=speed*Math.sin(degree);
		
		if(x<0||x >Contstant.GAME_WIDH-2*width) {
			
			degree = Math.PI-degree;
		}
		if(y<30||y>Contstant.GAME_HEIGHT-2*height) {
			degree = -degree;
		}
		
		g.setColor(c);
	}

}
