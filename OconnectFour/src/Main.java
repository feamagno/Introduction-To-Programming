import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        ConnectFour c = new ConnectFour();

        setConnect(in, c);

        if (c.whoWon().equals('.')){
            if (!c.stillGoing()){
                System.out.println("JOGANDO");
            }
            else {
                System.out.println("EMPATE");
            }
        }
        else if (c.whoWon().equals('X')) {
            System.out.println("GANHOU X");
        }
        else {
            System.out.println("GANHOU O");
        }

        in.close();
    }


    private static void setConnect(Scanner in, ConnectFour c){
        for (int i = 0; i < 6; i++) {
            String line = in.nextLine();
            c.addLine(line, i);
        }
    }
}
