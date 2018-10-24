package lab1.zad3;

public class Buffer {

    private String message;
    private boolean isEmpty;

    public Buffer(){
        this.isEmpty = true;
    }

    public synchronized  void put(String s) throws InterruptedException{

        while(!isEmpty){
            wait();
        }
        this.isEmpty = false;
        this.message = s;
        notifyAll();
    }

    public synchronized String take() throws InterruptedException{

        while(isEmpty){
            wait();
        }

        this.isEmpty = true;
        notifyAll();
        return message;
    }

    public boolean isEmpty(){
        return this.isEmpty;
    }



}
