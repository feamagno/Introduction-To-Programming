public class Crop {

    private final String Name;
    private final int Max;
    private final int Min;

    private int Happy = 0;

    public String getName(){
        return this.Name;
    }
    public int getHappy(){
        return Happy;
    }

    public Crop(String Name, int Max, int Min){
        this.Name = Name;
        this.Max = Max;
        this.Min = Min;
    }

    public boolean registerDay(String day) {
        if (isHappy(day)) {
            Happy++;
            return true;
        } else {
            return false;
        }
    }

    public boolean isHappy(String day){

        int dayLength = day.length();
        int waterDrops = 0;

        for (int i = 0; i < dayLength; i++){
            if (day.charAt(i) == '1'){
                waterDrops++;
            }
        }
        return (waterDrops <= this.Max && waterDrops >= this.Min);
    }
}
