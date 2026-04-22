
public class NonThreaded {
    public static void main(String[] args) {

        //n = number of squares to sum
        int n = Integer.parseInt(args[0]);

        //create & fill array
        int[] smallInts = new int[n];
        for (int i = 0; i < n; i++) {
            smallInts[i] = (i % 100)+1;
        }
        
        int squareSum = 0;
        long startTime, endTime, duration;

        //Non-threaded way

        startTime = System.nanoTime();

        for (int x: smallInts) {
            squareSum += x*x;
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("Square Sum = " + squareSum);
        System.out.println("Nonthreaded Time = " + duration + "\n");

    }
    
}
