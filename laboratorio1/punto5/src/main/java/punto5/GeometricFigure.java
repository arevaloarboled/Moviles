package punto5;

public abstract class GeometricFigure {
	private String name;
	private String description;
	
	public void nombre(String s){
		this.name = s;
	}
	public void descripcion(String s){
		this.description = s;
	}
	
	public String getname(){
		return this.name;
	}
	
	public String getdescription(){
		return this.description;
	}
	
	public abstract double caclulateArea();
	public abstract double calculatePerimeter();
}
