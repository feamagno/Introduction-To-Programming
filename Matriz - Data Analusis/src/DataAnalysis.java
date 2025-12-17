public class DataAnalysis {
    private int[][] data;
    private int row, cols;

    public DataAnalysis(int[][] data){
        this.data = data;
        row = data.length;
    }

    public int getMaximum(){
        int max = data[0][0];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < cols; j++){
                max = Math.max(max,data[i][j]);
            }
        }

        return max;
    }

    public boolean contains(int elem){
        boolean found = false;
        int i = 0;
        while(!found && i < row){
            int j = 0;
            while(!found && j < cols){
                found = (data[i][j] == elem);
                if(!found){
                    j++;
                }
            }
            i++;
        }
        return found;
    }

    public int countIncreasingRows(){
        int count = 0;
        for (int i = 0; i < row; i++){
            int j = 0;
            while(j < cols-1 && data[i][j+1] > data[i][j])
                j++;
            if (j == cols-1)
                count++;
            i++;
        }
        return count;
    }

    public int getColumnWithLargestTotal(){
        int maxIndex = 0;
        int maxTotal = Integer.MIN_VALUE;
        for (int j = 0; j < cols; j++){
            int total = 0;
            for (int i = 0; i < row; i++){
                total += data[i][j];
            }
            if (total > maxTotal){
                maxTotal = total;
                maxIndex = j;
            }
        }
        return maxIndex;
    }

    public DataAnalysis transpose(){
        int[][] tmp = new int[cols][row];
        for (int i = 0; i < cols; i++){
            for (int j = 0; j < row; j++){
                tmp[i][j] = data[j][i];
            }
        }
        return new DataAnalysis(tmp);
    }

    private int sum(int i, int j, int delta1, int delta2){
        int sum = 0;
        while (isInside(i, j)){
            sum += data[i][j];
            i += delta1;
            j += delta2;
        }
        return sum;
    }

    private boolean isInside(int i, int j){
        return 0 <= i && i < row && 0 <= j && j < cols;
    }



    //Pre: is a square
    public boolean isMagicSquare() {
        boolean magicSquare = true;

        //first diagonal
        int value = sum(0,0,1,1);

        //second diagonal
        magicSquare = sum(row,0, -1, 1) == value;

        //rows
        int i = 0;
        while (magicSquare && i < row && sum(i,0,0,1) == value){
            i++;
        }
        magicSquare = i == row;

        //column
        int j = 0;
        while (magicSquare && j < cols && sum(0,j,1,0) == value){
            j++;
        }
        magicSquare = j == cols;

        return magicSquare;
    }
}
