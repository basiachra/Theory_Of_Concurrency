package lab2.zad1;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Counter count = new Counter();
        Semafor sem = new Semafor(true);


        Increment t1 = new Increment(count,sem);
        Decrement t2 = new Decrement(count,sem);

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
