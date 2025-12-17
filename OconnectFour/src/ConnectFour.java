public class ConnectFour {

    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final Character X = 'X';
    private static final Character O = 'O';
    private static final Character NONE = '.';
    private boolean xHasFour;
    private boolean oHasFour;

    private Character[][] connect;

    public ConnectFour(){
        connect = new Character[ROWS][COLS];
        xHasFour = false;
        oHasFour = false;
    }

    public void addLine(String line, int row){
        for (int j = 0; j < COLS; j++) {
            connect[row][j] = line.charAt(j);
        }
    }

    public Character whoWon() {

        hasFour();

        Character whoWon = ' ';

        if (!xHasFour && !oHasFour) {
            whoWon = NONE;
        }
        else if (xHasFour){
            whoWon = X;
        }
        else {
            whoWon = O;
        }

        return whoWon;
    }

    public boolean stillGoing(){
        boolean stillGoing = true;
        int i = 0;
        int j = 0;
        while (i < ROWS && stillGoing){
            while (j < COLS && stillGoing){
                stillGoing = connect[i][j].compareTo('.') != 0;
                j++;
            }
            j = 0;
            i++;
        }
        return stillGoing;
    }

    private void hasFour(){
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLS; j++){
                if (connect[i][j].compareTo('X') == 0) {
                    xHasFour = xHasFour || check(X, i, j);
                }
                else if (connect[i][j].compareTo('O') == 0){
                    oHasFour = oHasFour || check(O, i, j);
                }
            }
        }
    }

    private boolean check(Character c, int i, int j){

        boolean hasFour = false;

        for (int d1 = -1; d1 <= 1; d1++){
            for (int d2 = -1; d2 <= 1; d2++){
                hasFour = hasFour || checkDirection(c, i, j, d1, d2);
            }
        }

        return hasFour;
    }

    private boolean checkDirection(Character c, int i, int j, int d1, int d2){
        int num = 1;
        int a = 1;
        int i1 = d1 + i;
        int j1 = d2 + j;
            while (i1 < ROWS && i1 >= 0 && j1 < COLS && j1 >= 0 && !(d1 == 0 && d2 == 0) && a < 4){
                if (connect[i1][j1].compareTo(c) == 0) {
                    num++;
                }

                a++;
                i1 = d1*a + i;
                j1 = d2*a + j;

            }
        return num == 4;
    }
}
