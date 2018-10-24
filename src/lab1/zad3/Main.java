package lab1.zad3;

public class Main {

    public static void main(String[] args) {

        Buffer buffer = new Buffer();
        Producer p = new Producer(buffer);
        Consumer c = new Consumer(buffer);
        Thread producer = new Thread(p);
        Thread consumer = new Thread(c);

        producer.start();
        consumer.start();

        try{
            consumer.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }


    }

}
