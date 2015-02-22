import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics;


public abstract class PlotData {
	
	Map<Double, Double> data = new HashMap<Double,Double>();	
	Color colour;
	Axis xaxis;
	Axis yaxis;
	DataRange range;
	
	
	public abstract void plotData(Graphics g, Axis xaxis, Axis Yaxis);
	
	public void setDataRange(DataRange range) {
		this.range=range;
	}

}