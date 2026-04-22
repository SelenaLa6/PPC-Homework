public class Threaded {
    public static void main (String[] args) {

        //n = number of squares to sum
        int n = Integer.parseInt(args[0]);

        //create & fill array
        int[] smallInts = new int[n];
        for (int i = 0; i < n; i++) {
            smallInts[i] = (i % 100)+1;
        }

        int squareSum = 0;
        long startTime, endTime, duration;

        startTime = System.nanoTime();

        //t1 computes 1st half
        ssThread t1 = new ssThread(0, n/2, smallInts);

        // Thread t1 = new Thread(() -> {
        //     int sum = 0;
        //     for (int i = 0; i < n/2; i++) {
        //         int x = smallInts[i];
        //         sum += x*x;
        //     }
        //     SumBox.add(sum);
        // });

        //t2 computes 2nd half
        ssThread t2 = new ssThread(n/2, n, smallInts);

        // Thread t2 = new Thread(() -> {
        //     int sum = 0;
        //     for (int i = n/2; i < n; i++) {
        //         int x = smallInts[i];
        //         sum += x*x;
        //     }
        //     SumBox.add(sum);
        // });

        //start both threads
        t1.start();
        t2.start();
        
        //wait for both threads to finish computing
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        squareSum = t1.getSum() + t2.getSum();

        endTime = System.nanoTime();
        duration = endTime - startTime;

        // System.out.println("Square Sum = " + SumBox.getSquareSum());
        System.out.println("Square Sum = " + squareSum);
        System.out.println("Threaded Time = " + duration + "\n");

    }
    
}

class ssThread extends Thread {

    int start;
    int end;
    int[] nums;
    int sum;

    public void run() {
        for (int i = start; i < end; i++)
            sum += nums[i]*nums[i];
    }

    public int getSum() { return sum; }

    public ssThread(int start, int end, int[] nums) {
        this.start = start;
        this.end = end;
        this.nums = nums;
        sum = 0;
    }

}
