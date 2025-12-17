public class Fibonacci {

    private static final double MAX = 4000000;

    public static double sumEven(){
        double sum = 0;
        double lastNum = 1;
        double num = 1;
        double tmp;
        while (num < MAX){
            if (num % 2 == 0)
                sum += num;
            tmp = num + lastNum;
            lastNum = num;
            num = tmp;
        }
        return sum;
    }
}
