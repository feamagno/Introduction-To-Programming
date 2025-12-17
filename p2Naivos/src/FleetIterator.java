public class FleetIterator {
    /**
     * @author Diogo Antunes(67763) & Felipe Magno(67994)
     * An object that can be used to iterate over a fleet's rows
     */
    //constants
    private char[][] fleet;
    private int nextRow;

    /**
     * Constructor
     * @param fleet the matrix to be iterated over
     * @pre: fleet != null
     */
    public FleetIterator(char[][] fleet) {
        this.fleet = fleet;
        nextRow = 0;
    }

    // methods

    /**
     * Checks for the presence of another row
     * @pre: nextRow >= 0
     * @return true if there is a next row
     */
    public boolean hasNext(){
        return nextRow < fleet.length;
    }

    /**
     * Returns the next row of the fleet as a String
     * @pre: hasNext()
     * @return next row as a String
     */
    public String next(){
        return new String(fleet[nextRow++]);
    }
}