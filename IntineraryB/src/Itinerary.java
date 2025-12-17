public class Itinerary {

    // setting up variables
    private final int departure2;
    private final int departure3;
    private final int duration1;
    private final int duration2;
    private final int duration3;
    private final int exam;

    // constructor with 6 values to be inputted
    public Itinerary(int dep2,int dep3,int dur1,int dur2,int dur3,int ex){
        departure2 = dep2;
        departure3 = dep3;
        duration1 = dur1;
        duration2 = dur2;
        duration3 = dur3;
        exam = ex;
    }

    // checks if it is possible to get to each
    // station before the train leaves
    public boolean isPossible() {
        return ((departure2 >= duration1) && (departure3 >= duration2 + departure2));
    }

    // checks if it is possible to get to NOVA
    // before the exam has started
    public boolean isUseful(){
        return (departure3 + duration3 <= exam);
    }

}

