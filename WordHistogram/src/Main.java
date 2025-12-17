import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        WordHistogram hst = new WordHistogram();

        processInput(in, hst);

        in.close();

        Iterator it = hst.listFrequencies();

        processPrint(it);
    }

    private static void processInput(Scanner in, WordHistogram hst){
        int n = in.nextInt();
        in.nextLine();
        for(int i = 0; i < n; i++){
            hst.addLine(in.nextLine());
        }
    }

    private static void processPrint(Iterator it){
        while(it.hasNext()){
            WordFrequency ws = it.next();
            System.out.println(ws.getWord()+" "+ws.getFrequency());
        }
    }
}
