package punto5;

public class Square extends GeometricFigure {
	private double edge;
	
	public void lado(double l){
		this.edge=l;
	}
	
	public double getedge(){
		return this.edge;
	}

	@Override
	public double caclulateArea() {
		return (Math.pow(this.edge,2));
	}
	@Override
	public double calculatePerimeter() {
		return (this.edge*4);
	}

}
