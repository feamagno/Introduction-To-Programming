public class PlayerIterator {
    /**
     * @author Diogo Antunes(67763) & Felipe Magno(67994)
     * An object that can be used to iterate over players
     */
    //instance variables
    private boolean sorted;
    private Player[] players;
    private int idx;
    private int size;

    /**
     * Constructor
     * @param players an array of Player Objects to iterate through (Player[])
     * @param sorted variable determining if array should be sorted  (boolean)
     * @pre: players != null
     */
    public PlayerIterator(Player[] players, boolean sorted) {
        this.size = players.length;
        idx = 0;
        if (sorted) {
            this.players = new Player[players.length];
            for (int i = 0; i < players.length; i++) {
                this.players[i] = players[i];
            }
            sortPlayers();
        } else {
            this.players = players;
            advance(); //set the idx to the first active player
        }
        this.sorted = sorted;

    }

    // methods

    /**
     * Returns the name of the Player in players at index: idx
     * Updates idx value to next suitable value
     * @pre: hasNext()
     * @return the name variable of the Player object
     */
    public String getNextName() {
        String playerName = players[idx].getName();
        if (!sorted) {
            idx++;
            advance();
        }
        return playerName;
    }

    /**
     * Returns the integer score of the Player in players at index: idx
     * Updates idx value to next suitable value
     * @pre: hasNext()
     * @return integer score of the Plauer Object
     */
    public int getNextScore() {
        return players[idx++].getScore();
    }

    /**
     * Checks if idx is within maximum index allowed by players
     * @pre: idx >= 0
     * @return true if idx is within dimensions of players
     */
    public boolean hasNext(){
        return idx < size;
    }

    //private methods

    /**
     * Finds the next instance of an active Player Object
     */
    private void advance(){
        while (idx < size && players[idx].isInactive()) {
            idx++;
        }
    }
    /**
     * Selection sort of the players Player Object array
     * by score and then name (descending)
     * @pre: players.length > 0
     */
    private void sortPlayers() {
        for (int i = 0; i < size-1; i++) {
            int idxOfMax = i;
            for (int j = i; j < size; j++) {
                if (players[j].greaterThan(players[idxOfMax]))
                    idxOfMax = j;
            }
            Player tmp = players[i];
            players[i] = players[idxOfMax];
            players[idxOfMax] = tmp;
        }
    }
}
