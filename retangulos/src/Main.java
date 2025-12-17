
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // create scanner to read input
        Scanner in = new Scanner(System.in);

        Rectangles r1 = getRectangle(in);

        Rectangles r2 = getRectangle(in);

        in.close();

        Rectangles rh = r1.getHull(r2);

        System.out.println(r1.getPerimeter());
        System.out.println(r2.getPerimeter());
        System.out.println(rh.getPerimeter());

        if (rh.isSquare()) {
            System.out.println("The hull is square! Wowzers");
        }
        else {
            System.out.println("Sua familia será exterminada");
        }
    }
    private static Rectangles getRectangle(Scanner input){
        // get 4 numbers from user, then create rectangle

        double left = input.nextDouble();
        double top = input.nextDouble();
        double right = input.nextDouble();
        double bottom = input.nextDouble();

        return new Rectangles(left, top, right, bottom);
    }
}
