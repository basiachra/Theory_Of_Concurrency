package lab4.zad2.a;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Consumer implements Runnable{
	private int consumerId;
	private Buffer buff;
	
	public Consumer(int id, Buffer buff){
		this.consumerId = id;
		this.buff = buff;
	}
	
	public void run(){
			int rand = new Random().nextInt(Main.M) + 1;
			System.out.println("Konsument "+ consumerId + " pobiera " + rand + " komórek.");


        long startGetTime = System.nanoTime();
            buff.take(rand, this.consumerId);
        long estimatedGetTime = System.nanoTime() - startGetTime;


		try {
			FileWriter file = new FileWriter("getZGlodzeniem.txt", true);
			BufferedWriter out = new BufferedWriter(file);
			out.write((double)estimatedGetTime/1_000_000_000.0 + " , " + rand + "\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}

