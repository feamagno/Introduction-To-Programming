
public class Battleships {
    /**
     * @author Diogo Antunes(67763) & Felipe Magno(67994)
     * Handles information concerning the game's functions
     * Middle-man between Main and the Player objects
     * (and respective iterators)
     */
    // constants
    private static final int HIT = 100;
    private static final int BAD_HIT = -30;
    private static final int NONE = -1;


    public static final int ELIMINATED_PLAYER_CASE = 4;
    public static final int INVALID_COORS_CASE = 5;
    public static final int INVALID_PLAYER_CASE = 3;
    public static final int SELF_HIT_CASE = 2;
    public static final int VALID = 1;


    // instance variables
    private int playerCount;
    private int playerTurnIdx;
    private int winnerIdx;
    private Player[] players;

    /**
     * Constructor
     * @param numOfPlayers the number of participating players (int)
     * @pre: numOfPlayers > 1
     */
    public Battleships(int numOfPlayers) {
        this.players = new Player[numOfPlayers];
        winnerIdx = NONE;
        playerCount = 0;
    }
    // methods

    /**
     * Adds a valid player to the players array to participate in the game
     * @param playerName the unique name attribute of the new Player object    (String)
     * @param layout a 2-dimensional array containing the player base fleet  (String[])
     * @pre: !playerName.equals(null) && layout != null
     */
    public void addPlayer(String playerName, String[] layout) {
        players[playerCount++] = new Player(playerName, layout);
    }
    /**
     * Returns FleetIterator of the Player object with name equal to playerName
     * @param playerName name of player to get fleet (String)
     * @pre:  hasPlayer(playerName)
     * @return an object to iterate over the lines of the player's fleet
     */
    public FleetIterator getFleet(String playerName) {
        return getPlayer(playerName).getFleet();
    }
    /**
     * Returns PlayerIterator Object of players to be iterated over
     * @param sorted whether output is expected to be sorted (boolean)
     * @return an Object to iterate over player names and scores
     */
    public PlayerIterator getPlayerIterator(boolean sorted) {
        return new PlayerIterator(players, sorted);
    }

    /**
     * Returns the name of the next player to attack
     * @pre:  getNextPlayer() != null && !playerName.equals(null);
     * @return the current turn player's name,
     */
    public String getNextPlayerName() {
        return getNextPlayer().getName();
    }
    /**
     * Returns score of the Player object with name equal to playerName
     * @param playerName name of player to retrieve score value (String)
     * @pre:  hasPlayer(playerName)
     * @return the current score of the retrieved player
     */
    public int getScore(String playerName) {
        return getPlayer(playerName).getScore();
    }
    /**
     * Checks for existence of Player Object in players
     * with name variable of value equal to playerName
     * @param playerName name of the player being searched for (String)
     * @pre:  !playerName.equals(null)
     * @return boolean value, represents whether there exists a player
     * containing a name value equal to the one given
     */
    public boolean hasPlayer(String playerName) {
        boolean found = false;
        int i = 0;
        int size = players.length;
        while (!found && i < size) {
            found = (playerName.equals(players[i++].getName()));
        }
        return found;
    }

