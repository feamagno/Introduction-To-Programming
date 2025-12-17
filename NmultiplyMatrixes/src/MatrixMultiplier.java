public class MatrixMultiplier {

    private int[][] m;

    public MatrixMultiplier(int row, int col) {
        m = new int[row][col];
    }

    public int getValueMatrix(int row, int col) {
        return m[row][col];
    }

    public int getRows() {
        return m.length;
    }

    public int getCols() {
        return m[0].length;
    }

    public boolean canMultiply(MatrixMultiplier other) {
        return this.getCols() == other.getRows();
    }

    public void set(int value, int row, int col) {
        m[row][col] = value;
    }

    public MatrixMultiplier multiply(MatrixMultiplier other) {


        MatrixMultiplier tmp = new MatrixMultiplier(getRows(), other.getCols());

        for (int i = 0; i < getRows(); i++) {

            for (int j = 0; j < other.getCols(); j++) {

                int value = 0;

                for (int k = 0; k < getCols(); k++) {

                    value += m[i][k] * other.getValueMatrix(k,j);

                }

                tmp.set(value, i, j);
            }
        }
        return tmp;
    }
}
