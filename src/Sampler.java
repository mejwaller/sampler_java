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
    	
    	XYData xy1 = new XYData(xyset1,Plotter2d.Shape.ELLIPSE, Color.BLACK);
    	XYData xy2 = new XYData(xyset2, Plotter2d.Shape.RECT, Color.BLUE);
    	CurveData c = new CurveData(f,0.001,new DataRange(1.0,0.0,1.0,-1.0,1,2),Color.RED);    	
      
    	Plotter2d GP = new Plotter2d(); //create a new frame to which we will add a canvas  
    	GP.addData(xy1);
    	GP.addData(xy2);
    	GP.addData(c);
    	//GP.addXYData(xyset3, Plotter2d.Shape.ELLIPSE, Color.BLUE);
    	Frame aFrame = new Frame(); 
    	aFrame.setSize(1010, 1010); //5 pixels either side for frame, 5 below for frame + 25 above foe window bar
    	aFrame.add(GP); 
    	aFrame.setVisible(true); 
    	
    	//System.out.println("xmax is " + GP.getRange().xmax + " xmin is " + GP.getRange().xmin + " ymax is " + GP.getRange().ymax + " ymin is " + GP.getRange().ymin);
    	//System.out.println("GP width = " + GP.getWidth() + " GP height = " + GP.getHeight());
    	
    	
    
    }
}

