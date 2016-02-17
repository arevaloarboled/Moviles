import java.util.HashMap;

public class hashMap {
public HashMap<Integer,String> hash = new HashMap<Integer,String>();
	
	public hashMap(HashMap<Integer, String> hash) {
		this.hash = hash;
	}
	public void putHash(int n){
		for (int i = 0; i < n; i++) {
			hash.put(i,"Value");
		}
	}	
	public void getHash(int n){
		for (int i = 0; i < n; i++) {
			hash.get(i);
		}
	}	
	public void removeHash(int n){
		for (int i = 0; i < n; i++) {
			hash.remove(i);
		}
	}
}
