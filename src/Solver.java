/**
 * 
 */
import java.util.Comparator;
import java.util.Iterator;

import edu.princeton.cs.algs4.*;

/**
 * @author hao
 * 
 */
public class Solver {
  private static final Comparator<SearchNode> BY_ALL = new ByAll();
  private static final Comparator<SearchNode> BY_HAMM = new ByHamm();
  private static final Comparator<SearchNode> BY_MANHA = new ByManha();

  private static class ByAll implements Comparator<SearchNode> {
    public int compare(SearchNode v, SearchNode w) {
      if (v == null || w == null)
        throw new java.lang.NullPointerException();
      if (v.getManha() < w.getManha())
        return -1;
      if (v.getManha() > w.getManha())
        return 1;
      if (v.getHamm() < w.getHamm())
        return -1;
      if (v.getHamm() > w.getHamm())
        return 1;
      return 0;
    }
  }

  private static class ByHamm implements Comparator<SearchNode> {
    public int compare(SearchNode v, SearchNode w) {
      if (v == null || w == null)
        throw new java.lang.NullPointerException();
      if (v.getHamm() < w.getHamm())
        return -1;
      if (v.getHamm() > w.getHamm())
        return 1;
      return 0;
    }
  }

  private static class ByManha implements Comparator<SearchNode> {
    public int compare(SearchNode v, SearchNode w) {
      if (v == null || w == null)
        throw new java.lang.NullPointerException();
      return v.board.manhattan() + v.moves - w.board.manhattan() - w.moves;
    }
  }

  private class SearchNode {
    private Board board;
    private int moves;
    private SearchNode prior;

    SearchNode(Board board, int moves, SearchNode prior) {
      this.board = board;
      this.moves = moves;
      this.prior = prior;
    }

    public int getHamm() {
      return board.hamming() + moves;
    }

    public int getManha() {
      return board.manhattan() + moves;
    }
  }

  private MinPQ<SearchNode> minpq, minpqTwin;
  private Stack<Board> results;
  private SearchNode result, resultTwin;
  private boolean solvable;

  public Solver(Board initial) {
    // find a solution to the initial board (using the A* algorithm)
    result = new SearchNode(initial, 0, null);
    resultTwin = new SearchNode(initial.twin(), 0, null);
    Solve();
  }

  private void Solve() {
    minpq = new MinPQ<SearchNode>(BY_ALL);
    minpqTwin = new MinPQ<SearchNode>(BY_ALL);
    minpq.insert(result);
    minpqTwin.insert(resultTwin);
    results = new Stack<Board>();
    while (result.board.isGoal() != true && resultTwin.board.isGoal() != true) {
      // StdOut.println(result.board.hamming());
      // StdOut.println(result.board.manhattan());
      // StdOut.println(result.board.toString());
      result = minpq.delMin();
      /*
       * SearchNode newResult = minpq.delMin(); while (result.prior != null && newResult.prior !=
       * result) { newResult = minpq.delMin(); } //StdOut.println(minpq.size()); result = newResult;
       */
      // StdOut.println(result.board.toString());
      // results.enqueue(result.board);

      for (Board board : result.board.neighbors()) {
        // StdOut.println(board.toString());
        if (result.prior != null && board.equals(result.prior.board))
          continue;
        SearchNode newNode = new SearchNode(board, result.moves + 1, result);
        minpq.insert(newNode);
        // StdOut.println(newNode.getManha());
        // StdOut.println(newNode.getHamm());
        // StdOut.println(newNode.board);
      }

      resultTwin = minpqTwin.delMin();
      for (Board board : resultTwin.board.neighbors()) {
        // StdOut.println(board.toString());
        if (resultTwin.prior != null && board.equals(resultTwin.prior.board))
          continue;
        SearchNode newNode = new SearchNode(board, resultTwin.moves + 1, resultTwin);
        minpqTwin.insert(newNode);
        // StdOut.println(newNode.board);
      }
    }

    // run = true;
    if (result.board.isGoal()) {
      solvable = true;
      SearchNode it = result;
      while (it.prior != null) {
        results.push(it.board);
        it = it.prior;
      }
      results.push(it.board);
    }
    // if (resultTwin.board.isGoal()) {
    else {
      // results = resultsTwin;
      solvable = false;
      // solvable = true;
    }
  }

  public boolean isSolvable() {
    // if (!run)
    // Solve();
    // is the initial board solvable?
    return solvable;
  }

  public int moves() {
    // min number of moves to solve initial board; -1 if unsolvable
    // if (!run)
    // Solve();
    if (!isSolvable()) {
      return -1;
    }
    return result.moves;
  }

  public Iterable<Board> solution() {
    // sequence of boards in a shortest solution; null if unsolvable
    if (!solvable)
      return null;
    return results;
  }

  public static void main(String[] args) {
    // solve a slider puzzle (given below)
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}