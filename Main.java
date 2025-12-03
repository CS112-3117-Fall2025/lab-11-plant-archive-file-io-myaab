// complete : Step 2 - Import file input statements here
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class Main 
{
	public static void main(String[] args)
	{
		//TEST FOR STEP 1 (you can delete/comment this out after completing step 1)
		Plant test = new Plant("Stinging Nettle,65,anti-inflammatory and culinary");
		System.out.println("test plant:\n" + test + "\n\n");


		//Complete: Step 2 - Declare + initialize variables for file input here
		FileInputStream fis = null;
		Scanner fileScanner = null;

		// Create the ArrayList to store Plant objects
		ArrayList<Plant> plantList = new ArrayList<>();

		//complete: Step 2 - Connect input stream to file (dont forget the try/catch!)
		try {
			fis = new FileInputStream("Forage.csv");
			fileScanner = new Scanner(fis);

			System.out.println("Reading data from Forage.csv...");

			//complete: Step 2 - create loop to read through whole file
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();

				if (line.isEmpty()) {
					continue;
				}

				//complete: Step 3 - build Plant Objects and store into ArrayList
				try {
					// Use the new CSV constructor from Plant.java
					Plant newPlant = new Plant(line);
					plantList.add(newPlant);
				} catch (IllegalArgumentException e) {
					// catches errors from the Plant constructor
					System.err.println("SKIPPING BAD DATA: " + e.getMessage());
				}

			}

		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Could not find Forage.csv file.");
			// print the stack trace
			e.printStackTrace();
		} finally {
			//complete: Step 2 - close the input stream
			if (fileScanner != null) {
				fileScanner.close();
			}
		}


		//complete: Step 3 - print contents of ArrayList
		System.out.println("\n--- Lab Summary: Foraging List ---");
		System.out.println("Total plants loaded: " + plantList.size() + "\n");

		for (Plant p : plantList) {
			System.out.println(p);
			System.out.println("--------------------");
		}
	}
}
