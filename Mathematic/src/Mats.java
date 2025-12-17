public class Mats {
    // pre: n >= 1

    public static boolean isPrime(int n){

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

    public static boolean isPerfect(int n){
        // Pre: n>= 1

        int sum = 0;
        double limit = n/2;

        for(int d = 1; d <= limit; d++){

            if(n%d == 0){
                sum += d;
            }
        }
        return sum == n;
    }

    public static int factorial(int n){

        if(n==0){
            return 1;
        }
        else{
            return n * factorial(n-1);
        }
    }

    public static int factorial2(int n){

        int result = 1;

        for(int i = 1; i<=n; i++){
            result = result * i;
        }

        return result;
    }

    public static int gcdRec(int a, int b){
        //returns gcd(a,b)
        if (b==0){
            return a;
        }
        else{
            return gcdRec(b,a%b);
        }
    }

    public static int gcdInt(int x, int y){
        if (y==0){
            return x;
        }

        int a, b, old_a, old_b;

        a = x;
        b = y;

        while (b > 0){
            old_a = a;
            a = b;
            b = old_a%b;
        }
        return a;
    }

    public static int sumRec(int n){

        int sum = 0;
        for(int i = 1; i <= n; i++){
            sum += i*i;
        }
        return sum;
    }

    public static int sumInt(int n){

        if (n == 0){
            return n;
        }
        else {
            return (n*n) + sumInt(n-1);
        }
    }
}
