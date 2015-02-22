class DataRange {

	public double xmax=1.0, xmin=-1.0, ymax=1.0, ymin=-1.0, xrange=2.0, yrange=2.0;
	
	DataRange(){}
	
	DataRange(double xmx, double xmn, double ymx, double ymn, double xr, double yr) {
		xmax = xmx;
		ymax=ymx;
		xmin=xmn;
		ymin=ymn;
		xrange=xr;
		yrange=yr;
	}
	
	public void setRanges(double xmx, double xmn, double ymx, double ymn, double xr, double yr) {
		xmax = xmx;
		ymax=ymx;
		xmin=xmn;
		ymin=ymn;
		xrange=xr;
		yrange=yr;
	}
}