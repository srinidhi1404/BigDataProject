package streamsProject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws CsvValidationException {
		String stresslevelStats = "StressLevelDataset.csv";
		List<StressLevelStats> stressLevelStatsList = loadCsvData(stresslevelStats);


		System.out.println("Goal 1: Calculate the average stress level");
	 
		long startTime1 = System.currentTimeMillis();
		double averageStressLevel = stressLevelStatsList.stream().mapToDouble(StressLevelStats::getStress_level)
				.average().orElse(0.0);
		long endTime1 = System.currentTimeMillis();
		long elapsedTime1 = endTime1 - startTime1;
		System.out.println("Elapsed Time: " + elapsedTime1 + " milliseconds");
		System.out.println("Average Stress Level: " + averageStressLevel);
		
		long startTime2 = System.currentTimeMillis();		
		double averageStressLevelP = stressLevelStatsList.parallelStream()
		        .mapToDouble(StressLevelStats::getStress_level)
		        .average()
		        .orElse(0.0);
		long endTime2 = System.currentTimeMillis();
		long elapsedTime2 = endTime2 - startTime2;
		System.out.println("\nElapsed Time using parallel streams: " + elapsedTime2 + " milliseconds");
		System.out.println("Average Stress Level: " + averageStressLevelP);
		
		System.out.println("\nGoal 2: Count the number of students with the highest anxiety");
		int highestAnxietyLevel = stressLevelStatsList.stream()
                .mapToInt(StressLevelStats::getAnxiety_level)
                .max()
                .orElse(0);
		long startTime3 = System.currentTimeMillis();
        long countHighestAnxiety = stressLevelStatsList.stream()
                .filter(student -> student.getAnxiety_level() == highestAnxietyLevel)
                .count();
		long endTime3 = System.currentTimeMillis();
		long elapsedTime3 = endTime3 - startTime3;
		System.out.println("Elapsed Time: " + elapsedTime3 + " milliseconds");
        System.out.println("Number of Students with the Highest Anxiety Level: " + countHighestAnxiety);
        
        long startTime4 = System.currentTimeMillis();
        long countHighestAnxietyP = stressLevelStatsList.parallelStream()
                .filter(student -> student.getAnxiety_level() == highestAnxietyLevel)
                .count();
		long endTime4 = System.currentTimeMillis();
		long elapsedTime4 = endTime4 - startTime4;
		System.out.println("\nElapsed Time using parallel streams: " + elapsedTime4 + " milliseconds");
        System.out.println("Number of Students with the Highest Anxiety Level: " + countHighestAnxietyP);
		


	}

	private static List<StressLevelStats> loadCsvData(String csvFilePath) throws CsvValidationException {
		List<StressLevelStats> dataEntries = new ArrayList<>();

		try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
			// Read the header and ignore it (assuming the first row is the header)
			reader.readNext();

			// Read the data rows
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				StressLevelStats entry = new StressLevelStats(Integer.parseInt(nextLine[0]),
						Integer.parseInt(nextLine[1]), Integer.parseInt(nextLine[2]), Integer.parseInt(nextLine[3]),
						Integer.parseInt(nextLine[4]), Integer.parseInt(nextLine[5]), Integer.parseInt(nextLine[6]),
						Integer.parseInt(nextLine[7]), Integer.parseInt(nextLine[8]), Integer.parseInt(nextLine[9]),
						Integer.parseInt(nextLine[10]), Integer.parseInt(nextLine[11]), Integer.parseInt(nextLine[12]),
						Integer.parseInt(nextLine[13]), Integer.parseInt(nextLine[14]), Integer.parseInt(nextLine[15]),
						Integer.parseInt(nextLine[16]), Integer.parseInt(nextLine[17]), Integer.parseInt(nextLine[18]),
						Integer.parseInt(nextLine[19]), Integer.parseInt(nextLine[20]));
				dataEntries.add(entry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataEntries;
	}

}