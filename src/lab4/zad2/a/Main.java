package lab4.zad2.a;

public class Main {
	final static int M = 1000;
	final static int Num = 100;
	
	public static void main (String [] args){
		Buffer buffer = new Buffer();
		Thread [] producers = new Thread [Num];
		Thread [] consumers = new Thread [Num];
		
		for (int i = 0; i < Num; i++){
			producers[i] = new Thread(new Producer(i, buffer));
            consumers[i] = new Thread(new Consumer(i, buffer));
		}
		
		for (int i = 0; i < Num; i++) {
            producers[i].start();
            consumers[i].start();
        }
	}
}
