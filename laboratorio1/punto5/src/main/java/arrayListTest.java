import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

public class arrayListTest {
	
	ArrayList<Integer> alistt=new ArrayList<Integer>();
	@Test
	public void testAList100() {
		arrayList alist= new arrayList(alistt);
		alist.insertAList(100);
		alist.getAList(100);
		alist.removeAList(100);		
	}
	@Test
	public void testAList1000() {
		arrayList alist= new arrayList(alistt);
		alist.insertAList(1000);
		alist.getAList(1000);
		alist.removeAList(1000);		
	}
	@Test
	public void testAList10000() {
		arrayList alist= new arrayList(alistt);
		alist.insertAList(10000);
		alist.getAList(10000);
		alist.removeAList(10000);		
	}
}
