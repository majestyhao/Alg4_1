/**
 * @author hao
 * 
 */

//import edu.princeton.cs.algs4.*;

public class Percolation {
  private int[][] grids;
  private WeightedQuickUnionUF uf;
  // private WeightedQuickUnionUF backwash;
  private int N;

  public Percolation(int N) {
    if (N <= 0)
      throw new java.lang.IllegalArgumentException();
    this.N = N;
    uf = new WeightedQuickUnionUF(N * N + 2); // for virtual source and virtual sink
    // create N-by-N grid, with all sites blocked
    grids = new int[N][];
    for (int i = 0; i < N; i++) {
      grids[i] = new int[N];
      for (int j = 0; j < N; j++) {
        grids[i][j] = 0;
      }
    }

    // backwash = new WeightedQuickUnionUF(N * N + 2);
    // connect virtual source with the first row sites
    // connect virtual sink with the last row sites
    // for (int i = 1; i <= N; i++) {
    // uf.union(0, i);
    // uf.union(N * N + 1, (N - 1) * N + i);
    // }
  }

  private int xyTo1D(int x, int y) {
    return (x - 1) * N + y;
  }

  private void connect(int x, int y) {
    int focus = xyTo1D(x, y);
    if (x == 1)
      uf.union(0, focus);
    if (x == N)
      uf.union(N * N + 1, focus);

    if (x > 1 && isOpen(x - 1, y)) {
      int up = xyTo1D(x - 1, y);
      uf.union(focus, up);
    }
    if (x < N && isOpen(x + 1, y)) {
      int down = xyTo1D(x + 1, y);
      uf.union(focus, down);
    }
    if (y > 1 && isOpen(x, y - 1)) {
      int left = xyTo1D(x, y - 1);
      uf.union(focus, left);
    }
    if (y < N && isOpen(x, y + 1)) {
      int right = xyTo1D(x, y + 1);
      uf.union(focus, right);
    }
  }

  private void validInput(int i, int j) {
    if (i < 1 || i > N || j < 1 || j > N) {
      throw new IndexOutOfBoundsException("index is not between 0 and " + N);
    }
  }

  public void open(int i, int j) {
    validInput(i, j);
    // open site (row i, column j) if it is not open already
    grids[i - 1][j - 1] = 1;
    // StdOut.println("done grids.");
    connect(i, j);
  }

  public boolean isOpen(int i, int j) {
    validInput(i, j);
    // is site (row i, column j) open?
    if (grids[i - 1][j - 1] == 1)
      return true;
    else
      return false;
  }

  public boolean isFull(int i, int j) {
    validInput(i, j);
    // is site (row i, column j) full?
    int focus = xyTo1D(i, j);
    if (uf.connected(0, focus))
      return true;
    else
      return false;
  }

  public boolean percolates() {
    // does the system percolate?
    // whether virtual source connect to virtual sink
    return uf.connected(0, N * N + 1);
    // for (int i = 0; i < N; i++) {
    // if (uf.connected(0, (N - 1) * N + i))
    // return true;
    // }
    // return false;
  }

  public static void main(String[] args) {
    int N = 2;
    Percolation perc = new Percolation(N);
    perc.open(1, 1);

    /*
     * // test client (optional) In in = new In(args[0]); // input file int N = in.readInt(); //
     * N-by-N percolation system
     * 
     * // repeatedly read in sites to open and draw resulting system Percolation perc = new
     * Percolation(N);
     * 
     * while (!StdIn.isEmpty()) { int x = StdIn.readInt(); int y = StdIn.readInt(); if
     * (!perc.isOpen(x, y)) { perc.open(x, y); StdOut.println(x + " " + y); } }
     */
  }

}
