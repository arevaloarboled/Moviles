import java.util.Vector;

public class vector {
	public Vector<Integer> vector1 = new Vector<Integer>();

	public vector(Vector<Integer> vector1) {
		this.vector1 = vector1;
	}
	
	public void insertVector(int n)	
	{
		for(int i = 0; i < n; i++) {
			vector1.add(i);
	}
	}	
	public void removeVector(int n)	
	{
		for(int i = 0; i < n; i++) {
			vector1.remove(0);
			System.out.println("Se borró el " + i);
		}
	}	
	public void getVector(int n)	
	{
		for(int i = 0; i < n; i++) {
			vector1.get(i);
			System.out.println("Se obtuvo el  " + i);
		}
	}
}
