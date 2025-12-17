import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        game game = new game();

        Scanner in = new Scanner(System.in);

        int j = in.nextInt();

        int[] handI = new int[5];

        for (int i = 0; i < j; i++){

            in.nextLine();

            for (int a = 0; a < handI.length; a++) {
                int number = in.nextInt();
                handI[a] = number;
            }

            game.addHand(handI);
        }

        in.close();

        System.out.println(game.getNumOfWinners()+" "+game.getMaxPoints());

    }
}
