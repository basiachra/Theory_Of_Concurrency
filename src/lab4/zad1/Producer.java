package lab4.zad1;

public class Producer implements Runnable {
	private int id;
	private int index = 0;
	private Semaphore [] semsArray;
	private int buffer[];
	
	public Producer(int id, Semaphore[] semsArray, int buffer[]){
		this.id = id;
		this.buffer = buffer;
		this.semsArray = semsArray;
	}
	
	public void run(){
		for (int j = 0; j < Main.N; j++) {																												//for(int i = 0; i < Main.N; i++) {
				try {		
					semsArray[id].decrease();
					
					buffer[index] += 1;
					System.out.println("\n zmiana bufora[" + index + "] = " + id);

					index = (index + 1) % Main.N;
					semsArray[(id+1)%Main.M].increase();

				} catch(Exception e) {
					e.printStackTrace();
				}
			}
	}
}