public class GettingHotterBabe {

    private final int maxTemp;
    private final int[] consecutiveDays;

    public GettingHotterBabe(int averageTemperature, int[] consecutiveDays) {
        this.maxTemp = averageTemperature + 5;
        this.consecutiveDays = consecutiveDays;
    }


    //se der merda tentar colocar: consecutiveDays[day] >= this.maxTemp
    private int numOfHotDays(int day){

        int daysHot = 0;

        while(day < consecutiveDays.length && consecutiveDays[day] > this.maxTemp){
            daysHot++;
            day++;
        }

        if (daysHot == 0){
            daysHot = 1;
        }

        return daysHot;
    }

    public boolean doWeHaveAWave(){

        int daysHot = 0;

        boolean doWeHaveAWave = false;

        int day = 0;

        while (daysHot < 6 && day < consecutiveDays.length){
            daysHot = numOfHotDays(day);
            day += daysHot;
        }

        if (daysHot >= 6){
            doWeHaveAWave = true;
        }

        return doWeHaveAWave;
    }
}
