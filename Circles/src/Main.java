import java.util.Scanner;

public class Main {

    public static void main(String [] args) {

        Scanner in = new Scanner(System.in);

        //gets circles variables
        Circles circle1 = getCircle(in);

        //prints menu
        printMenu();

        //chooses and operates option
        executeMenu(circle1, in);
    }

    private static void printMenu(){
        //prints options
        System.out.println("(P) gets circles perimeter");
        System.out.println("(A) gets area");
        System.out.println("(LP) figures if a point is in the circle");
        System.out.println("(LC) figures relation first circle has with another");
    }

    private static Circles getCircle(Scanner input){
        //returns a circle
        System.out.println("x, y, radius:");
        double x = input.nextDouble();
        double y = input.nextDouble();
        double radius = input.nextDouble();

        return new Circles(x,y,radius);
    }

    private static void executeMenu(Circles circle,Scanner input){
        //figures which option user has chosen and executes it
        String option = input.next().toUpperCase();
        switch (option){
            case "P":
                double perimeter = processPerimeter(circle);
                System.out.printf("your perimeter is %.2f gayzao",perimeter);
                break;
            case "A":
                double area = processArea(circle);
                System.out.printf("your area is %.2f gayzao",area);
                break;
            case "LP":
                processIsPointInside(circle,input);
                break;
            case "LC":
                processIsCircleInside(circle, input);
                break;
        }
    }

    private static void processIsPointInside(Circles circle,Scanner input){
        //process if point is inside
        double x = input.nextDouble();
        double y = input.nextDouble();

        if (circle.isPointInside(x,y)){
            System.out.println("WOWZERS TA DENTRO");
        }
        else {
            System.out.println("MORRA MORRA SEU PONTO NN TA DENTRO");
        }
    }

    private static double processPerimeter(Circles circle){
        //return perimeter
        return circle.getPerimeter();
    }
    private static double processArea(Circles circle){
        //return area
        return circle.getArea();
    }

    private static void processIsCircleInside(Circles circle,Scanner input){
        //creates new circles
        //checks its relation with the main circle
        Circles c2 = getCircle(input);

        switch(circle.isCircleInside(c2)){
            case "contains":
                System.out.println("circle is contained");
                break;

            case "intersects":
                System.out.println("circle intersects");
                break;

            case "nothing":
                System.out.println("no relation");
                break;

            default:
                System.out.println("ih carai sla qq rolo");
        }
    }
}
