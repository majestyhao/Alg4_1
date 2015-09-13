import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PercolationTest {
  Percolation perc;
  int N;

  @Before
  public void setUp() throws Exception {
    N = 2;
    perc = new Percolation(N);
  }

  @Test
  public void testPercolation() {
  }

  @Test
  public void testOpen() {
    //perc.open(3, 4);
    perc.open(1, 1);
    perc.open(1, 2);
  }

  @Test
  public void testIsOpen() {
    perc.open(1, 1);
    perc.open(1, 2);
    assertEquals(true, perc.isOpen(1, 1));
    assertEquals(true, perc.isOpen(1, 2));
    assertEquals(false, perc.isOpen(2, 1));
    assertEquals(false, perc.isOpen(2, 2));
  }

  @Test
  public void testIsFull() {
    perc.open(1, 1);
    perc.open(1, 2);
    assertEquals(true, perc.isFull(2, 1));
    assertEquals(true, perc.isFull(2, 2));
    assertEquals(false, perc.isFull(1, 1));
    assertEquals(false, perc.isFull(1, 2));
  }

  @Test
  public void testPercolates() {
    perc.open(1, 1);
    perc.open(1, 2);
    assertEquals(false, perc.percolates());
    perc.open(2, 1);
    assertEquals(true, perc.percolates());
  }

}
