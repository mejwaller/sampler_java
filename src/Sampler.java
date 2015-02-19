import java.util.Random;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

class Sampler {
   

    public static void main(String[] args)
    {	
        List<Double> x = new ArrayList<Double>(21);
        List<Double> t = new ArrayList<Double>(21);
    	
    	Function f = new FunctionBuilder().var(0.3).buildFunction();
    	
    	//hardcoded number. Sucks
    	double step = 20;
    	
    	for(int i = 0; i <= step; i++) {
    	    
    	    x.add(i*1.0/(step));    	
    		t.add(f.call(x.get(i)));
    	
    		System.out.println(i + " " + x.get(i) + " " + t.get(i));
    	}
    	
    	//graphics class is now a type of canvas 
    	//since it extends the Canvas class 
    	//lets instantiate it  
    	Plotter2d GP = new Plotter2d(); //create a new frame to which we will add a canvas  
    	GP.setVals(x,t);
    	Frame aFrame = new Frame(); 
    	aFrame.setSize(1010, 1010); //5 pixels either side for frame, 5 below for frame + 25 above foe window bar
    	aFrame.add(GP); 
    	aFrame.setVisible(true); 
    	
    	System.out.println("GP width = " + GP.getWidth() + " GP height = " + GP.getHeight());
    	
    	
    
    }
}

