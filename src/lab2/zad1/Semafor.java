package lab2.zad1;

public class Semafor {
    private boolean sem;

    public Semafor (boolean x){
        this.sem = x;
    }

    public synchronized void P() throws InterruptedException{
        while(!sem){
            wait();
        }
        sem = false;
    }
    public synchronized void V(){
        sem = true;
        notifyAll();
    }
}
