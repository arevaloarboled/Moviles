import java.util.LinkedList;

public class linkedList {	
	public LinkedList<Integer> lilist= new LinkedList<Integer>();
	
	public linkedList(LinkedList<Integer> lilist) {
		this.lilist= lilist;
	}
	
	public void insertAList(int n)	
	{
		for(int i = 0; i < n; i++) {
			lilist.add(i);
	}
	}	
	public void removeAList(int n)	
	{
		for(int i = 0; i < n; i++) {
			lilist.remove(0);
			System.out.println("Se borró el " + i);
		}
	}	
	public void getAList(int n)	
	{
		for(int i = 0; i < n; i++) {
			lilist.get(i);
			System.out.println("Se obtuvo el  " + i);
		}
	}
}
