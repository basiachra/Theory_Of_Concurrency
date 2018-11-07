package lab4.zad1;


public class Semaphore {
	private int sem_counter;
	
	public Semaphore(int start){
		sem_counter = start;
	}
	
	public synchronized void increase() {
		notifyAll();
		sem_counter += 1;
	}
	
	public synchronized void decrease() throws InterruptedException {
		while(sem_counter == 0)
			wait();
		sem_counter -= 1;
	}
}