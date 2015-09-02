//naive attempt to create function object
//random should only be required for specialisations
import java.util.Random;

public abstract class Function {
   
	protected double variance = 1.0;
	
    public Function()
    {
        
    }    
	
	void setVar(double val) {
		variance = val;
	}
	
	public double noise(double val) {
		double pureval = call(val);
		Random gen = new Random();
        //from z = (x-mu)/sigma - mu is val, sigma the variance so required val x is mu + generated val * sigma
        return pureval + gen.nextGaussian()*variance;  
        //return val;
    }
    
    abstract public double call(double x);
        
}

