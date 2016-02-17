

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

public class vectorTest1 {

	Vector<Integer> vectore=new Vector<Integer>();
	@Test
	public void testAList100() {
		vector vector1= new vector(vectore);
		vector1.insertVector(100);
		vector1.getVector(100);
		vector1.removeVector(100);		
	}
	@Test
	public void testAList1000() {
		vector vector1= new vector(vectore);
		vector1.insertVector(1000);
		vector1.getVector(1000);
		vector1.removeVector(1000);		
	}
	@Test
	public void testAList10000() {
		vector vector1= new vector(vectore);
		vector1.insertVector(10000);
		vector1.getVector(10000);
		vector1.removeVector(10000);		
	}

}