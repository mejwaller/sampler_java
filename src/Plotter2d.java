//canvas is nice and simple and naive to draw on 
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

//this class shuld be in it's own package outside of sampler
public class Plotter2d extends Canvas {
    
    private List<Double> x;
    private List<Double> t;
    private Map<Double,Double> vals;
    private double xmax, xmin, ymax, ymin, xrange, yrange;
    
	
	public Plotter2d(){
	    x = new ArrayList<Double>();
	    t = new ArrayList<Double>();
	    vals = new HashMap<Double,Double>();
		//setSize(400, 800); 
		setBackground(Color.white); 
	} 
	
	public void setVals(List<Double> x, List<Double> t) {
	    
	    for(int i =0; i<x.size();i++) {
	        vals.put(x.get(i),t.get(i));
	    }
	    this.x=x;
	    this.t=t;
	    
	    //stupidly expensive way to get axes max and min
	    //need max and min really to ensure graph is correctly 'balanced' on screen
	    //what about multiple data sets? Beyind scope here for noew - assume x and single data set y = f(x)
	    //axes should be separate objects anyway
	    Collections.sort(x);//need max and mins for scaling
	    Collections.sort(t);  
	    xmin = x.get(0);
	    ymin = t.get(0);
	    xmax = x.get(x.size()-1);
	    ymax = t.get(t.size()-1);
	    xrange = xmax-xmin;
	    yrange = ymax - ymin;
	    System.out.println("min X is " + xmin + " and max X is " + xmax + " and xrange is " + xrange);
	    System.out.println("min Y is " + ymin + " and max y is " + ymax + " and yrange is " + yrange);
	    
	}
	
	@Override
	public void paint(Graphics g){ 
	    
	    System.out.println("Canvas size heigh is " + getHeight() + " and width is " + getWidth());
	    
	    //change to account for scaling
		g.setColor(Color.black); 
		//yuck - just yuck
		drawLine(g, 10, 10, 800, 10);
		drawLine(g, 10, 10, 10, 800);
		g.setColor(Color.red);
		drawLine(g, 10, 10, 800, 800); 
		
	}
	
	public void drawLine(Graphics g, int x1, int y1, int x2, int y2){
		g.drawLine(x1, getHeight()-y1, x2, getHeight()-y2);
	}
	
	@Override
	public void update(Graphics g)
	{
	    super.update(g);
	    paint(g);
	    
		System.out.println("repaint!");
	
	}
	
	//this gets called by awt sutomatically on e.g resize
	@Override
	public void invalidate() {
		System.out.println("Invalidate!");
		super.invalidate();
		update(getGraphics());
	}
	
	//0,0 is at top left by default - ensure transforms are made to set ymin at bottom left
	// and necessary scalings applied
	public void setYtransform() {
		
	}
	
	//ensure necessary sclaing are applied and any necessary transforms applied
	public void setXtransform() {
	}

}
