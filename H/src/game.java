public class game {

    private static final int CARD_TWO_MULTIPLIER = 3;
    private static final int CARD_THREE_MULTIPLIER = 5;
    private static final int CARD_FOUR_MULTIPLIER = 10;
    private static final int MAX_CARD_NUMBER = 10;
    private int numOfWinners;
    private int maxPoints;


    public game() {
        maxPoints = 0;
        numOfWinners = 0;
    }

    public void addHand(int[] hand){
        int points = calcPoints(hand);

        if (points == maxPoints){

            numOfWinners++;

        } else if (points > maxPoints) {

            numOfWinners = 1;
            maxPoints = points;

        }
    }

    private int calcPoints(int[] hand){
        int points = 0;
        int[] hst = new int[MAX_CARD_NUMBER + 1];
        for(int i = 0; i < hand.length; i++ ){
            int card = hand[i];
            hst[card]++;
        }
        for (int i = 1; i < hst.length; i++){
            int card = i;
            int times = hst[card];
            switch (times){
                case 2:
                    points += card*CARD_TWO_MULTIPLIER;
                    break;
                case 3:
                    points += card*CARD_THREE_MULTIPLIER;
                    break;
                case 4:
                    points += card*CARD_FOUR_MULTIPLIER;
                    break;
                default:
                    points += card*times;
            }
        }
        return points;
    }

    public int getNumOfWinners() {
        return numOfWinners;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
