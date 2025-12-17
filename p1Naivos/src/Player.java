
/**
 * @author Felipe Magno
 * handles player's information, such as, amount of points, fleet name, etc.
 * stores and modifies the information
 */

public class Player {

    //constants
    private static final int POINTS_GAINED_MULTIPLIER = 100;
    private static final int POINTS_LOST_MULTIPLIER = 30;
    private static final int INITIAL_AMOUNT_OF_POINTS = 0;
    private static final char BROKEN_BOAT_CHAR = '*';
    private static final char WATER_CHAR = '.';

    //instance variables
    private final String name;
    private final char[] initialFleet;
    private final char[] currentFleet;
    private int points;
    private boolean isFleetBroken;

    //constructor

    /**
     * Constructor: sets player's name and fleet based
     * on values received. Sets up some instance variables values
     * @param name - name of Player (String)
     * @param fleet - fleet of Player (String)
     */
    public Player(String name, String fleet) {
        this.name = name;
        initialFleet = fleet.toCharArray();
        currentFleet = fleet.toCharArray();
        points = INITIAL_AMOUNT_OF_POINTS;
        isFleetBroken = false;
    }

    //methods

    /**
     * sets instance variable "isFleetBroken" to true
     */
    public void breakFleet(){
        isFleetBroken = true;
    }

    /**
     * figures which was the boat's first cell position
     * @param shotCell cell that was shot by the other player
     * @return position in which the boat's first cell (from left to right) is
     */
    private int figureInitialPosition(int shotCell){

        char boatChar = this.initialFleet[shotCell];
        int firstCell = shotCell;

        while(firstCell > 0 && boatChar == this.initialFleet[firstCell-1]){
            firstCell--;
        }

        return firstCell;
    }

    /**
     * figures which cell is the first boat's firstCell (from left to right)
     * checks boats size counting from this boat's firstCell till the last one
     * @param shotCell cell that was shot by the other player
     * @return returns the amount of Cells this boat occupies
     */
    private int figureBoatSize(int shotCell){
        char boatChar = this.initialFleet[shotCell];
        int boatSize = 0;
        int boatFirstCell = figureInitialPosition(shotCell);

        while(boatFirstCell < initialFleet.length && boatChar == this.initialFleet[boatFirstCell]){
            boatSize ++;
            boatFirstCell++;
        }
        return boatSize;
    }

    /**
     * checks weather shotCell is in boundaries of player's fleet
     * @param shotCell cell that was shot by the other player
     * @return true if shotCell is in player's fleet boundaries; false, in the opposite case
     */
    public boolean isCellInBoundaries(int shotCell){
        return shotCell >= 1 && shotCell <= initialFleet.length;
    }

    /**
     * turns shotCell from a default way of counting to the way arrays count (shotCell - 1)
     * checks if shotCell was a BROKEN_BOAT_CHAR and if it's not WATER_CHAR
     * if shotCell == BROKEN_BOAT_CHAR calls method shotBrokenBoat
     * else if shot != WATER_CHAR calls method shotBoat
     * if shotCell == WATER_CHAR does nothing
     * @param shotCell cell that was shot by the other player (conventional counting)
     * @param otherPlayer other player that is playing
     * @pre: shotCell is in player's fleet boundaries
     */
    public void shot(int shotCell, Player otherPlayer){

        shotCell--;

        char shotChar = this.currentFleet[shotCell];

        if (shotChar == BROKEN_BOAT_CHAR){
            shotBrokenBoat(shotCell, otherPlayer);
        }
        else if (isNotWater(shotCell)){
            shotBoat(shotCell, otherPlayer);
        }
    }

    /**
     * checks if the shotCell is a WATER_CHAR
     * @param shotCell cell that was shot by the other player (array way of counting)
     * @return returns true if char at shotCell positions != WATER_CHAR
     * @pre: shotCell is in player's fleet boundaries
     */
    private boolean isNotWater(int shotCell) {
        return this.initialFleet[shotCell] != WATER_CHAR;
    }

    /**
     * sets which boat was shot
     * sets first boat's position (from left to right)
     * sets boat's size
     * refreshes currentFleet
     * attribute correct amount of points (to the other player) that varies depending on boat's size
     * @param shotCell cell that was shot by the other player (array way of counting)
     * @param otherPlayer other player that is playing
     * @pre: shotCell is in player's fleet boundaries
     */
    private void shotBoat(int shotCell, Player otherPlayer){

        int boatSize, boatInitialPosition, boatFinalPosition;

        boatSize = figureBoatSize(shotCell);
        boatInitialPosition = figureInitialPosition(shotCell);
        boatFinalPosition = boatInitialPosition + boatSize - 1;

        refreshFleet(boatInitialPosition, boatFinalPosition);

        int points = boatSize * POINTS_GAINED_MULTIPLIER;

        otherPlayer.sumPoints(points);
    }

    /**
     * sets which boat was shot
     * sets first boat's position (from left to right)
     * sets boat's size
     * removes correct amount of points (from the other player) that varies depending on boat's size
     * @param shotCell cell that was shot by the other player (array way of counting)
     * @param otherPlayer other player that is playing
     * @pre: shotCell is in player's fleet boundaries
     */
    private void shotBrokenBoat(int shotCell, Player otherPlayer){
        int boatSize = figureBoatSize(shotCell);
        int points = -1 * boatSize * POINTS_LOST_MULTIPLIER;

        otherPlayer.sumPoints(points);
    }

    /**
     * adds points to player
     * if it is needed to remove points, points can be a negative amount
     * @param points amount of points to be summed to player
     * @pre: points != null
     */
    private void sumPoints(int points){
        this.points += points;
    }

    /**
     * refreshes currentFleet, replacing broken boats char's to BROKEN_BOAT_CHAR
     * @param firstCell first boat's cell (from left to right)
     * @param lastCell last boat's cell (from left to right)
     */
    private void refreshFleet(int firstCell, int lastCell) {
        for(int i = firstCell; i <= lastCell; i++){
            this.currentFleet[i] = BROKEN_BOAT_CHAR;
        }
    }

    /**
     * @return this player's name, as a String
     * @pre: this.name != null
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return amount of points this player has
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * @return currentFleet as a String
     */
    public String getCurrentFleet(){
        return new String(this.currentFleet);
    }

    /**
     * @return returns true if fleet has been broken
     */
    public boolean getIsFleetBroken(){
        return isFleetBroken;
    }

}
