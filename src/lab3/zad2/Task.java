package lab3.zad2;

public class Task implements Runnable{
    private int nrWatku;
    private Monitor_Drukarki printMonitor;

    public Task(int nrWatku, Monitor_Drukarki printMonitor){
        System.out.println("Tworzymy wątek o numerze " + nrWatku);
        this.nrWatku = nrWatku;
        this.printMonitor = printMonitor;
    }

    public void run(){

        System.out.println("Wątek o nr. " + this.nrWatku + " startuje");
        int printerId;
        printerId = printMonitor.takePrinter();
        System.out.println("Wątek o nr. " + this.nrWatku + " zajmuje drukarke o id " + printerId);

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        printMonitor.releasePrinter(printerId);
    }
}
