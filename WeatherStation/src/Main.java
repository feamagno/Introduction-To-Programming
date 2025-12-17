
/**
 * chupa picas e lambe paus
 * @author gayzao
 */

public class Main {

    public static void main(String[] args) {

        WeatherStation ws = new WeatherStation();

        ws.addSample(7);
        ws.addSample(6);
        ws.addSample(1134);
        ws.addSample(4);
        ws.addSample(13);
        ws.addSample(58);


    }

    private static void print(WeatherStation ws){
        for (int i = 1; i <=ws.getNumberOfSamples(); i++) {
            System.out.println(ws.getSample(i));
        }
    }

}
