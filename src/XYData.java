import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class XYData extends PlotData{
	
	Plotter2d.Shape shape;
    	    	
    XYData(){
    	
    }
    	
    XYData(Map<Double,Double> points, Plotter2d.Shape ashape, Color acolour) {
    	
    	data = points;
    	shape= ashape;
   		colour = acolour;
   	}
    	
   	public void plotData(Graphics g, Axis xaxis, Axis yaxis) {
   		switch(shape) {
           	case ELLIPSE:
           		plotEllipses(g, data, xaxis, yaxis, colour);
           		break;
           	case RECT:
           		plotRects(g, data, xaxis, yaxis, colour);
           		break;
           	default:
           		System.out.println("Shape not implemented");
   		}	    
   	}
   	public void plotEllipses(Graphics g, Map<Double,Double> vals, Axis xaxis, Axis yaxis, Color colour) {
    	    
   	    int height=10;
   	    int width=10;
   	    for(Double key : vals.keySet()) {
   	        
   	        int xval = (int)(xaxis.xaxismin + ((key.doubleValue()-xmin)/xrange)*xaxis.xaxisrange);
   	        int yval = yaxis.canvasHeight - (int)(yaxis.yaxismin + ((vals.get(key)-ymin)/yrange)*yaxis.yaxisrange);
   	        
   	        g.setColor(colour);
   	        g.drawOval(xval-width/2,yval-width/2,height,width);
 
   	    }
    	
   	}
   	
   	public void plotRects(Graphics g, Map<Double,Double> vals, Axis xaxis, Axis yaxis,  Color colour) {
   		int height=10;
   	    int width=10;
   	    for(Double key : vals.keySet()) {
   	        
   	        int xval = (int)(xaxis.xaxismin + ((key.doubleValue()-xmin)/xrange)*xaxis.xaxisrange);
   	        int yval = yaxis.canvasHeight - (int)(yaxis.yaxismin + ((vals.get(key)-ymin)/yrange)*yaxis.yaxisrange);
   	        
   	        g.setColor(colour);
   	        g.drawRect(xval-width/2,yval-width/2,height,width);
   	    }
    		
   	}
 }