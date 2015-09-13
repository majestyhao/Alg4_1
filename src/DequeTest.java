import static org.junit.Assert.*;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


public class DequeTest {
  Deque dq;

  @Before
  public void setUp() throws Exception {
    dq = new Deque();
  }

  @Test
  public void testIsEmpty() {
    assertEquals(true, dq.isEmpty());
    dq.addFirst(3);
    assertEquals(false, dq.isEmpty());
    dq.removeLast();
    assertEquals(true, dq.isEmpty());
  }

  @Test
  public void testSize() {
    assertEquals(0, dq.size());
    dq.addFirst(3);
    assertEquals(1, dq.size());
    dq.removeLast();
    assertEquals(0, dq.size());
    dq.addFirst('a');
    assertEquals(1, dq.size());
    dq.addLast('b');
    assertEquals(2, dq.size());
  }

  @Test
  public void testAddFirst() {
    dq.addFirst(1);
    assertEquals(1, dq.removeFirst());
    dq.addFirst(1);
    dq.addFirst(2);
    assertEquals(2, dq.removeFirst());
    assertEquals(1, dq.removeFirst());
    dq.addFirst(1);
    dq.addFirst(2);
    assertEquals(1, dq.removeLast());
    assertEquals(2, dq.removeLast());
  }

  @Test
  public void testAddLast() {
    dq.addLast(1);
    assertEquals(1, dq.removeLast());
    dq.addLast(1);
    dq.addLast(2);
    assertEquals(1, dq.removeFirst());
    assertEquals(2, dq.removeFirst());
    dq.addLast(1);
    dq.addLast(2);
    assertEquals(2, dq.removeLast());
    assertEquals(1, dq.removeLast());
  }

  @Test
  public void testRemoveFirst() {
    dq.addLast(1);
    assertEquals(1, dq.removeFirst());
    dq.addLast(1);
    dq.addLast(2);
    assertEquals(1, dq.removeFirst());
    assertEquals(2, dq.removeFirst());
    dq.addFirst(1);
    dq.addFirst(2);
    assertEquals(2, dq.removeFirst());
    assertEquals(1, dq.removeFirst());
  }

  @Test
  public void testRemoveLast() {
    dq.addLast(1);
    assertEquals(1, dq.removeLast());
    dq.addLast(1);
    dq.addLast(2);
    assertEquals(2, dq.removeLast());
    assertEquals(1, dq.removeLast());
    dq.addFirst(1);
    dq.addFirst(2);
    assertEquals(1, dq.removeLast());
    assertEquals(2, dq.removeLast());
  }

  @Test
  public void testIterator() {
    Iterator it = dq.iterator();
    assertEquals(false, it.hasNext());
    dq.addFirst(4);
    dq.addFirst(3);
    dq.addFirst(2);
    dq.addFirst(1);
    it = dq.iterator();
    assertEquals(true, it.hasNext());
    assertEquals(1, it.next());
    assertEquals(true, it.hasNext());
    assertEquals(2, it.next());
    assertEquals(true, it.hasNext());
    assertEquals(3, it.next());
    assertEquals(true, it.hasNext());
    assertEquals(4, it.next());
    assertEquals(false, it.hasNext());
  }

}
