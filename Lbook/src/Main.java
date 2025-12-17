import java.io.*;
import java.util.Scanner;

public class
Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);

        WordHistogram hst = new WordHistogram();

        String input = processInput(in);

        processBookReading(input, hst);

        processPrint(hst);

        processSortedPrint(hst);

        in.close();
    }

    private static String processInput(Scanner in){
        return in.nextLine().trim();
    }

    private static void processBookReading(String input, WordHistogram hst) throws FileNotFoundException{

        FileReader reader = new FileReader(input);

        Scanner in = new Scanner(reader);

        String line;

        while(in.hasNextLine()){
            line = in.nextLine();
            hst.addLine(line);
        }

        in.close();
    }

    private static void processPrint(WordHistogram hst){

        FrequenciesIterator it = hst.frequenciesIterator();

        while(it.hasNext()){
            WordFrequency ws = it.next();
            System.out.println(ws.getWord()+" "+ws.getFrequency());
        }
    }

    private static void processSortedPrint(WordHistogram hst){

        FrequenciesIterator it = hst.sortedFrequenciesIterator();

        System.out.println();

        while(it.hasNext()){
            WordFrequency ws = it.next();
            System.out.println(ws.getWord()+" "+ws.getFrequency());
        }
    }

}
