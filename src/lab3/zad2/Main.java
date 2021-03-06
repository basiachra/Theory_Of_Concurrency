package lab3.zad2;

public class Main {
    public final static int M = 5;  //liczba drukarek
    public final static int N = 40; //liczba wątków

    public static void main (String[] args){
        Monitor_Drukarki m = new Monitor_Drukarki();

        Thread [] threads = new Thread[N];
        for(int i = 0; i < N; i++){
            threads[i] = new Thread(new Task(i, m));
        }

        for(int i = 0; i < N; i++){
            threads[i].start();
        }

        try{
            threads[0].join();
        } catch (InterruptedException e){
            System.out.println("InterruptedException");
        }
    }
}