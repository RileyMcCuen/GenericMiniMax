package utils.performance;

/**
 * 
 * This class is useful for printing out data to the terminal in a formatted
 * way.
 * 
 * @author Riley McCuen
 *
 */

public class PrintUtilities {

	/**
	 * Prints the performance data for a specific depth in easy to read string
	 * format.
	 * 
	 * @param depth
	 * @param average
	 * @param evaluatorString
	 */
	public static void printWithWords(int depth, long average, String evaluatorString) {
		System.out.println(
				"Depth: " + depth + ", Time: " + ((double) average / 100000000.0) + " seconds, " + evaluatorString);
	}

	/**
	 * Prints the performance data for a specific depth in CSV format.
	 * 
	 * @param depth
	 * @param average
	 * @param evaluatorCount
	 */
	public static void printCSV(int depth, long average, long evaluatorCount) {
		System.out.println(depth + ", " + ((double) average / 100000000.0) + ", " + evaluatorCount);
	}

}
