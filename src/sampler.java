import java.util.Random;

//graphics experimentation
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

class graphics extends Canvas {
	
	public graphics(){
		setSize(800, 800); 
		setBackground(Color.white); 
	} 
	
	public void paint(Graphics g){ 
		g.setColor(Color.blue); 
		g.drawLine(30, 30, 800, 800); 
		g.drawRect(20, 150, 100, 100); 
		g.fillRect(20, 150, 100, 100); 
		g.fillOval(150, 20, 100, 100); 
	}
}

class Sampler {

    public static void main(String[] args)
    {
    	System.out.println("Hello");
    	
    	double[] vals = new double[20];
    	
    	Function f = new FunctionBuilder().var(0.3).buildFunction();
    	
    	double step = 22;
    	
    	for(int i = 1; i < step-1; i++) {
    	
    		vals[i-1] = f.call(i*1.0/(step-2.0));
    	
    		System.out.println(i-1 + " " + i*1.0/(step-2.0) + " " + vals[i-1]);
    	}
    	
    	//graphics class is now a type of canvas 
    	//since it extends the Canvas class 
    	//lets instantiate it  
    	graphics GP = new graphics(); //create a new frame to which we will add a canvas  
    	Frame aFrame = new Frame(); 
    	aFrame.setSize(800, 800); //add the canvas  
    	aFrame.add(GP); 
    	aFrame.setVisible(true); 
    	
    	
    
    }
}

class Function {
	
	private double variance = 1.0;
	
	public Function()
	{
		
	}
	
	public Function(double var) {
		variance = var;
	}
	
	public double call(double x) {
		double pureval = Math.sin(2.0 * x * Math.PI);
		return noise(pureval);
	}
		
	public double noise(double val)
	{
		Random gen = new Random();
		//from z = (x-mu)/sigma - mu is val, sigma the variance so required val x is mu + generated val * sigma
		return val + gen.nextGaussian()*variance;		
	}
	
}

class FunctionBuilder {
	
	private double var = 1.0;
	
	public FunctionBuilder() { }
	
	public Function buildFunction() {
		return new Function(var);
	}
	
	public FunctionBuilder var(double var)
	{
		this.var = var;
		return this;
	}
	

}