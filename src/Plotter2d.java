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
	private List<PlotData> xydata = new Vector<PlotData>();
	private Axis xaxis = new Axis();
	private Axis yaxis = new Axis();
	public double xmax,xmin,ymax,ymin,xrange,yrange;
        
    public enum Shape {
        RECT, ELLIPSE, CROSS, POLYGON
    }
    
    
    
	
	public Plotter2d(){
		setSize(1000, 1000); 
		setBackground(Color.white); 
	} 
	
	public void addCurve(Function f, Color acolour, double xmax, double xmin, double ymax, double ymin) {
		
		CurveData data = new CurveData(f, acolour);
		data.xmax=this.xmax=xmax;
		data.xmin=this.ymin=xmin;
		data.ymax=this.ymax=ymax;
		data.ymin=this.ymin=ymin;
		data.xrange = this.xrange = xmax-xmin;
		data.yrange = this.yrange = ymax-ymin;
		xydata.add(data);
		
	}
	
	public void addXYData(Map<Double,Double> data, Shape shape, Color colour) {
						
		xydata.add(new XYData(data, shape, colour));
		
		SortedSet<Double> xvals = new TreeSet<Double>();
		SortedSet<Double> yvals = new TreeSet<Double>();
		
		
		//autocalculate  max and mins
		for(PlotData dataset: xydata) {
			xvals.addAll(dataset.data.keySet());
			yvals.addAll(dataset.data.values());
			
		}
		
		//er...crap..should be 1 instance of max,min, rnage data shared by all...
		for(PlotData dataset: xydata) {
			dataset.xmax=xmax=xvals.last();
			dataset.xmin=xmin-xvals.first();
			dataset.ymax=ymax=yvals.last();
			dataset.ymin=ymin=yvals.first();
			dataset.xrange = xrange = dataset.xmax-dataset.xmin;
			dataset.yrange = yrange = dataset.ymax-dataset.ymin;
		}
		
	}
	
	public void addXYData(Map<Double,Double> somedata, Shape shape, Color colour, double xmax, double xmin, double ymax, double ymin) {
		XYData data = new XYData(somedata,shape,colour);
		data.xmax=xmax;
		data.xmin=xmin;
		data.ymax=ymax;
		data.ymin=ymin;
		data.xrange = xmax-xmin;
		data.yrange = ymax-ymin;
		xydata.add(data);
		
	}
	
	@Override
	public void paint(Graphics g){ 
	    
	    System.out.println("Canvas size height is " + getHeight() + " and width is " + getWidth());	    
	    yaxis.yaxismin=getHeight()/10;
	    yaxis.yaxismax=getHeight()-getHeight()/10;
	    xaxis.xaxismin=getWidth()/10;
	    xaxis.xaxismax=getWidth()-getWidth()/10;
	    yaxis.yaxisrange = yaxis.yaxismax-yaxis.yaxismin;
	    xaxis.xaxisrange = xaxis.xaxismax-xaxis.xaxismin;
	    yaxis.canvasHeight = getHeight();
	    
	    //change to account for scaling
		g.setColor(Color.black); 
		//leave 10% whitespace margin top, bottom, right and left
		drawLine(g,xaxis.xaxismin,yaxis.yaxismin,xaxis.xaxismax,yaxis.yaxismin);
		drawLine(g,xaxis.xaxismin,yaxis.yaxismin,xaxis.xaxismin,yaxis.yaxismax);
		
		for(PlotData data: xydata) {	
			data.plotData(g,xaxis,yaxis);
		}
		
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

}

