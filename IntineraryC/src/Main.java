import java.util.Scanner;

public class Main {
	
	public static void main(String [] args) {
		
		Scanner in = new Scanner(System.in);

		int hDep1 = in.nextInt();
		int mDep1 = in.nextInt();
		
		int hDep2 = in.nextInt();
		int mDep2 = in.nextInt();

		int hDep3 = in.nextInt();
		int mDep3 = in.nextInt();
		
		int dur1 = in.nextInt();
		int dur2 = in.nextInt();
		int dur3 = in.nextInt();
		
		int hExam = in.nextInt();
		int mExam = in.nextInt();
				
		Itinerary it = new Itinerary(hDep1, mDep1, hDep2, mDep2, hDep3, mDep3, dur1, dur2, dur3, hExam, mExam);

		if (it.isPossible()) {
			System.out.println("The itinerary is possible.");
			
			if (it.isUseful())
				System.out.println("The itinerary is useful.");
			else
				System.out.println("The itinerary is not useful.");
		}
		else
			System.out.println("The itinerary is not possible.");	
		
		in.close();
	}
}
