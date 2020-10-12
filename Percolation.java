public class Percolation {

	private final int n;
	private final WeightedQuickUnionUF grid;
	private final int size;
	private int openSites = 0;
	private boolean[] openArr;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int no) {
		if (no <= 0)
			throw new IllegalArgumentException("N less than one");

		n = no;
		size = n * n + 2;
		grid = new WeightedQuickUnionUF(size);
		openArr = new boolean[size];
		// System.out.println("inside cons, n=" + n + ", size=" + size);
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n)
			throw new IllegalArgumentException("Rows or Column out of bound");

		int pos = (row - 1) * n + col;
		if (openArr[pos])
			return;

		openArr[pos] = true;
		openSites++;

		// here we are trying to percolate by openung adjacent sides
		if (row == 1) {
			grid.union(0, pos);
			if (openArr[pos + n])
				grid.union(pos, pos + n);
			if (pos == 1) {
				if (openArr[pos + 1])
					grid.union(pos, pos + 1);
			} else if (pos == n) {
				if (openArr[pos - 1])
					grid.union(pos, pos - 1);
			} else {
				if (openArr[pos + 1])
					grid.union(pos, pos + 1);
				if (openArr[pos - 1])
					grid.union(pos, pos - 1);
			}
		} else if (row == n) {
			grid.union(n * n + 1, pos);
			if (openArr[pos - n])
				grid.union(pos, pos - n);
			if (pos == n * (n - 1) + 1) {
				if (openArr[pos + 1])
					grid.union(pos, pos + 1);
			} else if (pos == n * n) {
				if (openArr[pos - 1])
					grid.union(pos, pos - 1);
			} else {
				if (openArr[pos + 1])
					grid.union(pos, pos + 1);
				if (openArr[pos - 1])
					grid.union(pos, pos - 1);
			}
		} else if (col == 1) {
			if (openArr[pos - n])
				grid.union(pos, pos - n);
			if (openArr[pos + n])
				grid.union(pos, pos + n);
			if (openArr[pos + 1])
				grid.union(pos, pos + 1);
		} else if (col == n) {
			if (openArr[pos - n])
				grid.union(pos, pos - n);
			if (openArr[pos + n])
				grid.union(pos, pos + n);
			if (openArr[pos - 1])
				grid.union(pos, pos - 1);
		} else {
			if (openArr[pos - n])
				grid.union(pos, pos - n);
			if (openArr[pos + n])
				grid.union(pos, pos + n);
			if (openArr[pos - 1])
				grid.union(pos, pos - 1);
			if (openArr[pos + 1])
				grid.union(pos, pos + 1);
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n)
			throw new IllegalArgumentException("Rows or Column out of bound");

		int pos = (row - 1) * n + col;
		return openArr[pos];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n)
			throw new IllegalArgumentException("Rows or Column out of bound");

		int pos = (row - 1) * n + col;
		return grid.connected(0, pos);
	}

	// returns the number of open sites
	public int numberOfOpenSites() {

		return openSites;
	}

	// does the system percolate?
	public boolean percolates() {
		return grid.connected(0, size - 1);
	}

	// test client (optional)

	public static void main(String[] args) {
		// Percolation p= new Percolation(3);
		/*
		 * p.open(1,1); p.open(1,2); p.open(1,3); p.open(2,3); p.open(3,3);
		 */
		// for (int i=0; i<11; i++) System.out.println(p.openArr[i]);
		// System.out.println("in main: len openArr= "+ p.openArr.length + "\nn="+ p.n +
		// "\nsize="+ p.size);
	}

}
