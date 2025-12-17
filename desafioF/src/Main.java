import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        long n1, a1, b1, n2, a2, b2, d;

        n1 = in.nextInt();
        a1 = in.nextInt();
        b1 = in.nextInt();

        in.nextLine();

        n2 = in.nextInt();
        a2 = in.nextInt();
        b2 = in.nextInt();
        d = in.nextInt();

        in.close();

        Lucas lucas = new Lucas();

        System.out.println(lucas.lucasSeq(n1,a1,b1));
        System.out.println(lucas.sumLucasSeq(n2,a2,b2,d));
    }
}
