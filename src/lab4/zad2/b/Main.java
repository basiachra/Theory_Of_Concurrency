package lab4.zad2.b;


import java.util.ArrayList;

public class Main {
	
	public static final int M = 500;
	public static final int K = 10;
	public static final int P = 10;
	public static final int B = 2*M;
	
	public static void main(String[] args) throws InterruptedException {
		
		Buffer b = new Buffer();
		Monitor m = new Monitor(b);
		ArrayList<Thread> threads = new ArrayList<Thread>();
		Thread t;
		for (int i=0; i<K; i++) {
		    t = new Thread(new Consumer(i, m));
		    threads.add(t);	    
		}
		for (int i=0; i<P; i++) {
		    t = new Thread(new Producer(i, m));
		    threads.add(t);	    
		}
		
		for(Thread th : threads) {
			th.start();
		}
		
		for(Thread th : threads) {
			th.join();
		}
		

	}

}
