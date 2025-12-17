public class Itinerary {

    // setting up variables
    private final int departure1;
    private final int  departure2;
    private final int  departure3;
    private final int  duration1;
    private final int  duration2;
    private final int duration3;
    private final int exam;

    // figures out time between two set times
    public int timeSkip(int h1,int m1,int h2,int m2){
        int h = h2;
        if (h2<h1){
            h = h + 24;
        } else if ((h2==h1)&&(m1>m2)) {
            h = h + 24;}
        return (((h -h1)*60) + (m2-m1));
    }

    // constructor with 6 values to be inputted
    public Itinerary(int hDep1,int mDep1,int hDep2,int mDep2,int hDep3,int mDep3,int dur1,int dur2,int dur3,int hExam,int mExam){
        departure1 = hDep1 + mDep1;
        int tS12 = timeSkip(hDep1, mDep1, hDep2, mDep2);
        int tS23 = timeSkip(hDep2, mDep2, hDep3, mDep3);
        int tS3E = timeSkip(hDep3, mDep3, hExam, mExam);
        departure2 = departure1 + tS12;
        departure3 = departure2 + tS23;
        duration1 = dur1;
        duration2 = dur2;
        duration3 = dur3;
        exam = departure3 + tS3E ;
    }

    // checks if it is possible to get to each
    // station before the train leaves
    public boolean isPossible() {
        return ((duration1+departure1 <= departure2) && (duration2+departure2 <= departure3));
    }

    // checks if it is possible to get to NOVA
    // before the exam has started
    public boolean isUseful(){
        return (departure3 + duration3 <= exam);
    }

}
