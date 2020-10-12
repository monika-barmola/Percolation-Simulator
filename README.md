
Program to estimate the value of the percolation threshold via Monte Carlo simulation.

We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

The problem:   In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates.When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid almost never percolates, and when p > p*, a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived. Your task is to write a computer program to estimate p*.

Monte Carlo simulation: To estimate the percolation threshold, consider the following computational experiment:

-Initialize all sites to be blocked.
-Repeat the following until the system percolates:
-Choose a site uniformly at random among all blocked sites.
-Open the site.
-The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.

API:

Percolation data type. To model a percolation system

              public class Percolation {

                  // creates n-by-n grid, with all sites initially blocked
                  public Percolation(int n)

                  // opens the site (row, col) if it is not open already
                  public void open(int row, int col)

                  // is the site (row, col) open?
                  public boolean isOpen(int row, int col)

                  // is the site (row, col) full?
                  public boolean isFull(int row, int col)

                  // returns the number of open sites
                  public int numberOfOpenSites()

                  // does the system percolate?
                  public boolean percolates()

                  // test client
                  public static void main(String[] args)
              }
              
              
To perform a series of computational experiments, a data type PercolationStats with the following API.

              public class PercolationStats {

                  // perform independent trials on an n-by-n grid
                  public PercolationStats(int n, int trials)

                  // sample mean of percolation threshold
                  public double mean()

                  // sample standard deviation of percolation threshold
                  public double stddev()

                  // low endpoint of 95% confidence interval
                  public double confidenceLo()

                  // high endpoint of 95% confidence interval
                  public double confidenceHi()

                 // test client
                 public static void main(String[] args)

