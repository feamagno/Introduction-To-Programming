public class COumidaIterator {

    private final int size;
    private final COumida[] groceries;
    private int next;

    public COumidaIterator(int size, COumida[] groceries) {
        this.groceries = groceries;
        this.next = 0;
        this.size = size;
    }

    public boolean hasNext(){
        return next < size;
    }

    public COumida next(){
        return groceries[next++];
    }
}
