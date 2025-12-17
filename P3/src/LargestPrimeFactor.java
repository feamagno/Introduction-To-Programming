public class LargestPrimeFactor {

    private static long MAX = 13195L;

    public static long largest(){
        long prime = 0;
        int i = Math.sqrt(MAX);
        while (i < Math.sqrt(MAX)){
            if (MAX % i == 0){
                prime = i;
            }
            i++;
        }
        return prime;
    }

    private boolean isPrime(long num){
        int i = 0;
        while ()
    }
}
