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


public class Plotter2d extends Canvas {
    
    private List<Double> x;
    private List<Double> y;
    private Map<Double,Double> vals;
    private double xmax, xmin, ymax, ymin, xrange, yrange;
    private int yaxismin, yaxismax,xaxismin, xaxismax, xaxisrange, yaxisrange;//store actual points 
    
    public enum Shape {
        RECT, ELLIPSE, CROSS, POLYGON
    }
    
	
	public Plotter2d(){
	    x = new ArrayList<Double>();
	    y = new ArrayList<Double>();
	    vals = new HashMap<Double,Double>();//<x,y> pairs
		setSize(1000, 1000); 
		setBackground(Color.white); 
	} 
	
	
	//overload to manually set min, max for x and y if desired
	public void setVals(List<Double> d, List<Double> t, double xmax, double xmin, double ymax, double ymin) {
	    storeVals(d,t);
	    this.xmax=xmax;
	    this.xmin=xmin;
	    this.ymax=ymax;
	    this.ymin=ymin;
	    xrange = xmax-xmin;
	    yrange = ymax-ymin;
	    
	    System.out.println("min X is " + xmin + " and max X is " + xmax + " and xrange is " + xrange);
        System.out.println("min Y is " + ymin + " and max y is " + ymax + " and yrange is " + yrange);
	}
	
	public void setVals(List<Double> d, List<Double> t) {
	    
	    storeVals(d,t);
	    
	    this.x=d;
	    this.y=t;
	    
	    //stupidly expensive way to get axes max and min
	    //need max and min really to ensure graph is correctly 'balanced' on screen
	    //what about multiple data sets? Beyind scope here for noew - assume x and single data set y = f(x)
	    //axes should be separate objects anyway
	    Collections.sort(x);//need max and mins for scaling
	    Collections.sort(y);  
	    xmin = x.get(0);
	    ymin = y.get(0);
	    xmax = x.get(x.size()-1);
	    ymax = y.get(y.size()-1);
	    xrange = xmax-xmin;
	    yrange = ymax-ymin;
	    System.out.println("min X is " + xmin + " and max X is " + xmax + " and xrange is " + xrange);
	    System.out.println("min Y is " + ymin + " and max y is " + ymax + " and yrange is " + yrange);    
	}
	
	public void storeVals(List<Double> d, List<Double> t) {
	    for(int i =0; i < d.size();i++) {
            vals.put(d.get(i),t.get(i));
        }	    
	}
	
	@Override
	public void paint(Graphics g){ 
	    
	    System.out.println("Canvas size heigh is " + getHeight() + " and width is " + getWidth());	    
	    yaxismin=getHeight()/10;
	    yaxismax=getHeight()-getHeight()/10;
	    xaxismin=getWidth()/10;
	    xaxismax=getWidth()-getWidth()/10;
	    yaxisrange = yaxismax-yaxismin;
	    xaxisrange = xaxismax-xaxismin;
	    
	    //change to account for scaling
		g.setColor(Color.black); 
		//leave 10% whitespace margin top, bottom, right and left
		drawLine(g,xaxismin,yaxismin,xaxismax,yaxismin);
		drawLine(g,xaxismin,yaxismin,xaxismin,yaxismax);
		
		plotData(Shape.ELLIPSE);
		
		
		//yuck - just yuck
		//drawLine(g, 10, 10, 800, 10);
		//drawLine(g, 10, 10, 10, 800);
		//g.setColor(Color.red);
		//drawLine(g, 10, 10, 800, 800); 
		
	}
	
	public void drawLine(Graphics g, int x1, int y1, int x2, int y2){
	  //yaxis is inverted to get 'standard' axis with y going up rather tha device axis where y goes down.
		g.drawLine(x1, getHeight()-y1, x2, getHeight()-y2);
	}
	
	@Override
	public void update(Graphics g)
	{
	    super.update(g);
	    paint(g);
	    
		//System.out.println("repaint!");
	
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
	
	public void plotData(Shape shape) {
	    switch(shape) {
	        case ELLIPSE:
	            plotEllipses();
	            break;
	        default:
	            System.out.println("Shape not implemented");
	    }
	}
	
	public void plotEllipses()
	{
	    
	    int height=10;
	    int width=10;
	    for(Double key : vals.keySet()) {
	        
	        int xval = (int)(xaxismin + ((key.doubleValue()-xmin)/xrange)*xaxisrange);
	        int yval = getHeight() - (int)(yaxismin + ((vals.get(key)-ymin)/yrange)*yaxisrange);
	        	        
	        getGraphics().drawOval(xval-width/2,yval-width/2,height,width);
	        
	        /*System.out.println("xaxismin = " + xaxismin + " yaxismin = " + yaxismin + " xaxismax = " + xaxismax + " yaxismax = " + yaxismax );
	        System.out.println("xrange = " + xrange + " and yrange = " + yrange);
	        System.out.println("xval = xaxismin (" + xaxismin + ") + ((x (" + key.doubleValue() + ") - xmin (" + xmin + ")) (" + (key.doubleValue() - xmin) + ") / xrange (" + xrange + ")) * xaxisrange (" + xaxisrange + ")");
	        System.out.println("Plotting x = " + xval + " and y = " + yval );
	        */	        
	    }
	
	}

}
