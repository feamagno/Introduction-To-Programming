public class WordHistogram {
    private static final int GROWTH_FACTOR = 2;
    private static final int DEFAULT_SIZE = 10;
    private static final String SEPARATOR = " ";
    private WordFrequency[] frequencies;
    private int size;

    public WordHistogram() {
        frequencies = new WordFrequency[DEFAULT_SIZE];
        size = 0;
    }

    public void addLine(String line) {
        String[] words = line.split(SEPARATOR);

        for (int i = 0; i < words.length; i++) {
            String w = words[i].toLowerCase();
            countWord(w);
        }
    }

    private void countWord(String word) {
        int i = 0;

        while ((i < size) && !(frequencies[i].getWord().equals(word))) {
            i++;
        }
        if (i == size) {
            addWord(word);
        } else {
            frequencies[i].increment();
        }
    }

    private void addWord(String word) {
        if (isFull()) {
            grow();
        }
        frequencies[size] = new WordFrequency(word);
        size++;
    }

    private boolean isFull() {
        return frequencies.length <= size;
    }

    private void grow() {
        WordFrequency[] tmp = new WordFrequency[frequencies.length * GROWTH_FACTOR];

        for (int i = 0; i < size; i++) {
            tmp[i] = frequencies[i];
        }

        frequencies = tmp;
    }

    public FrequenciesIterator frequenciesIterator() {
        return new FrequenciesIterator(frequencies, size);
    }

    public FrequenciesIterator sortedFrequenciesIterator() {

        selectionSort();

        return new FrequenciesIterator(frequencies, size);
    }

    private void selectionSort() {

        for (int i = 0; i < size - 1; i++) {

            int idxOfMin = i;

            for (int j = i + 1; j < size; j++) {

                if (frequencies[j].compareTo(frequencies[idxOfMin]) < 0) {
                    idxOfMin = j;
                }
            }

            WordFrequency tmp = frequencies[i];
            frequencies[i] = frequencies[idxOfMin];
            frequencies[idxOfMin] = tmp;
        }
    }
}
