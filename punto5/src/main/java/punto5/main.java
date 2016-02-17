package punto5;

public class main {
	public static void main(String args[]) {
		Circle c = new Circle();
		c.nombre("circulo");
		c.descripcion("esto es un circulo");
		c.radio(1);
		Rectangular r = new Rectangular();
		r.nombre("rectangulo");
		r.descripcion("esto es un rectangulo");
		r.alto(1);
		r.ancho(2);
		Square q = new Square();
		q.nombre("cuadrado");
		q.descripcion("esto es un cuadrado");
		q.lado(1);
		
		System.out.println("el "+c.getname()+" tiene area :"+c.caclulateArea());
		System.out.println("el "+r.getname()+" tiene area :"+r.caclulateArea());
		System.out.println("el "+q.getname()+" tiene area :"+q.caclulateArea());

	}
}
