package lab2.zad2;

public class Client implements Runnable{

    private Semafor sem;
    private int n;

    public Client(Semafor sem, int n){
        this.sem = sem;
        this.n = n;
    }

    private void shopping() throws InterruptedException{
        try{
            sem.P(this.n);
            Thread.sleep(10);

        }catch(InterruptedException e){
            e.printStackTrace();
        }
        sem.V();
        System.out.println("Klient  " + this.n + " robi zakupy");
    }

    @Override
    public void run(){
        try{
            shopping();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
