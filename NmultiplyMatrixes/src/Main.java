import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        MatrixMultiplier a = setMatrix(in);
        MatrixMultiplier b = setMatrix(in);

        in.close();

        if (a.canMultiply(b)){
            MatrixMultiplier c = a.multiply(b);
            printMatrix(c);
        }
        else {
            System.out.println("Impossible to multiply the two matrices");
        }
    }

    private static void printMatrix(MatrixMultiplier m){

        for (int i = 0; i < m.getRows(); i++){
            for (int j = 0; j < m.getCols()-1; j++){
                System.out.printf("%d ",m.getValueMatrix(i,j));
                }
            System.out.println(m.getValueMatrix(i,m.getCols()-1));
        }
    }

    private static MatrixMultiplier setMatrix(Scanner in){

        int row = in.nextInt();
        int col = in.nextInt();
        in.nextLine();

        MatrixMultiplier m = new MatrixMultiplier(row, col);

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                int value = in.nextInt();
                m.set(value, i, j);
            }
            in.nextLine();
        }
        return m;
    }
}
