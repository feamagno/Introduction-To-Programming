public class FrequenciesIterator {

    private final WordFrequency[] frequencies;
    private final int size;
    private int next;

    public FrequenciesIterator(WordFrequency[] frequencies, int size) {
        this.frequencies = frequencies;
        this.size = size;
        next = 0;
    }

    public boolean hasNext(){
        return next < size;
    }

    public WordFrequency next(){
        return frequencies[next++];
    }

}
