/**
 * 
 */

/**
 * @author hao
 *
 */
//import edu.princeton.cs.algs4.*;

public class PercolationStats {
  //private Percolation perc;
  private int N, T;
  private double[] prob;
  
  public PercolationStats(int N, int T) {
    if (N <= 0 || T <= 0)
      throw new java.lang.IllegalArgumentException();
    this.N = N;
    this.T = T;
    // perform T independent experiments on an N-by-N grid
    prob = new double[T];
    
    for (int i = 0; i < T; i++) {
      Percolation perc = new Percolation(N);
      int count = 0;
      while (!perc.percolates()) {
        int x = StdRandom.uniform(N) + 1;
        int y = StdRandom.uniform(N) + 1;
        if (!perc.isOpen(x, y)) {
          perc.open(x, y);
          count++;
        }
      }
      prob[i] = (double) count / (N * N);
    }
  }
  
  public double mean() {       
    // sample mean of percolation threshold
    /*
    double sum = 0;
    for (int i = 0; i < T; i++)
      sum += prob[i];
    return sum / T;
    */
    return StdStats.mean(prob);
  }
  
  public double stddev() {
    // sample standard deviation of percolation threshold
    /*
    double avg = mean();
    double sum = 0;
    for (int i = 0; i < T; i++) {
      sum += (prob[i] - avg) * (prob[i] - avg);
    }
    return sum / (T - 1);
    */
    return StdStats.stddev(prob);
  }
  
  public double confidenceLo() {
    // low  endpoint of 95% confidence interval
    double sigma = stddev();
    double mu = mean();
    return (mu - 1.96 * sigma / Math.sqrt(T));
  }
  
  public double confidenceHi() {
    // high endpoint of 95% confidence interval
    double sigma = stddev();
    double mu = mean();
    return (mu + 1.96 * sigma / Math.sqrt(T));
  }

  public static void main(String[] args) {
    // test client
    int N = Integer.parseInt(args[0]); // input N
    int T = Integer.parseInt(args[1]); // input T
    PercolationStats percStat = new PercolationStats(N, T);
    StdOut.println("mean                    = " + percStat.mean());
    StdOut.println("stddev                  = " + percStat.stddev());
    StdOut.println("95% confidence interval = " + percStat.confidenceLo() + ", " + percStat.confidenceHi());
  }
}
