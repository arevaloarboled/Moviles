import java.util.ArrayList;

public class arrayList {	
	public ArrayList<Integer> alist= new ArrayList<Integer>();
	
	public arrayList(ArrayList<Integer> alist) {
		this.alist= alist;
	}
	
	public void insertAList(int n)	
	{
		for(int i = 0; i < n; i++) {
			alist.add(i);
	}
	}	
	public void removeAList(int n)	
	{
		for(int i = 0; i < n; i++) {
			alist.remove(0);
			System.out.println("Se borró el " + i);
		}
	}	
	public void getAList(int n)	
	{
		for(int i = 0; i < n; i++) {
			alist.get(i);
			System.out.println("Se obtuvo el  " + i);
		}
	}
}
