//graphics experimentation
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class plotter2d extends Canvas {
	
	public plotter2d(){
		//setSize(400, 800); 
		setBackground(Color.white); 
	} 
	
	public void paint(Graphics g){ 
		g.setColor(Color.black); 
		drawLine(g, 10, 10, 800, 10);
		drawLine(g, 10, 10, 10, 800);
		g.setColor(Color.red);
		drawLine(g, 10, 10, 800, 800); 
		
		//g.drawRect(20, 150, 100, 100); 
		//g.fillRect(20, 150, 100, 100); 
		//g.fillOval(150, 20, 100, 100); 
	}
	
	public void drawLine(Graphics g, int x1, int y1, int x2, int y2){
		g.drawLine(x1, getHeight()-y1, x2, getHeight()-y2);
	}
	
	public void update(Graphics g)
	{
		System.out.println("repaint!");
	
	}
}
