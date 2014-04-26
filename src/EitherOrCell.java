/**
 * An Either/Or Cell is a cell that contains two possible letters but only one is considered at a time
 * 
 * @author Dany
 */
public class EitherOrCell extends Cell {
	// The first possible letter
	private final char either;
	// The second possible letter
	private final char or;

	/**
	 * Construct an EitherOrCell by providing both possible letters
	 * 
	 * @param either The first possible letter
	 * @param or The second possible letter
	 */
	public EitherOrCell(char either, char or) {
		this.either = Character.toLowerCase(either);
		this.or = Character.toLowerCase(or);
	}

	/**
	 * An EitherOr cell concatenates to a string by concatenating each possible choice individually to the given string to form two possible strings
	 */
	@Override
	public String[] concatTo(String s) {
		return new String[] { s + either, s + or };
	}

	/**
	 * An EitherOr cell is converted to a string by returning both choices in uppercase separated by a forward slash
	 */
	@Override
	public String toString() {
		return Character.toUpperCase(either) + "/" + Character.toUpperCase(or);
	}

}
