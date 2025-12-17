public class WaterSystem {

    private final Crop c1, c2;
    int happyBothDays = 0;

    public WaterSystem(Crop c1, Crop c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public int getHappyBothDays(){
        return happyBothDays;
    }

    public boolean areBothHappy(String day){
        //returns true if both are happy
        boolean x, y;
        x = this.c1.registerDay(day);
        y = this.c2.registerDay(day);
        return (x&&y);
    }

    public void registerDay(String day) {
        //checks if both are happy and adds 1 to variable if they are
        if (areBothHappy(day)) {
            happyBothDays++;
        }
    }
}
