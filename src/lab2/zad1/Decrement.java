package lab2.zad1;

public class Decrement implements Runnable {

    Counter count;
    Semafor semafor;

    public Decrement (Counter n, Semafor sem){
        this.count = n;
        this.semafor = sem;
    }
    @Override
    public void run(){
        try{
            semafor.P();
            for(int i = 0; i< 10000000 ;i++)
                count.substract();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        semafor.V();
    }
}
