public class PercolationStats {

	private final double conf = 1.96;
	private final int t, n;
	private int data[];

	// perform independent trials on an n-by-n grid
	public PercolationStats(int no, int trials) {
		if (no <= 0 || trials <= 0) {
			throw new IllegalArgumentException("N and T cannot be <= 0");
		}

		t = trials;
		n = no;
		data = new int[t];
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(data);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(data);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - (conf * stddev() / Math.sqrt(t));
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + (conf * stddev() / Math.sqrt(t));
	}

	private int experiment() {
		Percolation p = new Percolation(n);
		while (!p.percolates()) {
			int a = StdRandom.uniform(n) + 1;
			int b = StdRandom.uniform(n) + 1;

			if (p.isOpen(a, b))
				continue;

			p.open(a, b);
		}

		return p.numberOfOpenSites();
	}

	// test client (see below)

	public static void main(String[] args) {
		int a, b;
		a = Integer.parseInt(args[0]);
		b = Integer.parseInt(args[1]);
		PercolationStats p = new PercolationStats(a, b);

		for (int i = 0; i < b; i++) {
			p.data[i] = p.experiment();
		}
		System.out.println("mean = " + p.mean() + "\nstddev = " + p.stddev() + "95% confidence interval = ["
				+ p.confidenceLo() + ", " + p.confidenceHi() + "]");
	}

}
