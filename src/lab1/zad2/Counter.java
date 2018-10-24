package lab1.zad2;

public class Counter {
    public static int count;

    public Counter(){

        Counter.count = 0;
    }

    public synchronized void add(){
        Counter.count +=1;
    }

    public synchronized void substract(){
        Counter.count -=1;
    }

    public void showCount(){
        System.out.println("Wynik wynosi: " + Counter.count);
    }
}
