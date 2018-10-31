package lab3.zad3;

public class Client implements Runnable{
	private int pairId;
	private Waiter waiter;
	
	public Client(int id, Waiter w){
		this.pairId = id;
		this.waiter = w;
	}
	
	public void run(){
		try{
			Thread.sleep(200);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		waiter.takeTable(this.pairId);
		
		try{
			Thread.sleep(500);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		waiter.releaseTable();
	}
}
