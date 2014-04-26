import java.util.ArrayList;

public class Path implements Comparable<Path> {
	private ArrayList<Cell> cells;

	private boolean eitherOr = false;
	private boolean digram = false;

	public Path(){
		 cells = new ArrayList<>();
	}
	
	@SuppressWarnings("unchecked")
	public Path(Path s){
		cells = (ArrayList<Cell>) (s.cells.clone());
		eitherOr = s.eitherOr;
		digram = s.digram;
	}
	
	public void append(Cell c) {
		cells.add(c);

		if (c instanceof EitherOrCell) {
			eitherOr = true;
		} else if (c instanceof DigramCell) {
			digram = true;
		}
	}

	public String toString() {
		String s = "";

		for (Cell c : cells) {
			s += c;
		}

		return s;
	}

	public int length() {
		return cells.size();
	}

	@Override
	public int compareTo(Path r) {
		// Sort strings ascending according to length
		if (length() < r.length()) {
			return -1;
		} else if (length() > r.length()) {
			return 1;
		}
		// And then alphabeticallt
		else {
			return toString().compareTo(r.toString());
		}
	}

	public boolean isEitherOr() {
		return eitherOr;
	}

	public boolean isDigram() {
		return digram;
	}
}
