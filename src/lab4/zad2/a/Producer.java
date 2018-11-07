package lab4.zad2.a;

import java.io.*;
import java.util.Random;

public class Producer implements Runnable{
	private int producerId;
	private Buffer buff;
	
	public Producer(int id, Buffer buff){
		this.producerId = id;
		this.buff = buff;
	}

	
	public void run(){

			int rand = new Random().nextInt(Main.M) + 1;
			System.out.println("Producent "+ producerId + " zapisuje do " + rand + " komorek.");

			long startPutTime = System.nanoTime();
			    buff.put(rand, this.producerId);
            long estimatedPutTime = System.nanoTime() - startPutTime;

		try {
			FileWriter file = new FileWriter("putZGlodzeniem.txt", true);
			BufferedWriter out = new BufferedWriter(file);
			out.write((double)estimatedPutTime/1_000_000_000.0 + " , " + rand + "\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}