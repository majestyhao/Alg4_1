/**
 * 
 */

/**
 * @author hao
 * 
 */
import edu.princeton.cs.algs4.*;

public class Board {
  private int N;
  private int[] blocks;
  private int[] spot;
  
  private int XY21D(int i, int j) {
    return i * N + j;
  }
  
  public Board(int[][] blocks) {
    if (blocks == null) {
      throw new java.lang.NullPointerException();
    }
    // construct a board from an N-by -N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    N = blocks.length;
    this.blocks = new int[N * N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int index = XY21D(i, j);
        this.blocks[index] = blocks[i][j];
        if (blocks[i][j] == 0) {
          //spot = XY21D(i, j);
          spot = new int[2];
          spot[0] = i;
          spot[1] = j;
        }
      }
    }
  }

  public int dimension() {
    // board dimension N
    return N;
  }

  public int hamming() {
    int count = 0;
    // number of blocks out of place
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int index = XY21D(i, j);
        if (blocks[index] == 0)
          continue;
        if (blocks[index] != i * N + j + 1) {
          count++;
        }
      }
    }
    return count;
  }

  public int manhattan() {
    // sum of Manhattan distances between blocks and goal
    int sum = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int index = XY21D(i, j);
        if (blocks[index] == 0)
          continue;
        int vert = (blocks[index] - 1) / N;
        int hori = (blocks[index] - 1) % N;
//        StdOut.print(i);
//        StdOut.print(vert);
//        StdOut.print(j);
//        StdOut.println(hori);
        if (vert < i) {
          vert = i - vert;
        } else {
          vert = vert - i;
        }
        if (hori < j) {
          hori = j - hori;
        } else {
          hori = hori - j;
        }
        sum += vert + hori;
      }
    }
    return sum;
  }

  public boolean isGoal() {
    // is this board the goal board?
    if (hamming() == 0) {
      return true;
    } else {
      return false;
    }
  }

  public Board twin() {
    // a board that is obtained by exchanging any pair of blocks
    //int i = StdRandom.uniform(N);
    //int j = StdRandom.uniform(N);
    if (spot[0] == 0) {
      return swap(N - 1, 0, N - 1, 1);
    }
      return swap(0, 0, 0, 1);
//    int tmp = blocks[i][j];
//    blocks[i][j] = blocks[j][i];
//    blocks[j][i] = tmp;
  }

  public boolean equals(Object y) {
    // does this board equal y?
    if (y == this)
      return true;
    if (y == null)
      return false;
    if (y.getClass() != this.getClass())
      return false;
    Board that = (Board) y;
    if (this.N != that.N)
      return false;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int index = XY21D(i, j);
        if (this.blocks[index] != that.blocks[index]) {
          return false;
        }
      }
    }
    return true;
  }

  private Board swap(int ai, int aj, int bi, int bj) {
    int[][] blocks = new int[N][];
    for (int i = 0; i < N; i++) {
      blocks[i] = new int[N];
      for (int j = 0; j < N; j++) {
        int index = XY21D(i, j);
        blocks[i][j] = this.blocks[index];
      }
    }
    int tmp = blocks[ai][aj];
    blocks[ai][aj] = blocks[bi][bj];
    blocks[bi][bj] = tmp;
    Board result = new Board(blocks);
    return result;
  }

  public Iterable<Board> neighbors() {
    // all neighboring boards
    Stack<Board> stack = new Stack<Board>();
    if (spot[0] != 0) {
      stack.push(swap(spot[0], spot[1], spot[0] - 1, spot[1]));
    }
    if (spot[0] != N - 1) {
      stack.push(swap(spot[0], spot[1], spot[0] + 1, spot[1]));
    }
    if (spot[1] != 0) {
      stack.push(swap(spot[0], spot[1], spot[0], spot[1] - 1));
    }
    if (spot[1] != N - 1) {
      stack.push(swap(spot[0], spot[1], spot[0], spot[1] + 1));
    }
    return stack;
  }

  public String toString() {
    // string representation of this board (in the output format specified below)
    StringBuilder output = new StringBuilder();
    //String output = Integer.toString(N) + "\n";
    output.append(Integer.toString(N) + '\n');
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        //StdOut.print(blocks[i][j]);
        //output.concat(Integer.toString(blocks[i][j]));
        int index = XY21D(i, j);
        output.append(Integer.toString(blocks[index]));
        if (j != N - 1) {
          //StdOut.print(' ');
          //output.concat(" ");
          output.append(' ');
        }
      }
      //StdOut.print('\n');
      //output.concat("\n");
      output.append('\n');
    }
    return output.toString();
  }

  public static void main(String[] args) {
    // unit tests (not graded)
  }
}