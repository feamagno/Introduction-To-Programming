import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) {
		
		Scanner in = new Scanner(System.in);
		
		int dep2 = in.nextInt();
		int dep3 = in.nextInt();
		
		int dur1 = in.nextInt();
		int dur2 = in.nextInt();
		int dur3 = in.nextInt();
		
		int exam = in.nextInt();

		in.close();

		Itinerary it = new Itinerary(dep2, dep3, dur1, dur2, dur3, exam);
		
		if (it.isPossible()) {
			System.out.println("The itinerary is possible.");
			
			if (it.isUseful())
				System.out.println("The itinerary is useful.");
			else
				System.out.println("The itinerary is not useful.");
		}
		else
			System.out.println("The itinerary is not possible.");
	}
}
