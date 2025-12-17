import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        int M = in.nextInt();

        in.nextLine();

        int N = in.nextInt();
        int[] days = new int[N];

        in.nextLine();

        for(int i = 0; i < N; i++){

            int input = in.nextInt();
            days[i] = input;

            in.nextLine();
        }
        //eu amo voce mentira
        in.close();

        GettingHotterBabe gettingHotterBabe = new GettingHotterBabe(M,days);

        if (gettingHotterBabe.doWeHaveAWave()){
            System.out.println("WAVE");
        }
        else {
            System.out.println("FLAT");
        }

    }
}
