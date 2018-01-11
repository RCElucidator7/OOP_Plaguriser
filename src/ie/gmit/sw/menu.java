package ie.gmit.sw;
import java.util.Scanner;
/**
 * Menu class displays all nessessary values and reads in user input values
 * @author Ryan Conway
 *
 */
public class menu {

	private boolean keepRunning = true;
	private int choice = -1;
	
	private String file1;
	private String file2;
	
	private int shingle;
	private int blockq;
	
	private Scanner sc = new Scanner(System.in);
	
	public menu() {
		
	}
	
	public void show() {
		do {
			System.out.println("======= Main Menu =======\n1) Compare Documents\n2) Quit Application");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				//Call method
				fileCompare();
				break;
			
			case 2:
				keepRunning = false;
				System.out.println("Closing application!");
				break;
			}
			
		} while(keepRunning);
	}

	private void fileCompare() {
		//menu display for taking in two files, shingle size and blocking queue size
		System.out.println("======= Document Comparison Service =======");
		
		System.out.println("Enter file name 1: (Must end in .txt!)");
		file1 = sc.next();
		System.out.println("Enter file name 2: (Must end in .txt!)");
		file2 = sc.next();
		System.out.println("Enter size of Shingle: ");
		shingle = sc.nextInt();
		System.out.println("Enter size of Blocking queue: (I recommend a big number)");
		blockq = sc.nextInt();
		Launcher l = new Launcher();
		l.Launch(file1, file2, shingle, blockq);
		
	}
	
	public static void showResults(Comparer sim) {
		//menu to display results for comparisons
		float jacard = sim.calcJac();

		System.out.println("\n===== Plaguriser Checker Completed! =====");
		System.out.println("=========================================");
		System.out.println("File 1: " + sim.getsetA() + " shingles");
		System.out.println("File 2: " + sim.getsetB() + " shingles");
		System.out.println("\nNumber of Comparisons : " + sim.getIntersect());
		System.out.printf("\nPercentage: %.2f", jacard);
		System.out.println("\n\n");
	}
}