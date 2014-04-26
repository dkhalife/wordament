/**
 * A BasicCell is a Cell that contains a single letter
 * 
 * @author Dany
 */
public final class BasicCell extends Cell {
	// The letter inside this cell
	private final char letter;

	/**
	 * Constructor for the Cell
	 * 
	 * @param letter The letter to attach to this cell
	 */
	public BasicCell(char letter) {
		this.letter = Character.toLowerCase(letter);
	}

	/**
	 * A BasicCell concatenates to a string by appending the letter it holds to that string
	 */
	@Override
	public String[] concatTo(String s) {
		return new String[] { s + letter };
	}

	/**
	 * A Basic Cell will be converted to a string containing the letter it holds in uppercase
	 */
	@Override
	public String toString() {
		return Character.toUpperCase(letter) + "";
	}
}
