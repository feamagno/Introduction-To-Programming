import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * @author Diogo Antunes(67763) & Felipe Magno(67994)
     */
    //message constants
    private static final String ELIMINATED_PLAYER = "Eliminated player";
    private static final String GAME_OVER = "The game is over";
    private static final String GAME_WIN ="%s won the game!\n";
    private static final String INVALID_COMMAND = "Invalid command";
    private static final String INVALID_PLAYER = "Nonexistent player";
    private static final String INVALID_SHOT = "Invalid shot";
    private static final String MIDGAME_QUIT = "The game was not over yet...";
    private static final String NEXT_PLAYER = "Next player: %s\n";
    private static final String SCORE_MESSAGE = "%s has %d points\n";
    private static final String SELF_SHOT = "Self-inflicted shot";

    //command constants
    private static final String PLAYER = "player";
    private static final String PLAYERS = "players";
    private static final String SCORE = "score";
    private static final String SCORES = "scores";
    private static final String FLEET = "fleet";
    private static final String SHOOT = "shoot";
    private static final String QUIT = "quit";

    //other constants
    private static final boolean ENTRY_ORDER = false;
    private static final boolean SORTED = true;
    private static final String INPUT_FILE = "fleets.txt";

    public static void main(String[] args) throws FileNotFoundException {
        //instantiating required objects
        Scanner input = new Scanner(System.in);
        Battleships game = createGame(input);

        //main game loop
        String command;

        do {
            command = input.next();
            processCommand(input, game, command);

        } while (!command.equals(QUIT));
        input.close();

    }
    // methods

    /**
     * Returns a Battleships object instance from input
     * Containing numOfPlayers Player Objects indicated by input
     * @param input the Scanner instance used to receive input               (Scanner)
     * @return a Battleships Object instance that keeps track of all game elements
     */
    private static Battleships createGame(Scanner input) throws FileNotFoundException {
        int numOfPlayers = input.nextInt();
        input.nextLine();
        Battleships newGame = new Battleships(numOfPlayers);
        createPlayers(input, newGame, numOfPlayers);

        return newGame;
    }

    /**
     * Creates the participating player objects through the Battleships game Object
     * And assigns the fleets to be used
     * @param input the Scanner instance used to receive input               (Scanner)
     * @param newGame the game object receiving new players              (Battleships)
     * @param numOfPlayers total number of participating players                 (int)
     * @pre: numOfPlayers > 1
     */
    private static void createPlayers(Scanner input, Battleships newGame, int numOfPlayers)
            throws FileNotFoundException {
        Scanner reader = new Scanner(new FileReader(INPUT_FILE));
        int layoutToRead = 1; //pos of fleet in input file
        int lastLayoutNum = 0;
        String[] fleet = null;

        for (int i = 0; i < numOfPlayers; i++) {
            String playerName = input.nextLine();
            int layoutNum = input.nextInt();
            input.nextLine();
            //skipping unused fleets
            while (layoutToRead < layoutNum) {
                skipFleet(reader);
                layoutToRead++;
            }
            if (lastLayoutNum != layoutNum) {
                fleet = readNextFleet(reader);
                layoutToRead++;
            }
            newGame.addPlayer(playerName, fleet);
            lastLayoutNum = layoutNum;
        }
        reader.close();
    }

    /**
     * Checks the input against expected values
     * Acts upon an expected command
     * @param input the Scanner instance used to receive input       (Scanner)
     * @param game    the object that coordinates game functions (Battleships)
     * @param command the action to be carried out                    (String)
     */
    private static void processCommand(Scanner input, Battleships game, String command) {
        switch(command) {
            case PLAYER -> displayPlayerTurn(game);
            case PLAYERS -> displayActivePlayers(game);
            case SCORE -> displayScore(input, game);
            case SCORES -> displayAllScores(game);
            case FLEET -> displayFleet(input, game);
            case SHOOT -> processShot(input, game);
            case QUIT -> quitGame(game);
            default -> invalidCommand(input);
        }
    }

    //------------Command-processing methods
    /**
     * Displays the name of all players with remaining active ships
     * @param game  the object that coordinates game functions (Battleships)
     */
    private static void displayActivePlayers(Battleships game) {
        PlayerIterator pIterator = game.getPlayerIterator(ENTRY_ORDER);
        while (pIterator.hasNext()) {
            System.out.println(pIterator.getNextName());
        }
    }

    /**
     * Displays the current attacking Player
     * @param game  the object that coordinates game functions (Battleships)
     */
    private static void displayPlayerTurn(Battleships game) {
        if (game.hasWinner()) {
            System.out.println(GAME_OVER);
        } else {
            String playerName = game.getNextPlayerName();
            System.out.printf(NEXT_PLAYER, playerName);
        }
    }
    /**
     * Displays the current score of a given existing player
     * @param input the Scanner instance used to receive input     (Scanner)
     * @param game  the object that coordinates game functions (Battleships)
     */
    private static void displayScore(Scanner input, Battleships game) {
        String playerName = input.nextLine().trim();
        if (game.hasPlayer(playerName)) {
            int score = game.getScore(playerName);
            System.out.printf(SCORE_MESSAGE, playerName, score);
        } else {
            //no player with such name exists
            System.out.println(INVALID_PLAYER);
        }
    }
    /**
     * Displays the name of all players with remaining active ships
     * @param game  the object that coordinates game functions (Battleships)
     */
    private static void displayAllScores(Battleships game) {
        PlayerIterator pIterator = game.getPlayerIterator(SORTED);
        while (pIterator.hasNext()) {
            System.out.printf(SCORE_MESSAGE, pIterator.getNextName(), pIterator.getNextScore());
        }
    }
    /**
     * Displays the current fleet of a given existing player
     * @param input the Scanner instance used to receive input     (Scanner)
     * @param game  the object that coordinates game functions (Battleships)
     */
    private static void displayFleet(Scanner input, Battleships game) {
        String playerName = input.nextLine().trim();
        if (game.hasPlayer(playerName)) {
            FleetIterator playerFleet = game.getFleet(playerName);
            while (playerFleet.hasNext()) {
                System.out.println(playerFleet.next());
            }
        } else {
            //no player with such name exists
            System.out.println(INVALID_PLAYER);
        }
    }
    /**
     * Current-turn Player attacks Opposing Player
     * Fleets are updated accordingly
     * Negative or positive score-change applied to attacking Player
     * @param input the Scanner instance used to receive input     (Scanner)
     * @param game  the object that coordinates game functions (Battleships)
     */
    private static void processShot(Scanner input, Battleships game) {
        int rowPos = input.nextInt();
        int colPos = input.nextInt();
        String targetName = input.nextLine().trim();
        if (game.hasWinner()) {
            System.out.println(GAME_OVER);
        } else {
            int scenario = game.shotIsValid(rowPos, colPos, targetName);
            switch (scenario) {
                case Battleships.SELF_HIT_CASE -> System.out.println(SELF_SHOT);
                case Battleships.INVALID_PLAYER_CASE -> System.out.println(INVALID_PLAYER);
                case Battleships.ELIMINATED_PLAYER_CASE -> System.out.println(ELIMINATED_PLAYER);
                case Battleships.INVALID_COORS_CASE -> System.out.println(INVALID_SHOT);
                case Battleships.VALID -> game.shoot(rowPos, colPos, targetName);
            }
        }
    }
    /**
     * Displays the winning status
     * Serves as the last iteration to the main loop
     * @param game the object that coordinates game functions (Battleships)
     */
    private static void quitGame(Battleships game) {
        if (game.hasWinner()) {
            System.out.printf(GAME_WIN, game.getWinnerName());
        } else {
            System.out.println(MIDGAME_QUIT);
        }
    }

    /**
     * Skips ahead an unused fleet without using its' contents
     * @param reader the Scanner instance used to read input from input file (Scanner)
     */
    private static void skipFleet(Scanner reader) {
        int rows = reader.nextInt();
        reader.nextLine();
        for (int j = 0; j < rows; j++) {
            reader.nextLine();
        }
    }

    /**
     * Reads and assembles the next fleet in the input file, then returns it
     * @param reader the Scanner instance used to read input from input file (Scanner)
     * @return the next fleet in the input file as a String array
     */
    private static String[] readNextFleet(Scanner reader) {
        int rows = reader.nextInt();
        reader.nextLine();
        String[] fleet = new String[rows];
        for (int row = 0; row < rows; row++) {
            fleet[row] = reader.nextLine();
        }
        return fleet;
    }
    /**
     * Handles unexpected command input
     * @param input the Scanner instance used to receive input (Scanner)
     */
    private static void invalidCommand(Scanner input) {
        input.nextLine();
        System.out.println(INVALID_COMMAND);
    }
}
