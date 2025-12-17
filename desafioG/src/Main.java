import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        int start, end;

        start = in.nextInt();
        end = in.nextInt();

        Primes primes = new Primes(start, end);

        if (primes.atLeastTwoPrimes()){
            System.out.println(primes.higherDistance());
            System.out.println(primes.lowerDistance());
        }
        else {
            System.out.println("Interval contains fewer than two primes.");
        }
    }
}
