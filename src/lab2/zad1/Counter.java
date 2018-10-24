package lab2.zad1;

public class Counter {
    public static int count;

    public Counter(){
        Counter.count = 0;
    }

    public void add(){
        Counter.count +=1;
    }

    public void substract(){
        Counter.count -=1;
    }

    public void showCount(){
        System.out.println("Wynik wynosi: " + Counter.count);
    }
}
