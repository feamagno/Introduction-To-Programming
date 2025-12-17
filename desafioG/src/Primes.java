public class Primes {

    int start, end, values = 0;
    int[] arrayOfPrimes;

    public Primes(int start, int end){
        this.start = start;
        this.end = end;
        this.arrayOfPrimes = arrayOfPrimes(start, end);
    }

    public boolean atLeastTwoPrimes(){
        return values >= 2;
    }

     private boolean isPrime(int n){

        int d = 2;
        double limit = Math.sqrt(n);

        if(n==1){
            return false;
        }
        else {
            while (n % d != 0 && d <= limit) {
                d++;
            }
            return d > limit;
        }
    }

    private int[] arrayOfPrimes(int start, int end){

        int[] arrayOfPrimes = new int[end];

        for (int i = start; i <= end; i++){
            if (isPrime(i)){

                arrayOfPrimes[this.values] = i;

                this.values++;
            }
        }
        return arrayOfPrimes;
    }

    public int lowerDistance(){

        int distance = this.end;
        int newDistance;

        for(int i = 0;  i < this.values-1; i++){
            int a = i + 1;

            newDistance = this.arrayOfPrimes[a] - this.arrayOfPrimes[i];

            distance = Math.min(newDistance, distance);
        }
        return distance;
    }

    public int higherDistance(){

        int distance = 0;
        int newDistance;

        for(int i = 0;  i < this.values; i++){
            int a = i + 1;
            newDistance = this.arrayOfPrimes[a] - this.arrayOfPrimes[i];
            distance = Math.max(newDistance, distance);
        }
        return distance;
    }
}
