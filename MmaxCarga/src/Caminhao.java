public class Caminhao {

    private final COumida[] groceries;
    private COumida[] used;
    private int size;
    private int usedSize;
    private double maxWeight;

    public Caminhao(int size, double maxWeight) {
        this.size = 0;
        this.maxWeight = maxWeight;
        groceries = new COumida[size];
        used = new COumida[size];
    }

    public double maxPrice(){
        double price = 0;

        int i = 0;

        while (i < size && maxWeight > 0){

            double gWeight = groceries[i].getWeight();

            if (gWeight <= maxWeight){
                price += groceries[i].getPrice();
                maxWeight -= gWeight;
            }
            else {
                price += maxWeight * groceries[i].getUtility();
                maxWeight = 0;
            }
            i++;
        }
        for (int j = 0; j < i; j++){
            used[j] = groceries[j];
        }
        usedSize = i;
        return price;
    }

    public void addGrocerie(String name, double price, double weight){
        groceries[size++] = new COumida(name, price, weight);
    }

    public void sortByUtility(){
        for (int i = 0; i < size - 1; i++) {

            int idxOfMin = i;

            for (int j = i + 1; j < size; j++) {

                if (groceries[j].compareUtilityTo(groceries[idxOfMin]) < 0) {
                    idxOfMin = j;
                }
            }

            COumida tmp = groceries[i];
            groceries[i] = groceries[idxOfMin];
            groceries[idxOfMin] = tmp;
        }
    }

    public COumidaIterator COumidaIterator() {

        for (int i = 0; i < size - 1; i++) {

            int idxOfMin = i;

            for (int j = i + 1; j < usedSize; j++) {
                if (used[j].getName().compareTo(used[idxOfMin].getName()) < 0) {
                    idxOfMin = j;
                }
            }

            COumida tmp = used[i];
            used[i] = used[idxOfMin];
            used[idxOfMin] = tmp;
        }
        return new COumidaIterator(usedSize, used);
    }
}
