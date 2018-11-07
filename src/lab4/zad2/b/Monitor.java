package lab4.zad2.b;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
	private Lock lock = new ReentrantLock();
	private Condition firstProd = lock.newCondition();
	private Condition firstCons = lock.newCondition();
	private Condition restProd = lock.newCondition();
	private Condition restCons = lock.newCondition();
	private boolean prodWaiting;
	private boolean consWaiting;
	
	private Buffer buff;
	
	public Monitor(Buffer buff) {
		this.buff = buff;
		prodWaiting = false;
		consWaiting = false;
	}
	public void wstaw(int rand) throws InterruptedException {
		
		lock.lock();
		if(prodWaiting) 
			restProd.await();
		
		while (!buff.fits(rand)) {	//jesli sie nie mieści to czekamy i zaznaczamy że czekamy
			firstProd.await();
			prodWaiting = true;
		}
		buff.write(rand);
		buff.printBuffer();
		restProd.signal();
		firstCons.signal();
		consWaiting = false;
		lock.unlock();
	}
	
	public void pobierz(int rand) throws InterruptedException {
		
		lock.lock();

		if(consWaiting)
			restCons.await();

		while(!buff.eatable(rand)) { //jeśli nie ma tyle ile chcemy pobrać
			firstCons.await();
			consWaiting = true;
		}

		buff.take(rand);
		buff.printBuffer();
		restCons.signal();
		firstProd.signal();
		prodWaiting = false;
		lock.unlock();
	}
}
