import java.awt.Color;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class Sampler {
   

    public static void main(String[] args)
    {	
    	
    	Map<Double, Double> xyset1 = new HashMap<Double, Double>();
    	Map<Double, Double> xyset2 = new HashMap<Double, Double>();
    	//Map<Double, Double> xyset3 = new HashMap<Double,Double>();
            	
    	Function f = new Sin2xpi();
    	//Function g = new Cos2xpi();
    	f.setVar(0.3);
    	//g.setVar(0.3);
    	
    	//hardcoded number. Sucks
    	double step = 20;
    	    	
    	for(int i = 0; i <= step; i++) {
    		
    		double xval = i*1.0/(step);
    		
    		xyset1.put(xval,f.noise(xval));
    	        	
    		//System.out.println(i + " " + x.get(i) + " " + t.get(i));
    	}
    	
    	for(int i = 0; i <= step; i++) {
    		
    		double xval = i*1.0/(step);
    		
    		xyset2.put(xval,f.noise(xval));
    	        	
    		//System.out.println(i + " " + x.get(i) + " " + t.get(i));
    	}
    	
    	/*for(int i = 0; i <= step; i++) {
    		
    		double xval = i*1.0/(step);
    		
    		xyset3.put(xval,g.noise(xval));
    	        	
    		//System.out.println(i + " " + x.get(i) + " " + t.get(i));
    	}*/
      
    	Plotter2d GP = new Plotter2d(); //create a new frame to which we will add a canvas  
    	GP.addXYData(xyset1, Plotter2d.Shape.ELLIPSE, Color.BLACK);
    	GP.addXYData(xyset2, Plotter2d.Shape.RECT, Color.BLUE);
    	GP.addCurve(f,Color.RED,GP.xmax,GP.xmin,GP.ymax,GP.ymin);
    	//GP.addXYData(xyset3, Plotter2d.Shape.ELLIPSE, Color.BLUE);
    	Frame aFrame = new Frame(); 
    	aFrame.setSize(1010, 1010); //5 pixels either side for frame, 5 below for frame + 25 above foe window bar
    	aFrame.add(GP); 
    	aFrame.setVisible(true); 
    	
    	System.out.println("GP width = " + GP.getWidth() + " GP height = " + GP.getHeight());
    	
    	
    
    }
}

