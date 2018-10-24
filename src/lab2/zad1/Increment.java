package lab2.zad1;

public class Increment implements Runnable {

    Counter count;
    Semafor semafor;

    public Increment (Counter n, Semafor sem){
        this.count = n;
        this.semafor = sem;
    }

    @Override
    public void run(){
        try{
            semafor.P();
            for(int i = 0; i< 10000000 ;i++)
                count.add();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        semafor.V();
    }

}
