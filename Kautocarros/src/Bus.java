public class Bus {

    private final int[] passengers;
    private final int maxWaitTime;
    private int numOfTrips;


    public Bus(int maxWaitTime, int[] passengers) {
        this.passengers = passengers;
        this.maxWaitTime = maxWaitTime;
        numOfTrips = 0;
        sortPassengers();
        setNumOfTrips();
    }



    private void sortPassengers(){
        for (int i = 0; i < passengers.length-1; i++){
            int idxOfMin = i;
            for (int j = i+1; j < passengers.length; j++){
                if (passengers[j] < passengers[idxOfMin]){
                    idxOfMin = j;
                }
            }
            int tmp = passengers[i];
            passengers[i] = passengers[idxOfMin];
            passengers[idxOfMin] = tmp;
        }
    }

    public int getNumOfTrips() {
        return numOfTrips;
    }

    private void setNumOfTrips(){
        int i = 0;

        while(i < passengers.length){
            i = checkNumOfTrips(i);
        }
    }

    private int checkNumOfTrips(int i){

        int maxTime = passengers[i] + maxWaitTime;

        while (i < passengers.length && maxTime >= passengers[i]){
            i++;
        }

        numOfTrips++;

        return i;
    }
}
