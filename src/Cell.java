import java.util.HashSet;

/**
 * This class is an abstract implemntation of a Wordament cell which basically is connected to other cells
 * 
 * @author Dany
 */
public abstract class Cell {
	// Is this cell marked (visited)
	private boolean marked = false;
	// The other cells that can be reached from this cell
	private HashSet<Cell> connections = new HashSet<>();

	/**
	 * Mark this cell as visited
	 */
	public void mark() {
		marked = true;
	}

	/**
	 * Unmark this cell as visited
	 */
	public void unmark() {
		marked = false;
	}

	/**
	 * Is this cell marked?
	 * 
	 * @return True if visited already
	 */
	public boolean isMarked() {
		return marked;
	}

	/**
	 * Connect this cell to another cell (bidirectionnally)
	 * 
	 * @param c The cell to connect to
	 */
	public void connect(Cell c) {
		if (this == c)
			return;

		connections.add(c);
		c.connections.add(this);
	}

	/**
	 * Get the list of neihbooring cells connected to this one
	 * 
	 * @return A set containing all connected cells
	 */
	public HashSet<Cell> getNeighboors() {
		return connections;
	}

	/**
	 * This abstract method defines the way a cell can be concatenated with a string to form a possible word
	 * 
	 * @param s The string to concatenate to
	 * @return A string representation of the the cell concatenated to the provided string
	 */
	public abstract String[] concatTo(String s);

	/**
	 * This abstract method defines the way a cell can be casted to a string (usually for visual display)
	 */
	public abstract String toString();
}