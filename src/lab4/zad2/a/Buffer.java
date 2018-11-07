package lab4.zad2.a;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
	final Lock lock = new ReentrantLock();
	final Condition freeCells  = lock.newCondition();
	final Condition takenCells  = lock.newCondition();
	final Condition tryProd  = lock.newCondition();
	final Condition tryCons = lock.newCondition();
	private boolean producerWaiting;
	private boolean consumerWaiting;
	
	private int [] buffTab;
	private int currentlyFreeCells;
	
	public Buffer(){
		buffTab = new int [2 * Main.M];
		currentlyFreeCells = 2 * Main.M;
		producerWaiting = false;
		consumerWaiting = false;
		for (int i = 0; i < 2 * Main.M; i++)
			buffTab[i] = 0;
	}


    long startPutTime = System.nanoTime();

	public void put(int n, int id){
		try{
			lock.lock();
			if (producerWaiting)			    //ktoś inny już czeka
				tryProd.await();

			while (n > currentlyFreeCells){			//gdy chcemy włozyc więcej niż się da to czekamy na wolne
				producerWaiting = true;
				freeCells.await();
			}

			for (int i = 0; i < n; i++){        //zapisujemy w wolne miejsca w buforze
				int j = 0;
				boolean freeCellFound = false;
				while (j < 2 * Main.M && !freeCellFound){
					if (buffTab[j] == 0){
						buffTab[j] = 1;
						currentlyFreeCells--;
						freeCellFound = true;
					}
					j++;
				}
			}
			System.out.println("Producent " + id + " zakończył zapisywanie ");
			printBuffer();
			
			tryProd.signal(); 								//powiadoamiamy inne wątki że bufor nie jest pusty i że obecny wątek zakończył działanie
			takenCells.signal();
			consumerWaiting = false;
			lock.unlock();

		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

    long estimatedPutTime = System.nanoTime() - startPutTime;

    long startGetTime = System.nanoTime();
	public void take(int cellsToTake, int id){
		try{
			lock.lock();
			
			if (consumerWaiting){
				tryCons.await();
			}
			
			while (cellsToTake > (2*Main.M) - currentlyFreeCells){
				takenCells.await();
				System.out.println("Konsument " + id + " czeka na wystarczajacą liczbę wartości do pobrania");
				consumerWaiting = true;
			}
				
			for (int i = 0; i < cellsToTake; i++){
				int j = 0;
				boolean cellTaken = false;
				while (j < 2 * Main.M && !cellTaken){
					if (buffTab[j] != 0){
						buffTab[j] = 0;
						currentlyFreeCells++;
						cellTaken = true;
					}
					j++;
				}
			}
			System.out.println("Konsument " + id + " zakończył pobieranie ");
			printBuffer();
			
			freeCells.signal();
			tryCons.signal();
			producerWaiting = false;
			lock.unlock();

		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
    long estimatedGetTime = System.nanoTime() - startGetTime;


	private void printBuffer(){
		for (int i = 0; i < 2*Main.M; i++)
			System.out.print(buffTab[i] + " ");
		System.out.println();
	}
}
