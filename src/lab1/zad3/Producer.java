package lab1.zad3;

public class Producer implements Runnable {

    public static final int ILOSC = 10;
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < ILOSC;   i++) {
            String s = "message " + i;
            try{
                buffer.put(s);
                System.out.println("Producing:" + s);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
