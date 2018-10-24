package lab1.zad1;

public class Decrement implements Runnable {

    Counter count;

    public Decrement(Counter n){
        this.count = n;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100000000; i++)
            count.substract();
    }
}