    /**
     * Returns whether there is a winner
     * @pre: winnerIdx == NONE ||
     * (winnerIdx < players.length-1 && winnerIdx > NONE)
     * @return boolean value representing if the game has ended
     */
    public boolean hasWinner() {
        return winnerIdx != NONE;
    }
    /**
     * Returns the winning Player's name
     * @pre: hasWinner()
     * @return String name of winner player
     */
    public String getWinnerName() {
        return players[winnerIdx].getName();
    }
    /**
     * Main game action
     * Opposing Player's fleet is shot at
     * Change in points and target's fleet are carried out
     * @param rowPos the target row position of the shot  (int)
     * @param colPos the target col position of the shot  (int)
     * @param targetName name of the player being shot (String)
     * @pre: shotIsValid(rowPos, colPos, targetName)
     */
    public void shoot(int rowPos, int colPos, String targetName) {
        int rowIndex = rowPos-1;
        int colIndex = colPos-1;
        Player attacker = getNextPlayer();
        Player opponent = getPlayer(targetName);
        boolean isSunk = opponent.isShipSunk(rowIndex, colIndex);
        int shipSize = opponent.checkShot(rowIndex, colIndex);

        //Apply score to the attacking player
        //Assumes that by hitting water, score change is always 0
        if (isSunk) {
            attacker.addScore(shipSize * BAD_HIT);
        } else {
            attacker.addScore(shipSize * HIT);
        }
        int pastTurnIdx = playerTurnIdx;
        changeTurn();
        updateWinStatus(pastTurnIdx);
    }
    /**
     * Checks if shot is possible given specific parameters
     * @param rowPos the target row position of the shot (int)
     * @param colPos the target col position of the shot (int)
     * @param targetName name of the player targeted  (String)
     * @pre:  rowPos > 0 && colPos > 0
     *        && !targetName.equals(null)
     * @return int value referring to a particular scenario
     */
    public int shotIsValid(int rowPos, int colPos, String targetName) {
        //assumes a valid shot
        int scenario = VALID;

        if (!hasPlayer(targetName)) {
            //nonexistent player
            scenario = INVALID_PLAYER_CASE;
        } else {
            //player exists
            Player target = getPlayer(targetName);
            if (getNextPlayer().getName().equals(target.getName())) {
                //self-inflicted shot
                scenario = SELF_HIT_CASE;
            } else if (target.isInactive()) {
                //eliminated player
                scenario = ELIMINATED_PLAYER_CASE;
            } else if (!getPlayer(targetName).isCoordinateInGrid(rowPos, colPos)) {
                //position targeted outside grid bounds
                scenario = INVALID_COORS_CASE;
            }
        }
        return scenario;
    }

    //------------private methods
    /**
     * Changes the Player to carry out the next action
     */
    private void changeTurn() {
        int numPlayers = players.length;
        playerTurnIdx = (playerTurnIdx + 1) % numPlayers;
        while (players[playerTurnIdx].isInactive()) {
            playerTurnIdx = (playerTurnIdx + 1) % numPlayers;
        }
    }
    /**
     * Returns the current turn's player
     * @return the Player object next to engage in the shoot phase
     */
    private Player getNextPlayer() {
        return players[playerTurnIdx];
    }

    /**
     * Compares name value of the Player objects within BattleShips object
     * to find a match for the parameter value
     * @param playerName the name of the player to be retrieved (String)
     * @pre:  hasPlayer(playerName)
     * @return the Player object for a given name
     */
    private Player getPlayer(String playerName) {
        int idx = 0;
        while(idx < players.length && !playerName.equals(players[idx].getName())){
            idx++;
        }
        return players[idx];
    }

    /**
     * Checks for a winner each turn and modifies the object's winnerIdx value
     * @param pastTurnIdx the index of the previous turn's attacking player
     */
    private void updateWinStatus(int pastTurnIdx) {
        if (playerTurnIdx == pastTurnIdx) { //if true, game is over
            applyBonus();
            //retrieves player with highest score
            int highestScore = players[0].getScore();
            int highScoreIdx = 0;
            int numHsPlayers = 1; //Hs -> High-score
            for (int i = 1; i < players.length; i++) {
                int playerScore = players[i].getScore();
                if (playerScore > highestScore) {
                    highestScore = playerScore;
                    highScoreIdx = i;
                    numHsPlayers = 1;
                } else if (playerScore == highestScore) {
                    numHsPlayers++;
                }
            }
            if(numHsPlayers > 1) {
                winnerIdx = playerTurnIdx;
            } else {
                winnerIdx = highScoreIdx;
            }
        }
    }
    /**
     * Bonus - doubles score of surviving player before calculating winner
     */
    private void applyBonus(){
        Player survivor = players[playerTurnIdx];
        survivor.addScore(survivor.getScore());
    }
}
