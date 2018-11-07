package lab4.zad2.b;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Producer extends Job implements Runnable {


	public Producer(int id, Monitor m) {
		super(id, m);
	}

	@Override
	public void run() {
		int rand = getRandom();
		try {
		System.out.println("Producent " + id + " produkuje "+ rand + " elementów;");


			long startPutTime = System.nanoTime();
			monitor.wstaw(rand);
			long estimatedPutTime = System.nanoTime() - startPutTime;

			try {
				FileWriter file = new FileWriter("putBezGlodzenia.txt", true);
				BufferedWriter out = new BufferedWriter(file);
				out.write((double)estimatedPutTime/1_000_000_000.0 + " , " + rand + "\n");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}




		}catch(Exception e) {}
	}

}
