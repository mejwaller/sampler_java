import java.awt.Graphics;
import java.awt.Color;

public class CurveData extends PlotData {
	
	private Function f;
	private Color colour;
	
	CurveData(Function func, Color col) {
		f = func;
		colour = col;
	}
	
	
	
	public void plotData(Graphics g, Axis xaxis, Axis yaxis) {
		
		double j = xmin;
		int xprev = (int)(xaxis.xaxismin + ((j-xmin)/xrange)*xaxis.xaxisrange);
		int yprev = yaxis.canvasHeight - (int)(yaxis.yaxismin + ((f.call(j)-ymin)/yrange)*yaxis.yaxisrange);
		j+=0.001;
		
		for(double i = j; i< xmax; i+=0.001) {
			int xval = (int)(xaxis.xaxismin + ((i-xmin)/xrange)*xaxis.xaxisrange);
   	        int yval = yaxis.canvasHeight - (int)(yaxis.yaxismin + ((f.call(i)-ymin)/yrange)*yaxis.yaxisrange);
   	        
   	        g.setColor(colour);
	        g.drawLine(xprev,yprev,xval,yval);
	        
	        xprev=xval;
	        yprev=yval;
	        
		}
		
		
	}

}