import java.util.Scanner;

/**
 * @author Felipe Magno
 * inputs and outputs information from/to user
 * sets game up and according to the commands inputed
 * outputs the intended messages
 */

public class Main {

    public static void main(String[] args){
        
        //creating Scanner object
        Scanner in = new Scanner(System.in);

        //creating players objects
        Player p1 = createPlayer(in);
        Player p2 = createPlayer(in);

        //creating controller object
        Controller controller = new Controller(p1, p2);

        //calling method who runs the commands
        processCommand(in, p1, p2, controller);

        in.close();
    }

    /**
     * creates an object Player
     * @param in scanner created in main
     * @return object Player created
     */
    private static Player createPlayer(Scanner in){
        String name = in.nextLine();
        String fleet = in.nextLine();

        return new Player(name, fleet);
    }

    /**
     * checks which command will be processed
     * runs until users inputs "quit"
     * @param in scanner created in main
     * @param p1 player1 object
     * @param p2 player2 object
     * @param controller controller object
     */
    private static void processCommand(Scanner in, Player p1, Player p2, Controller controller) {

        while(controller.getStillGoing()){

            String command = in.next().toLowerCase();

            switch (command) {
                case "player":
                    processPlayer(controller);
                    break;

                case "score":
                    processScore(in, p1, p2);
                    break;

                case "fleet":
                    processFleet(in, p1, p2);
                    break;

                case "shoot":
                    processShoot(in, controller);
                    break;

                case "quit":
                    processQuit(controller);
                    break;

                default:
                    System.out.println("Invalid command");
                    in.nextLine();
            }
        }
    }

    /**
     * checks if game is over
     * outputs whose turn it is if game is not over
     * @param controller object controller
     */
    private static void processPlayer(Controller controller){

        if (controller.getIsGameFinished()){
            System.out.println("The game is over");
        }
        else {
            System.out.printf("Next player: %s\n",controller.getPlayerTurn().getName());
        }
    }

    /**
     * checks if name given is valid
     * if it is outputs player score
     * @param in scanner created in main
     * @param p1 player1 object
     * @param p2 player2 object
     */
    private static void processScore(Scanner in, Player p1, Player p2){

        String Name = in.nextLine().trim();

        int points;

        if (Name.equals(p1.getName())){
            points = p1.getPoints();
            System.out.printf("%s has %d points\n",Name,points);
        }
        else if (Name.equals(p2.getName())){
            points = p2.getPoints();
            System.out.printf("%s has %d points\n",Name,points);
        }
        else {
            System.out.println("Nonexistent player");
        }
    }

    /**
     * checks if name given is valid
     * if it is outputs player's current fleet
     * @param in scanner created in main
     * @param p1 player1 object
     * @param p2 player2 object
     */
    private static void processFleet(Scanner in, Player p1, Player p2){
        String Name = in.nextLine().trim();

        if (Name.equals(p1.getName())){
            System.out.println(p1.getCurrentFleet());
        }
        else if (Name.equals(p2.getName())){
            System.out.println(p2.getCurrentFleet());
        }
        else {
            System.out.println("Nonexistent player");
        }
    }

    /**
     * checks if game is finished
     * if it is, outputs that the game is over
     * if it isn't:
     * checks whose turn it is
     * if shotCell is not possible, outputs "invalid shot"
     * if it is:
     * shoots
     * switches turn
     * if shot fleet gets completely broken, sets game to be over
     * @param in scanner created in main
     * @param controller object controller
     * @pre: shotCell is an int number
     */
    private static void processShoot(Scanner in, Controller controller){
        int shotCell = in.nextInt();

        if (controller.getIsGameFinished()) {

            System.out.println("The game is over");

        }
        else {
            if (controller.isPossibleToShoot(shotCell)){
                controller.shooting(shotCell);
            }
            else {
                System.out.println("Invalid shot");
            }

        }
    }

    /**
     * checks if game is over
     * if it is, outputs winner
     * if it isn't outputs "The game was not over yet..."
     * @param controller object controller
     */
    private static void processQuit(Controller controller){

        if (controller.getIsGameFinished()){
            System.out.printf("%s won the game!\n", controller.getWhoWon());
        }

        else {
            System.out.println("The game was not over yet...");
        }

        controller.quit();
    }
}
