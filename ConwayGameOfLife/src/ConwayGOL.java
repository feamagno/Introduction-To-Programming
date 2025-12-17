public class ConwayGOL {

    private boolean[][] grid;

    public ConwayGOL(int rows, int cols) {
        grid = new boolean[rows+2][cols+2];
    }

    public void runGeneration(){

        boolean[][] tmp = new boolean[getNumRows()][getNumCols()];

        for (int i = 1; i < getNumRows()-2; i++){
            for (int j = 1; j < getNumCols()-2; j++){
                tmp[i][j] = setState(i,j);
            }
        }

        grid = tmp;
    }

    private boolean setState(int i, int j){

        boolean state = false;
        
        int alive = howManyAlive(i, j);

        if (grid[i][j] && (alive == 2 || alive == 3)){
            state = true;
        }

        else if (!grid[i][j] && alive == 3){
            state = true;
        }

        return state;
    }

    public int howManyAlive(int i, int j){
        int alive = 0;

        for (int a = -1; a < 2; a++){
            if (grid[i+a][j+1]){
                alive++;
            }
            if (grid[i+a][j-1]){
                alive++;
            }
            if (a != 0){
                if (grid[i+a][j]){
                    alive++;
                }
            }
        }
        return alive;
    }

    //pre: 1 <= row < getNumRows()-1
    public void addLine(String[] line, int row){
        for (int i = 0; i < getNumCols()-2; i++) {
            grid[row][i+1] = line[i].equals("#");
        }
    }

    public boolean[][] getState() {
        boolean [][] tmp = new boolean[getNumRows()-2][getNumCols()-2];
        for (int i = 1; i < getNumRows()-1; i++){
            for (int j = 1; j < getNumCols()-1; j++){
                tmp[i-1][j-1] = grid[i][j];
            }
        }
        return tmp;
    }

    public boolean[][] getGrid(){
        return grid;
    }

    public int getNumRows(){
        return grid.length;
    }

    public int getNumCols(){
        return grid[0].length;
    }

}
