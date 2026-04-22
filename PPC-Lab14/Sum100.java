
public class Sum100 {
    public static void main(String[] args) {

        SumThread1 t = new SumThread1();

        Thread T1 = new Thread(t);
        
        try {
            T1.start();
            T1.join();
        } catch (InterruptedException e) {
            System.out.println("Main stream interrupted.");
        }

        System.out.println("1a) Sum = " + t.getSum());

        SumThread2 T2 = new SumThread2();

        try {
            T2.start();
            T2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("1b) Sum = " + T2.getSum());

    }
}

class SumThread1 implements Runnable {

    static int sum = 0;

    public void run() {
        for (int i = 1; i <= 100; i++) {
           sum += i;
        }
    }

    public int getSum() { return sum; }

}

class SumThread2 extends Thread {

    static int sum = 0;

    public void run() {
        for (int i = 0; i <= 100; i++){
            sum += i;
        }
    }

    public int getSum() { return sum; }

}
