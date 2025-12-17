
/**
 * @author Felipe Magno
 * handles information from both players and from the game as a whole
 * such as who has more points, whose turn it is, if the game is  finished etc.
 */

public class Controller {

    //constants
    private static final char BROKEN_BOAT_CHAR = '*';
    private static final char WATER_CHAR = '.';

    //instance variables
    private Player playerTurn;
    private final Player player1, player2;
    private String whoWon;
    private boolean gameFinished;
    private boolean stillGoing;

    /**
     * Constructor: sets instance variables player1 and player2
     * on values received. Sets up some instance variables values
     * @param player1 first player to play
     * @param player2 second player to play
     * @pre: player1 != null && player2 != null
     */
    public Controller(Player player1, Player player2){
        this.gameFinished = false;
        this.stillGoing = true;
        this.player1 = player1;
        this.player2 = player2;

        this.playerTurn = player1;
    }

    //methods

    /**
     * checks weather if player who was shot current fleet is composed only by BROKEN_BOAT_CHAR || WATER_CHAR
     * if fleet is completely broken:
     * breaks players fleet
     * calls setGameFinished method
     * calls setWhoWon method
     * @param shotPlayer player who was shot
     */
    public void isFleetCompletelyBroken(Player shotPlayer){
        String fleet = shotPlayer.getCurrentFleet();

        int cell = 0;

        while (cell < fleet.length() && (fleet.charAt(cell) == (BROKEN_BOAT_CHAR) || fleet.charAt(cell) == (WATER_CHAR))){
            cell++;
            if (cell == fleet.length()){
                shotPlayer.breakFleet();
                setGameFinished();
                setWhoWon();
            }
        }
    }

    /**
     * sets instance variable stillGoing to false
     */
    public void quit(){
        this.stillGoing = false;
    }

    /**
     * @return true if game is still going; false if user hasn't inputted "quit"
     */
    public boolean getStillGoing(){
        return this.stillGoing;
    }

    /**
     * @return object player of whose turn it is
     * @pre: game is not over
     */
    public Player getPlayerTurn(){
        return this.playerTurn;
    }

    /**
     * @return whoever won as a String
     */
    public String getWhoWon(){
        return this.whoWon;
    }

    /**
     * switches turn to the other player
     */
    public void switchPlayerTurn(){
        if (isPlayer1Turn()){
            playerTurn = player2;
        }
        else {
            playerTurn = player1;
        }
    }

    /**
     * @return true if it is player1 turn; false if it is player2 turn
     */
    private boolean isPlayer1Turn(){
        return playerTurn.getName().equals(player1.getName());
    }

    /**
     * checks if it is player1 turn
     * if it is, returns player1
     * if it isn't returns player 2
     * @return the player whose turn it is not
     */
    private Player otherPlayer(){
        Player otherPlayer;
        if (isPlayer1Turn()){
            otherPlayer = player2;
        }
        else {
            otherPlayer = player1;
        }

        return otherPlayer;
    }

    /**
     * sets instance variable gameFinished to true
     */
    public void setGameFinished(){
        gameFinished = true;
    }

    /**
     * @return true if game is finished; false if it isn't
     */
    public boolean getIsGameFinished(){
        return gameFinished;
    }

    /**
     * sets whoWon, to the player who has the most points
     * if both players have the same amount of points:
     * sets whowWon to player whose fleet is not broken
     */
    public void setWhoWon() {

        int player1Points = player1.getPoints();
        int player2Points = player2.getPoints();

        String player1Name = player1.getName();
        String player2Name = player2.getName();

        if (player1Points > player2Points){
            whoWon = player1Name;
        }
        else if (player1Points < player2Points) {
            whoWon = player2Name;
        }
        else {
            if (player1.getIsFleetBroken()){
                whoWon = player2Name;
            }
            else {
                whoWon = player1.getName();
            }
        }
    }

    /**
     * checks if shotCell is in boundaries
     * if it is:
     * shoots otherPlayer's fleet
     * checks if fleet is completely broken
     * switches player's turn
     * @param shotCell cell that was shot (conventional way of counting)
     */
    public void shooting(int shotCell){

        if (otherPlayer().isCellInBoundaries(shotCell)){

            otherPlayer().shot(shotCell, playerTurn);

            isFleetCompletelyBroken(otherPlayer());

            switchPlayerTurn();
        }
    }

    /**
     * @param shotCell cell that was shot (conventional way of counting)
     * @return true if cell is possible to shoot; false if it isn't
     */
    public boolean isPossibleToShoot(int shotCell){
        return otherPlayer().isCellInBoundaries(shotCell);
    }
}