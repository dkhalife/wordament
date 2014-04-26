/**
 * A DiGram Cell is a cell that contains two letters
 * 
 * @author Dany
 */
public final class DigramCell extends Cell {
	// The letters contained in this cell
	private final char[] letters;

	/**
	 * Construct a digram cell by providing both letters it contains
	 * 
	 * @param letter1 The first letter
	 * @param letter2 The second letter
	 */
	public DigramCell(char letter1, char letter2) {
		letters = new char[] { Character.toLowerCase(letter1), Character.toLowerCase(letter2) };
	}

	/**
	 * A digram Cell concatentates to a string by adding both its characters to the provided string
	 */
	@Override
	public String[] concatTo(String s) {
		return new String[] { s + letters[0] + letters[1] };
	}

	/**
	 * A digram Cell is converted to a string by returning both its characters in uppercase
	 */
	@Override
	public String toString() {
		return new String(letters).toUpperCase();
	}

}
