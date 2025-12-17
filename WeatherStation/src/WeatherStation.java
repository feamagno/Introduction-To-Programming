public class WeatherStation {

    private static final int DEFAULT_CAPACITY = 1;
    private static final int GROWTH_FACTOR = 2;

    private int[] samples;

    //number of occupied entries
    private int size;

    public WeatherStation(){
        samples = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    public void selectionSort(){

        for (int i = 0; i < size-1; i++){
            int idxOfMin = i;
            for (int j = i+1; j < size; j++){
                if (samples[j] < samples[idxOfMin]){
                    idxOfMin = j;
                }
            }
            int tmp = samples[i];
            samples[i] = samples[idxOfMin];
            samples[idxOfMin] = tmp;
        }
    }

    public boolean binarySearch(int value){

        boolean found = false;

        int low = 0;
        int high = size - 1;

        while (!found && low <= high) {
            int mid = (low+high)/2;

            if (samples[mid] == value){
                found = true;
            }
            else if (samples[mid] > value){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        return found;
    }


    public boolean isFull(){
        //checks if samples array is full
        return size == samples.length;
    }

    public void grow(){
        //grows samples array by 1
        int[] array;
        array = new int[GROWTH_FACTOR * samples.length];

        for (int i = 0; i < samples.length; i++){
            array[i] = samples[i];
        }
        samples = array;
    }

    public void removeFrom(int pos){
        //erases a sample and passes all the other 1 to the left
        for (int i = pos-1 ; pos < size-1 ; pos++){
            samples[i] = samples[i+1];
        }
        size--;
    }

    public void addAt(int pos, int temp){

        if (isFull()){
            grow();
        }

        int oldSample;

        for (int i = size; i > pos-1; i--){

            oldSample = samples[i-1];
            samples[i] = oldSample;

        }

        samples[pos-1] = temp;
        size++;

    }

    public void addSample(int temp){
        //adds temperature reading temp to samples

        if (isFull()){
            grow();
        }
        samples[size] = temp;
        size++;
    }

    public int getSample(int i){
        //returns i-th sample
        //Pre: i > 0

        return samples[i-1];
    }

    public int getNumberOfSamples(){
        //returns number of stored samples
        return size;
    }

    public int getAverage(){
        //returns average of samples

        int sum = 0;

        for(int i = 0; i < size; i++){
            sum += samples[i];
        }

        return sum/size;
    }

    public int getMinimum(){
        //returns minimum temperature
        //getNumberOfSamples() > 0

        int temp = samples[0];

        for(int i = 0; i < size; i++){
            temp = Math.min(temp,samples[i]);
        }
        return temp;
    }
    public int getMaximum(){
        //returns minimum temperature
        //getNumberOfSamples() > 0

        int temp = samples[0];

        for(int i = 0; i < size; i++){
            temp = Math.max(temp, samples[i]);
        }
        return temp;
    }

    public int firstOccurrenceOfSample(int temp){
        //return place where we get the first number person is asking
        //pre getNumberOfSamples() > 0
        int order = 0;
        int i =0;

        while(i < size){
            if (temp == samples[i]){
                i++;
                order = i;
                break;
            }
            i++;
        }

        return order;
    }

    public int lastOccurrenceOfSample(int temp){
        //return place where we get the first number person is asking
        //pre getNumberOfSamples() > 0
        int order = 0;
        int i = size;

        while(i >= 0){
            if (temp == samples[i]){
                i++;
                order = i;
                break;
            }
            i--;
        }

        return order;
    }

    public int countSamplesGreaterThan(int temp){

        int count = 0;

        for(int i = 0; i < size; i++){
            if (samples[i] > temp){
                count++;
            }
        }
        return count;
    }

    public int countSamplesLowerThan(int temp){

        int count = 0;

        for(int i = 0; i < size; i++){
            if (samples[i] < temp){
                count++;
            }
        }
        return count;
    }

    public int percentageOfNegatives(){

        if (size > 1) {
            int negatives = this.countSamplesLowerThan(0);
            return (negatives / size) * 100;
        }
        else if (samples[0]<0){
            return 100;
        }
        else return 0;
    }
}
