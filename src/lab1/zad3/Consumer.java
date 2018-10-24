package lab1.zad3;

public class Consumer implements Runnable {

    public static final int ILOSC = 10;
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < ILOSC;   i++) {
            String s;
            try{
                s = buffer.take();
                System.out.println("Konsumuję: " + s);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
