package ppc.lab;

public class Misc {
    
    private static int[] intArr;
    private static String strOne;
    private static String strTwo;

    public static void randomizeIntArr(int size, int lower, int upper) {
        intArr = new int[size];
        for (int i = 0; i < size; i++) {
            intArr[i] = (int)(Math.random()*(upper-lower))+lower;
        }
    }

    public static int[] getIntArr() {
        return intArr;
    }

    public static void setStrOne(String s) {
        strOne = s;
    }

    public static void setStrTwo(String s) {
        strTwo = s;
    }

    public static String getStrOne() {
        return strOne;
    }

    public static String getStrTwo() {
        return strTwo;
    }

    public static void resetAll() {
        intArr = null;
        strOne = null;
        strTwo = null;
    }

}
