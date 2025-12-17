import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        double limPeso = in.nextDouble();
        int k = in.nextInt();

        Caminhao c = new Caminhao(k, limPeso);

        for (int i = 0; i < k; i++) {
            String name = in.next();
            double weight = in.nextDouble();
            double price = in.nextDouble();

            c.addGrocerie(name, price, weight);
        }

        c.sortByUtility();

        System.out.printf("%.2f\n",c.maxPrice());

        COumidaIterator it = c.COumidaIterator();

        while (it.hasNext()){
            COumida a = it.next();
            System.out.printf(a.getName()+"\n");
        }
    }
}
