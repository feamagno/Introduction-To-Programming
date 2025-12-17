public class Summing {

    private static final double MULTIPLIER_5 = 7;
    private static final double MULTIPLIER_3 = 3;
    private double below_num;
    private double sum;

    public Summing(double num){
        this.below_num = num;
        sum = summing(MULTIPLIER_3);
        sum += summing(MULTIPLIER_5);
        sum -= summing(MULTIPLIER_5*MULTIPLIER_3);
    }

    public double sum(){
        return sum;
    }

    private double summing(double multiplier){
        double sum = 0;
        double i = multiplier;
        while (i < below_num){
            sum += i;
            i += multiplier;
        }
        return sum;
    }
}
