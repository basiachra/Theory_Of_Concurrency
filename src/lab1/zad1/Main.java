package lab1.zad1;

public class Main {

    public static void main(String[] args){

        Counter count = new Counter();

        Increment t1 = new Increment(count);
        Decrement t2 = new Decrement(count);

        Thread th1 = new Thread(t1);
        Thread th2 = new Thread(t2);

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        count.showCount();

    }
}
