import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();

        int[] list = new int[n];

        for (int i = 0; i < n; i++){
            list[i] = in.nextInt();
        }

        in.nextLine();

        int maxWaitTime = in.nextInt();

        in.close();

        Bus bus = new Bus(maxWaitTime, list);

        System.out.println(bus.getNumOfTrips());

    }

}
