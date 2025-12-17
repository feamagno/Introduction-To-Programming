import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        int L = in.nextInt();
        int C = in.nextInt();
        in.nextLine();
        int P = in.nextInt();
        in.nextLine();

        ConwayGOL c = new ConwayGOL(L,C);

        processAddLines(c, in);

        processRun(c,P);

        in.close();
    }

    private static void processRun(ConwayGOL c, int P){
        for (int i = 0; i < P; i++){
            c.runGeneration();
            printGrid(c);
        }
    }

    private static void processAddLines(ConwayGOL c, Scanner in){
        for (int i = 1; i < c.getNumRows()-1; i++){
            String[] line = in.nextLine().split(" ");
            c.addLine(line, i);
        }
    }

    private static void printGrid(ConwayGOL c){

        boolean[][] grid = c.getState();

        System.out.println();

        for (int i = 0; i < c.getNumRows()-2; i++){
            for (int j = 0; j < c.getNumCols()-2; j++){
                if (grid[i][j]){
                    System.out.print("# ");
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("*********************************************");
    }

    private static void printRawGrid(ConwayGOL c){
        boolean[][] grid = c.getGrid();

        for (int i = 0; i < c.getNumRows(); i++){
            for (int j = 0; j < c.getNumCols(); j++){
                if (grid[i][j]){
                    System.out.print("#");
                }
                else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
