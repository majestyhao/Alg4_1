import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


public class BoardTest {
  private Board board;
  private Board board2, board3;

  @Before
  public void setUp() throws Exception {
    int[][] blocks = new int[3][3];
    blocks[0][0] = 8;
    blocks[0][1] = 1;
    blocks[0][2] = 3;
    blocks[1][0] = 4;
    blocks[1][1] = 0;
    blocks[1][2] = 2;
    blocks[2][0] = 7;
    blocks[2][1] = 6;
    blocks[2][2] = 5;
    board = new Board(blocks);
    board2 = new Board(blocks);
    In in = new In("puzzle2x2-unsolvable1.txt");
    int N = in.readInt();
    blocks = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        blocks[i][j] = in.readInt();
    board3 = new Board(blocks);
  }

  @Test
  public void testDimension() {
    assertEquals(3, board.dimension());
  }

  @Test
  public void testHamming() {
    assertEquals(5, board.hamming());
  }

  @Test
  public void testManhattan() {
    assertEquals(10, board.manhattan());
  }

  @Test
  public void testIsGoal() {
    assertEquals(false, board.isGoal());
  }

  @Test
  public void testTwin() {
    Board boardTmp = board.twin();
    StdOut.print("TestTwin: ");
    //StdOut.println(boardTmp);
    
    StdOut.println(board);
    boardTmp = board.twin();
    StdOut.println(boardTmp);
    StdOut.print("TestTwinDone\n");
  }

  @Test
  public void testEqualsObject() {
    assertEquals(true, board.equals(board));
    assertEquals(true, board.equals(board2));
  }

  @Test
  public void testNeighbors() {
    StdOut.println(board.toString());
    Iterable<Board> stack = board.neighbors();
    Iterator it = stack.iterator();
    while (it.hasNext()) {
      StdOut.println(it.next());
    }
  }

  @Test
  public void testToString() {
  }

}
