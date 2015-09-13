import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


public class RandomizedQueueTest {
  RandomizedQueue rq;

  @Before
  public void setUp() throws Exception {
    rq = new RandomizedQueue();
  }

  @Test
  public void testIsEmpty() {
    assertEquals(true, rq.isEmpty());
    rq.enqueue(3);
    assertEquals(false, rq.isEmpty());
    rq.dequeue();
    assertEquals(true, rq.isEmpty());
  }

  @Test
  public void testSize() {
    assertEquals(0, rq.size());
    rq.enqueue(3);
    assertEquals(1, rq.size());
    rq.dequeue();
    assertEquals(0, rq.size());
    rq.enqueue('a');
    assertEquals(1, rq.size());
    rq.enqueue('b');
    assertEquals(2, rq.size());
  }

  @Test
  public void testEnqueue() {
    rq.enqueue(1);;
    assertEquals(1, rq.dequeue());
    rq.enqueue(1);
    rq.enqueue(2);
    int tmp = (int) rq.dequeue();
    if (tmp != 1 && tmp != 2)
      fail("deque error");
    else {
      if (tmp == 1) {
        if ((int) rq.dequeue() != 2)
          fail("deque error");
      } else {
        if ((int) rq.dequeue() != 1)
          fail("deque error");
      }
    }
  }

  @Test
  public void testIterator() {
    Iterator it = rq.iterator();
    assertEquals(false, it.hasNext());
    rq.enqueue(1);
    rq.enqueue(2);
    rq.enqueue(3);
    rq.enqueue(4);
    it = rq.iterator();
    assertEquals(true, it.hasNext());
    //it.next();
    System.out.print(it.next());
    assertEquals(true, it.hasNext());
    System.out.print(it.next());
    //it.next();
    assertEquals(true, it.hasNext());
    System.out.print(it.next());
    //it.next();
    assertEquals(true, it.hasNext());
    System.out.print(it.next());
    //it.next();
    assertEquals(false, it.hasNext());
  }

  @Test
  public void testSample() {
    rq.enqueue(1);
    rq.enqueue(2);
    rq.enqueue(3);
    rq.enqueue(4);
    int tmp = (int) rq.dequeue();
    //System.out.print(tmp);
    if (tmp > 4 || tmp < 0)
      fail("sample error");
  }

}
