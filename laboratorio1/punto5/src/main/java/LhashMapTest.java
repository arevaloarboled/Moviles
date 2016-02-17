import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.Test;

public class LhashMapTest {

	LinkedHashMap<Integer,String> hash1 =new LinkedHashMap<Integer,String>();
	@Test
	public void testHash100() {
		LhashMap hash= new LhashMap(hash1);
		hash.putHash(100);
		hash.getHash(100);
		hash.removeHash(100);		
	}
	@Test
	public void testHash1000() {
		LhashMap hash= new LhashMap(hash1);
		hash.putHash(1000);
		hash.getHash(1000);
		hash.removeHash(1000);		
	}
	@Test
	public void testHash10000() {
		LhashMap hash= new LhashMap(hash1);
		hash.putHash(10000);
		hash.getHash(10000);
		hash.removeHash(10000);	
	}

}