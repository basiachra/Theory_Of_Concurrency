package lab1.zad2;

public class Increment implements Runnable {

    Counter count;

    public Increment (Counter n){
        this.count = n;
    }

    @Override
    public void run(){
        for(int i = 0; i< 100000000 ;i++)
            count.add();
    }

}
