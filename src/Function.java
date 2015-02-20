//naive attempt to create function object
//random should only be required for specialisations
import java.util.Random;

public class Function {
    
    private double variance = 1.0;
    
    public Function()
    {
        
    }
    
    public Function(double var) {
        variance = var;
    }
    
    public double call(double x) {
        return Math.sin(2.0 * x * Math.PI);
        //return noise(pureval);
    }
        
    public double noise(double val)
    {
        double noisey=call(val);
        Random gen = new Random();
        //from z = (x-mu)/sigma - mu is val, sigma the variance so required val x is mu + generated val * sigma
        return noisey + gen.nextGaussian()*variance;  
        //return val;
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