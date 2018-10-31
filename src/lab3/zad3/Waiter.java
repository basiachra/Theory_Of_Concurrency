package lab3.zad3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {
	private final Lock lock = new ReentrantLock();
	private int[] pairCounter = new int[Main.N];
	private final Condition freeTable = lock.newCondition();					
	private int eatingClients = 0;
	private Condition[] secondPersonToPair = new Condition[Main.N];	
	
	
	public Waiter(){
		for(int i = 0; i < Main.N; i++){
			pairCounter[i] = 0;
			secondPersonToPair[i] = lock.newCondition();
		}		
	}
	
	public void takeTable(int pairId){
		lock.lock();
		System.out.println("Przyszedł klient do pary " + pairId);
		pairCounter[pairId]++;
															
		try{															
			if(pairCounter[pairId] < 2)
				secondPersonToPair[pairId].await();	
			
			else {
				while(eatingClients == 2)
					freeTable.await();
				System.out.println("Para " + pairId + " zaczela jesc");
				eatingClients = 2;
				secondPersonToPair[pairId].signal();
			}		
			
		}catch (InterruptedException e){
			e.printStackTrace();
		}
				
		lock.unlock();
	}
	
	public void releaseTable(){
		lock.lock();
		eatingClients--;
		
		if(eatingClients == 0){
			System.out.println("Zwalniamy stolik.");
			freeTable.signal();
		}
				
		lock.unlock();
	}
}
