public class WordFrequency {

    private final String word;
    private int frequency;

    public WordFrequency(String word) {
        this.word = word;
        frequency = 1;
    }

    public void increment(){
        frequency++;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    public int compareTo(WordFrequency other){

        int result = other.getFrequency() - frequency;

        if(result == 0) {
            result = word.compareTo(other.getWord());
        }

        return result;
    }
}
