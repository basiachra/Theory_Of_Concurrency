package lab3.zad1;

public class Client implements Runnable{

    private BoundedBuffer bb;
    private int n;

    public Client(BoundedBuffer sem, int n){
        this.bb = sem;
        this.n = n;
    }

    private void shopping() throws InterruptedException{
        try{
            bb.put(this.n);
            Thread.sleep(10);

        }catch(InterruptedException e){
            e.printStackTrace();
        }
        bb.take();
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
