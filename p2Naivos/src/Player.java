
public class Player {
    /**
     * @author Diogo Antunes(67763) & Felipe Magno(67994)
     * Defines a player for the game battleships
     * Allows increasing or decreasing the user's score
     * Manages and tracks the status of the player's fleet
     */
    // constants
    private static final char WATER = '.';
    private static final char SUNKEN = '*';

    private static final int VALID_THRESHOLD = 0;
    // instance variables
    private String name;
    private char[][] layout; //Original unchanged fleet
    private char[][] fleet; //Current fleet
    private int numCannons; //number of grid spaces with a floating ship segment
    private int score;

    /**
     * Constructor
     * @param name the unique player name                            (String)
     * @param layout the array holding data for the player's fleet (String[])
     * @pre: !name.equals(null) && layout != null
     */
    public Player(String name, String[] layout) {
        this.name = name;
        this.layout = setFleet(layout);
        fleet = setFleet(layout);
        updateCannonCount();
        //Player score always starts at 0
        score = 0;
    }

    // methods

    /**
     * Modifies the Player objects' score variable by a given amount
     * @param amount the number which is added to the score (int)
     */
    public void addScore(int amount) {
        score += amount;
    }

    /**
     * Returns the size of the ship for the executed shot
     * Updates number of active ships
     * @param rowIndex the grid y-axis index aimed at by the shot (int)
     * @param colIndex the grid x-axis index aimed at by the shot (int)
     * @pre: rowIndex >= 0 && rowIndex < fleet.length
     * 	  && colIndex >= 0 && colIndex < fleet[0].length
     * @return value, int, corresponding to the size of the ship struck
     * to the opposing player at the end of the turn
     */
    public int checkShot(int rowIndex, int colIndex) {
        int shipSize = 0;
        if (fleet[rowIndex][colIndex] != WATER) {
            //assumes smallest size of 1
            shipSize++;
            //shot hits a ship, perform changes to array and retrieve size
            shipSize += sizeHorizontal(rowIndex, colIndex);
            shipSize += sizeVertical(rowIndex, colIndex);
            if (fleet[rowIndex][colIndex] != SUNKEN) {
                numCannons -= shipSize;
                fleet[rowIndex][colIndex] = SUNKEN;
            }
        }
        return shipSize;
    }

    /**
     * Checks whether the Player Object is greater or lesser than other
     * in terms of score and name
     * By the game's setup, two players can never be equal in this sense
     * @param other the Player Object being compared to (Player)
     * @pre: !name.equals(other.getName())
     * @return true, if this should come before other in a sorted array
     */
    public boolean greaterThan(Player other) {
        //player has greater score than other
        boolean condition1 = this.score > other.getScore();
        //Scores are the same but this.name comes earlier alphanumerically
        boolean condition2 = this.score == other.getScore() &&
                             this.name.compareTo(other.getName()) < VALID_THRESHOLD;

        return condition1||condition2;
    }

    /**
     * Returns the player's fleet as an iterable object
     * @return FleetIterator object for the player's fleet
     */
    public FleetIterator getFleet() {
        return new FleetIterator(fleet);
    }
    /**
     * Returns the Player object's name value
     * @return value of the object's name variable
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the number of rows in the Player object's fleet variable
     * @return integer value for the number of rows in the fleet
     */
    public int getNumFleetRows(){
        return fleet.length;
    }
    /**
     * Returns the number of columns in the Player object's fleet variable
     * @return integer value for the number of columns in the fleet
     */
    public int getNumFleetCols(){
        return fleet[0].length;
    }
    /**
     * Returns the Player object's score value
     * @return current value of the object's score int variable
     */
    public int getScore() {
        return score;
    }
    /**
     * Returns whether a player has lost all ships
     * @pre: numCannons >= 0
     * @return boolean value representing whether all ships are sunk
     */
    public boolean isInactive() {
        return numCannons == 0;
    }

    /**
     * Returns whether a shot's target is valid for the players fleet
     * @param row the grid y-axis position aimed at by the shot (int)
     * @param col the grid x-axis position aimed at by the shot (int)
     * @return boolean value of whether the indicated position is within
     * the dimensions of the fleet
     */
    public boolean isCoordinateInGrid(int row, int col) {
        int rows = getNumFleetRows();
        int cols = getNumFleetCols();
        return row <= rows && row > 0 && col <= cols && col > 0;
    }
    /**
     * Checks whether the spot being aimed at contains a sunken ship
     * @param rowIndex the grid y-axis index aimed at by the shot (int)
     * @param colIndex the grid x-axis index aimed at by the shot (int)
     * @pre: isCoordinateInGrid(rowIndex+1, ColIndex+1) == true
     * @return boolean value for whether a segment is part of a sunk ship
     */
    public boolean isShipSunk(int rowIndex, int colIndex) {
        return fleet[rowIndex][colIndex] == SUNKEN;
    }

    //------------private methods
    /**
     * Sets the player's fleet and layout variables
     * @param layout an array of fleet lines consisting of the grid (String[])
     * @pre: layout != null
     */
    private char[][] setFleet(String[] layout){
        char[][] tmp = new char[layout.length][layout[0].length()];
        for (int i = 0; i < layout.length; i++){
            tmp[i] = layout[i].toCharArray();
        }
        return tmp;
    }
    /**
     * Counts spaces occupied by the ship horizontally at the given index
     * Updates the value of fleet (no change if already sunk)
     * Returns the horizontal size of the ship
     * @param rowIndex the grid y-axis index aimed at by the shot (int)
     * @param colIndex the grid x-axis index aimed at by the shot (int)
     * @return int value for the number of grid spaces occupied
     * horizontally by the ship at the given index.
     */
    private int sizeHorizontal(int rowIndex, int colIndex){
        int size = 0;
        int j = colIndex;
        //comparing prior elements
        while (j-1 >= 0 && j < layout[0].length &&  layout[rowIndex][j] == layout[rowIndex][j-1]) {
            fleet[rowIndex][j-1] = SUNKEN;
            size ++;
            j--;
        }
        j = colIndex;
        //comparing subsequent elements
        while (j >= 0 && j+1 < layout[0].length && layout[rowIndex][j] == layout[rowIndex][j+1]) {
            fleet[rowIndex][j+1] = SUNKEN;
            size ++;
            j++;
        }
        return size;
    }

    /**
     * Counts spaces occupied by the ship vertically at the given index
     * Updates the value of fleet (no change if already sunk)
     * Returns the vertical size of the ship
     * @param rowIndex the grid y-axis index aimed at by the shot (int)
     * @param colIndex the grid x-axis index aimed at by the shot (int)
     * @return int value for the number of grid spaces occupied vertically
     * by the ship at the given index.
     */
    private int sizeVertical(int rowIndex, int colIndex) {
        int i = rowIndex;
        int size = 0;
        while (i-1 >= 0 && i < layout.length &&  layout[i][colIndex] == layout[i-1][colIndex]) {
            fleet[i-1][colIndex] = SUNKEN;
            size ++;
            i--;
        }
        i = rowIndex;
        //comparing subsequent elements
        while (i >= 0 && i+1 < layout.length &&  layout[i][colIndex] == layout[i+1][colIndex]) {
            fleet[i+1][colIndex] = SUNKEN;
            size ++;
            i++;
        }
        return size;
    }

    /**
     * Calculates total number of cannons in fleet
     */
    private void updateCannonCount() {
        int size = fleet.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < fleet[0].length; j++) {
                if (layout[i][j] != WATER) {
                    numCannons++;
                }
            }
        }
    }
}
