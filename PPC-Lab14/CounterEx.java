
public class CounterEx {
    public static void main(String[] args) {

        //2a)

        //counter w/ synchronized increment method
        Counter c = new Counter();

        //run method for each thread
        Runnable runIncrement = () -> {
            for (int i = 0; i < 1000; i++)
                c.increment();
        };

        //create & start 10 threads w/ run method
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runIncrement);
            threads[i].start();
        }

        //join each thread to wait until all end
        try {
            for (Thread t: threads)
                t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        //print out final counter
        System.out.println("2a) Counter = " + c.getCounter());
        //"2a) Counter = 10000"

        //2b) main thread joins the created thread after starting it

        c.resetCounter();

        //create all the threads
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runIncrement);
        }

        for (Thread t: threads) {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }
        }

        System.out.println("2b) Counter = " + c.getCounter());
        //"2b) Counter = 10000"

        //2c) main thread runs w/o joining any thread

        c.resetCounter();

        //create threads
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runIncrement);
        }

        //start threads, no joins
        for (Thread t: threads) {
            t.start();
        }

        System.out.println("2c) Counter = " + c.getCounter());
        //"2c) Counter = 0"

        //wait for all the threads to finish
        for (Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }
        }

        //2d) unsynchronized increment method

        c.resetCounter();

        //use unsynchronized increment method for run
        Runnable badRunIncrement = () -> {
            for (int i = 0; i < 1000; i++)
                c.badIncrement();
        };

        //create & start threads using bad increment method
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(badRunIncrement);
            threads[i].start();
        }

        //join all threads to ensure they all end first
        for (Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }
        }

        //print final counter
        System.out.println("2d) Counter = " + c.getCounter());
        //"2d) Counter = 9610"

    }
}

class Counter {

    static int counter = 0;

    synchronized public void increment() {
        counter++;
    }

    public void badIncrement() {
        counter++;
    }

    public int getCounter() { return counter; }

    public void resetCounter() { counter = 0; }

}
