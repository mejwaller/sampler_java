import java.util.Random;

class Sampler {

    public static void main(String[] args)
    {
    	System.out.println("Hello");
    	
    	double[] vals = new double[20];
    	
    	Function f = new FunctionBuilder().var(0.3).buildFunction();
    	
    	double step = 20;
    	
    	for(int i =0; i < step; i++) {
    	
    		vals[i] = f.call(i/step);
    	
    		System.out.println(vals[i]);
    	}
    
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
		double pureval = 2.0 * x * Math.PI;
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