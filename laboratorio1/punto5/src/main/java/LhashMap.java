import java.util.LinkedHashMap;

public class LhashMap {
public LinkedHashMap<Integer,String> lhash = new LinkedHashMap<Integer,String>();
	
	public LhashMap(LinkedHashMap<Integer, String> lhash) {
		this.lhash = lhash;
	}
	public void putHash(int n){
		for (int i = 0; i < n; i++) {
			lhash.put(i,"Value");
		}
	}	
	public void getHash(int n){
		for (int i = 0; i < n; i++) {
			lhash.get(i);
		}
	}	
	public void removeHash(int n){
		for (int i = 0; i < n; i++) {
			lhash.remove(i);
		}
	}
}
