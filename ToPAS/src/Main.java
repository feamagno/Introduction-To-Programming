import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //creates Scanner object
        Scanner in = new Scanner(System.in);

        //collecting variables from user
        //to create crops objects
        Crop c1 = creatingCrop(in);
        Crop c2 = creatingCrop(in);

        WaterSystem ws = new WaterSystem(c1,c2);

        //collecting information of each day
        //processes each day's information
        processRecord(in, ws, c1, c2);

        in.close();
    }

    private static Crop creatingCrop(Scanner in){
        //collects information from user
        //returns object Crop

        String name = in.next().toLowerCase();
        int min = in.nextInt();
        int max = in.nextInt();

        return new Crop(name,max,min);
    }

    private static void processRecord(Scanner in, WaterSystem ws,Crop c1, Crop c2){
        //collecting information about each day and how many days there are
        //checks if crops are happy and prints how many days is suitable for both
        //prints which crop is the most happiest crop of all the farm around this ol town

        //gets how many days there are
        int numRecords = in.nextInt();

        //creates loop with that many days
        for(int i = 1; i <= numRecords; i++) {

            String day = in.next();
            ws.registerDay(day);

        }
        System.out.printf("%d days suitable for both\n",ws.getHappyBothDays());
        if (c1.getHappy()>c2.getHappy()){
            System.out.printf("Opt for %s",c1.getName());
        }
        else if (c1.getHappy()<c2.getHappy()) {
            System.out.printf("Opt for %s",c2.getName());
        }
        else {
            System.out.print("Equivalent options");
        }
    }
}
