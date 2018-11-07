package lab4.zad2.b;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Consumer extends Job implements Runnable {
	
	public Consumer(int id, Monitor monitor) {
		super(id, monitor);
	}
	
	@Override
	public void run(){
		int rand = getRandom();
		try {
		System.out.println("Konsument " + id + " pobiera "+ rand + " elementów;");

			long startGetTime = System.nanoTime();
			monitor.pobierz(rand);;
			long estimatedGetTime = System.nanoTime() - startGetTime;

			try {
				FileWriter file = new FileWriter("getBezGlodzenia.txt", true);
				BufferedWriter out = new BufferedWriter(file);
				out.write((double)estimatedGetTime/1_000_000_000.0 + " , " + rand + "\n");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			
		}
	}
}
