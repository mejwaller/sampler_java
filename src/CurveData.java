import java.awt.Graphics;
import java.awt.Color;

public class CurveData extends PlotData {
	
	private Function f;
	private Color colour;
	private double step;	
	
	CurveData(Function func, double step, DataRange range, Color col) {
		f = func;
		this.step = step;
		this.range = range;
		colour = col;
		generateData();
	}
	
	public void generateData(){
		//System.out.println("RANGE.XMIN IS " + range.xmin);
		for(double i=range.xmin;i<=range.xmax;i+=step) {
			data.put(i, f.call(i));
			
		}
	}
	
	public void plotData(Graphics g, Axis xaxis, Axis yaxis) {
		
		double j=range.xmin;	
		int xprev = (int)(xaxis.xaxismin + ((j-range.xmin)/range.xrange)*xaxis.xaxisrange);
		int yprev = yaxis.canvasHeight - (int)(yaxis.yaxismin + ((f.call(j)-range.ymin)/range.yrange)*yaxis.yaxisrange);
		j+=0.001;
				
		for(double i = j; i< range.xmax; i+=step) {
			int xval = (int)(xaxis.xaxismin + ((i-range.xmin)/range.xrange)*xaxis.xaxisrange);
   	        int yval = yaxis.canvasHeight - (int)(yaxis.yaxismin + ((f.call(i)-range.ymin)/range.yrange)*yaxis.yaxisrange);
   	        
   	        g.setColor(colour);
	        g.drawLine(xprev,yprev,xval,yval);
	        
	        xprev=xval;
	        yprev=yval;
	        
		}
		
		
	}

}