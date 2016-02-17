package punto5;

public class Circle extends GeometricFigure {
	private double ratio;
	private double pi=3.1416;
	
	public void radio(double r){
		this.ratio = r;
	}
	
	public double getratio(){
		return this.ratio;
	}
	
	public double getpi(){
		return this.pi;
	}
	@Override
	public double caclulateArea() {
		return (this.pi*Math.pow(this.ratio, 2));
	}
	
	public double calculateArea(double p){
		return (p*Math.pow(this.ratio, 2));
	}

	@Override
	public double calculatePerimeter() {
		return (2*this.pi*this.ratio);
	}
}
