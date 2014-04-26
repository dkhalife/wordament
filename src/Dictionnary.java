import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * This class represents a dictionnary that contains a list of possible words in the specified locale
 * 
 * @author Dany
 */
public class Dictionnary {
	// The list of valid words
	private HashSet<String> words = new HashSet<>();

	/**
	 * To construct a dictionnary you need to specify the corresponding locale
	 * 
	 * @param locale The locale of the dictionnary which is also the name of the corresponding file without the extension
	 * @throws IOException
	 */
	public Dictionnary(String locale) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(locale + ".txt"));

		long startTime = System.nanoTime();

		String line;
		while ((line = br.readLine()) != null) {
			words.add(line);
		}
		br.close();

		long endTime = System.nanoTime();
		double executionTime = (endTime - startTime) / 1000000000.0;

		System.out.println("Loaded " + words.size() + " words in dictionnary in " + executionTime + " seconds.\n");
	}

	/**
	 * Determines wether a string is an english word according to the provided dictionnary
	 * 
	 * @param s The string to lookup
	 * @return True if the given string was found in the dictionnary
	 */
	public boolean isWord(String s) {
		return words.contains(s);
	}
}
