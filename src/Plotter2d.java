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
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;


public class Plotter2d extends Canvas {
	
	//Allow multiple XY data sets to plot
	//For ach data set, x is key, y is val
	private List<Map<Double, Double>> XYData = new Vector<Map<Double,Double>>();
        
    private double xmax, xmin, ymax, ymin, xrange, yrange;
    private int yaxismin, yaxismax,xaxismin, xaxismax, xaxisrange, yaxisrange;//store actual points 
    
    public enum Shape {
        RECT, ELLIPSE, CROSS, POLYGON
    }
    
	
	public Plotter2d(){
		setSize(1000, 1000); 
		setBackground(Color.white); 
	} 
	
	public void addXYData(Map<Double,Double> data, Shape shape) {
		
		
		XYData.add(data);
		
		SortedSet<Double> xvals = new TreeSet<Double>();
		SortedSet<Double> yvals = new TreeSet<Double>();
		
		
		//autocalculate  max and mins
		for(Map<Double,Double> dataset: XYData) {
			xvals.addAll(dataset.keySet());
			yvals.addAll(dataset.values());
			
		}
		
		xmax=xvals.last();
		xmin=xvals.first();
		ymax=yvals.last();
		ymin=yvals.first();
		xrange = xmax-xmin;
	    yrange = ymax-ymin;
		
		System.out.println("XYDATA: xmax is " + xmax + " xmin is " + xmin + " ymax is " + ymax + " ymin is " + ymin);
		
	}
	
	public void addXYData(Map<Double,Double> data, Shape shape, double xmax, double xmin, double ymax, double ymin) {
		XYData.add(data);
		this.xmax=xmax;
		this.xmin=xmin;
		this.ymax=ymax;
		this.ymin=ymin;
		xrange = xmax-xmin;
	    yrange = ymax-ymin;
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
		
		
		//TODO: remove shape from this call - needs ot be associted with each XY data set
		//(as does colour actually...)
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
		
		for(Map<Double, Double> data: XYData) { 
			switch(shape) {
	        	case ELLIPSE:
	        		plotEllipses(data);
	        		break;
	        	default:
	        		System.out.println("Shape not implemented");
			}	    
		}
	}
	
	public void plotEllipses(Map<Double,Double> vals)
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
