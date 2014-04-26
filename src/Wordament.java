import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Wordament {
	private Cell[][] grid;

	private ArrayList<Set<String>> words;
	private int minLength = 3;
	private int totalResults = 0;
	private int totalCombinations = 0;
	private Dictionnary dictionnary;

	public Wordament(String[] args) throws IOException {
		// Create the grid according to the letters provided
		createGrid(args);
		// Connect all cells in the possible directions
		connectCells();

		// Create a set to store words of each size (according to cell count, not length)
		words = new ArrayList<Set<String>>();
		for (int i = minLength; i <= args.length; ++i) {
			words.add(new TreeSet<String>(new PathComparator()));
		}

		dictionnary = new Dictionnary("en-US");
	}

	/**
	 * @param args
	 * @param GRID_SIZE
	 */
	private void createGrid(String[] args) {
		int GRID_SIZE = (int) Math.sqrt(args.length);

		this.grid = new Cell[GRID_SIZE][GRID_SIZE];

		for (int i = 0; i < GRID_SIZE; ++i) {
			for (int j = 0; j < GRID_SIZE; ++j) {
				// Type check for either/or and BOTH
				String param = args[i * GRID_SIZE + j];
				if (param.length() == 1)
					this.grid[i][j] = new BasicCell(param.charAt(0));
				else if (param.indexOf("/") == -1)
					this.grid[i][j] = new DigramCell(param.charAt(0), param.charAt(1));
				else if (param.length() == 3 && param.indexOf("/") == 1)
					this.grid[i][j] = new EitherOrCell(param.charAt(0), param.charAt(2));
			}
		}
	}

	/**
	 * @param GRID_SIZE
	 */
	private void connectCells() {
		// In order to achieve full coverage, all cells must be connected to their top, top-left, left and bottom-left siblings
		for (int i = 0; i < grid.length; ++i) {
			// Do we have a cell to the top
			boolean top = i > 0;
			// Do we have a cell to the bottom
			boolean bottom = i < grid.length - 1;

			for (int j = 0; j < grid.length; ++j) {
				// Do we have a cell to the left
				boolean left = j > 0;

				if (top) {
					this.grid[i][j].connect(this.grid[i - 1][j]);

					if (left) {
						this.grid[i][j].connect(this.grid[i - 1][j - 1]);
					}
				}

				if (left) {
					this.grid[i][j].connect(this.grid[i][j - 1]);

					if (bottom) {
						this.grid[i][j].connect(this.grid[i + 1][j - 1]);
					}
				}
			}
		}
	}

	public void solve() {
		long startTime = System.nanoTime();

		totalCombinations = 0;

		// Start from every possible cell
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				solve(grid[i][j], "", 0);
			}
		}

		long endTime = System.nanoTime();
		double executionTime = (endTime - startTime) / 1000000000.0;

		totalResults = 0;
		for (int i = 0; i < words.size(); ++i) {
			totalResults += words.get(i).size();
		}

		System.out.println("Filtered " + totalResults + "/" + totalCombinations + " words in " + executionTime + " seconds.");
		System.out.println("Avg speed: " + Math.round(totalCombinations / executionTime) + " words processed per second.\n");
	}

	private void solve(Cell c, String previousWord, int cellCount) {
		String[] currentWords = c.concatTo(previousWord);
		++cellCount;
		++totalCombinations;

		c.mark();

		for (String word : currentWords) {
			// Once word length >= 3
			// Check english word, if yes add
			if (cellCount >= minLength && dictionnary.isWord(word)) {
				words.get(cellCount - minLength).add(word);
				// Continue once a word is matched with a set of cells because even if others matched it's still considered as a single sequence
				continue;
			}

			// Perform DFS
			for (Cell n : c.getNeighboors()) {
				if (!n.isMarked()) {
					// And try for unmarked neighboors
					solve(n, word, cellCount);
				}
			}
		}

		c.unmark();
	}

	private void print() {
		System.out.println("GRID:");
		for (Cell[] row : grid) {
			for (Cell c : row) {
				System.out.print(c + "\t");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("Words (" + totalResults + "):");
		for (int i = words.size() - 1; i >= 0; --i) {
			int total = words.get(i).size();
			if (total > 0) {
				System.out.println((i + minLength) + " cells (" + total + "):");
				for (String s : words.get(i)) {
					System.out.println(s);
				}
				System.out.println();
			}
		}
	}

	public static void main(String args[]) throws IOException {
		Wordament w = new Wordament(args);
		w.solve();
		w.print();
	}
}
