import static org.junit.Assert.*;
import java.util.LinkedList;

import org.junit.Test;


public class linkedListTest {
	
	LinkedList<Integer> listt=new LinkedList<Integer>();
	@Test
	public void testAList100() {
		linkedList lilist= new linkedList(listt);
		lilist.insertAList(100);
		lilist.getAList(100);
		lilist.removeAList(100);		
	}
	@Test
	public void testAList1000() {
		linkedList lilist= new linkedList(listt);
		lilist.insertAList(1000);
		lilist.getAList(1000);
		lilist.removeAList(1000);		
	}
	@Test
	public void testAList10000() {
		linkedList lilist= new linkedList(listt);
		lilist.insertAList(10000);
		lilist.getAList(10000);
		lilist.removeAList(10000);		
	}
}
