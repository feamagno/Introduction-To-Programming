public class COumida {

    private final String name;
    private final double price;
    private double weight;

    public COumida(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public void subtractWeight(double weight) {
        this.weight -= weight;
    }

    public String getName(){
        return name;
    }

    public double compareUtilityTo(COumida other){
        return other.getUtility() - this.getUtility();
    }

    public double getUtility(){
        return price/weight;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }
}
