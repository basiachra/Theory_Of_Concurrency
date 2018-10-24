package lab2.zad2;

public class Semafor {

    private int cart = 2;
    private int sem;

    public Semafor(){
        sem = cart;
    }

    public synchronized void P(int n) throws InterruptedException{
        while(sem == 0){
            wait();
            System.out.println("Brak koszyków klient " + n +" czeka");
        }
        sem--;
    }
    public synchronized void V(){
        if(sem <= cart)
            sem++;
        notifyAll();
    }
}
