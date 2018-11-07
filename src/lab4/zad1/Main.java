package lab4.zad1;

public class Main {
	public final static int N = 100;
	public final static int M = 5;
	

	public static void main(String[] args) {
		Semaphore [] semsArray = new Semaphore[M];
		int buffer[] = new int[N];
		Thread [] threads = new Thread[M];
		
		for (int i = 0; i < N; i++) 
			buffer[i] = 0;
		
		semsArray[0] = new Semaphore(N);
		for(int i = 1; i < M; i++) 
			semsArray[i] = new Semaphore(0);
		
		for (int i = 0; i < M; i++)										
			threads[i] = new Thread(new Producer(i, semsArray, buffer));
		
		for (int i = 0; i < M; i++)
			threads[i].start();
		
		try {
			for (int i = 0; i < M; i++)
				threads[i].join();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Buffer: ");
		for (int k = 0; k < Main.N; k++)
			System.out.print(buffer[k] + " ");
	}
}
