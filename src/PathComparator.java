import java.util.Comparator;

/**
 * Thic class compares String by length before comparing them alphabetically
 * 
 * @author Dany
 */
public class PathComparator implements Comparator<String> {

	@Override
	public int compare(String l, String r) {
		// Sort strings ascending according to length
		if (l.length() < r.length()) {
			return -1;
		}
		else if (l.length() > r.length()) {
			return 1;
		}
		// And then alphabeticallt
		else {
			return l.compareTo(r);
		}
	}

}
