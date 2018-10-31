package lab3.zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Monitor_Drukarki {
    final Lock lock = new ReentrantLock();
    final Condition freePrinters = lock.newCondition();

    private boolean [] printerTab = new boolean[Main.M];
    private int counter = 0;

    public Monitor_Drukarki(){
        for (int i = 0; i < Main.M; i++)
            printerTab[i] = false;
    }

    public int takePrinter(){
        lock.lock();
        try {
            while(counter == printerTab.length){
                freePrinters.await();
                System.out.println("Oczekiwanie na drukarkę");
            }

        }catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }

        counter++;
        int index = -1;

        for(int i = 0; i < Main.M; i++){
            if(!printerTab[i]){
                printerTab[i] = true;
                index = i;
                break;
            }
        }

        lock.unlock();
        return index;

    }

    public void releasePrinter(int id){
        lock.lock();
        counter--;

        printerTab[id] = false;
        freePrinters.signal();
        lock.unlock();
    }
}

