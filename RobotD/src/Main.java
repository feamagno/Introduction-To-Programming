import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        //setting position variables
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int aut1 = in.nextInt();

        int x2 = in.nextInt();
        int y2 = in.nextInt();
        int aut2 = in.nextInt();

        in.nextLine();

        Robot r1 = new Robot(x1, y1, aut1);
        Robot r2 = new Robot(x2, y2, aut2);

        processCommand(in, r1, r2, aut1, aut2);
        processCommand(in, r1, r2, aut1, aut2);
        processCommand(in, r1, r2, aut1, aut2);
        processCommand(in, r1, r2, aut1, aut2);

        in.close();
    }

    private static void processCommand(Scanner in, Robot r1, Robot r2, int aut1, int aut2){

        String command = in.next().toUpperCase();

        Robot mainRobot, otherRobot;
        int mainAut;
        int i = in.nextInt();

        if (i == 1){
            mainRobot = r1;
            mainAut = aut1;
            otherRobot = r2;
        } else {
            mainRobot = r2;
            mainAut = aut2;
            otherRobot = r1;
        }

        switch (command){

            case "MOVE":
                processMove(in, mainRobot, otherRobot, i, mainAut);
                break;

            case "COLLISION":
                processCollision(in, mainRobot, otherRobot);
                break;

            case "POSITION":
                processPosition(mainRobot, i);
                break;

            case "CLOSESTWALL":
                processClosestWall(mainRobot, otherRobot);
                break;

            case "RETURN":
                processReturnRobot(mainRobot, otherRobot, i);
                break;

            case "SURVEY":
                processSurvey(in, mainRobot, otherRobot, i);
                break;
        }
        in.nextLine();
    }

    private static void processMove(Scanner in, Robot mainRobot, Robot otherRobot, int i, int mainAut){

        int x = mainRobot.getX();
        int y = mainRobot.getY();

        int distance = in.nextInt();
        int direction = in.nextInt();

        if (mainRobot.willCollide(otherRobot,distance,direction)){
            System.out.printf("Collision detected. Robot %d could not move.\n",i);
        }
        else {
            int moved = mainRobot.move(distance, direction, x, y,1, mainRobot.getAut());

            if (moved == 0){
                System.out.printf("Wall detected. Robot %d could not move.\n",i);
            }
            else if ((moved < distance)&&(moved>=0)){
                System.out.printf("Wall detected. Robot %d moved %d meters.\n",i,moved);
            }
            else if(moved == -1) {
                System.out.printf("No battery. Robot %d could not move.\n", i);
            }
            else {
                System.out.printf("Robot %d moved successfully.\n",i);
            }
        }
    }

    private static void processCollision(Scanner in,Robot mainRobot, Robot otherRobot) {

        int distance = in.nextInt();
        int direction = in.nextInt();

        if (mainRobot.willCollide(otherRobot, distance, direction)) {
            System.out.println("Robots will collide.");
        } else {
            System.out.println("Robots will not collide.");
        }
    }

    private static void processPosition(Robot mainRobot, int i){
        int y = mainRobot.getY();
        int x = mainRobot.getX();
        System.out.printf("Robot %d is at the position (%d,%d).\n",i,x,y);
    }

    private static void processClosestWall(Robot mainRobot, Robot otherRobot){
        int direction = mainRobot.closestWall(otherRobot,true);

        System.out.printf("%d is the closest exit direction.\n",direction);
    }

    private static void processReturnRobot(Robot mainRobot, Robot otherRobot, int i){

        if (mainRobot.robotReturn(otherRobot, mainRobot.getAut(), mainRobot.getX(), mainRobot.getY(),true)){
            System.out.printf("Robot %d can return.\n",i);
        }
        else {
            System.out.printf("Robot %d cannot return.\n",i);
        }
    }

    private static void processSurvey(Scanner in, Robot mainRobot, Robot otherRobot, int i){

        int xi = in.nextInt();
        int yi = in.nextInt();

        if(mainRobot.survey(otherRobot, xi, yi)){
            System.out.printf("Position (%d,%d) can be surveyed by robot %d.\n",xi,yi,i);
        }
        else {
            System.out.printf("Position (%d,%d) cannot be surveyed by robot %d.\n",xi,yi,i);
        }
    }
}