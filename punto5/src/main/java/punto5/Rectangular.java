package punto5;

public class Rectangular extends GeometricFigure {
	 private double height;
	 private double width;

	 public void alto(double h){
		this.height = h;
	}
	 
	public void ancho(double w){
			this.width = w;
		}
	
	public double getheight(){
		return this.height;
	}
	
	public double getwidth(){
		return this.width;
	}
	@Override
	public double caclulateArea() {
		return (this.height*this.width);
	}
	@Override
	public double calculatePerimeter() {
		return (2*(this.height+this.width));
	}

}
