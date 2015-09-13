/**
 * @author hao
 *
 */
import java.util.Iterator;
import edu.princeton.cs.algs4.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] items;
  private int N, capacity, head;

  public RandomizedQueue() {
    // construct an empty randomized queue
    N = 0;
    capacity = 1;
    head = -1;
    items = (Item[]) new Object[capacity];
  }

  public boolean isEmpty() {
    // is the queue empty?
    return N == 0;
  }

  public int size() {
    // return the number of items on the queue
    return N;
  }

  private void resize(int newCapacity) {
    Item[] tmp = (Item[]) new Object[newCapacity];
    for (int i = 0; i < N; i++) {
      tmp[i] = items[i];
    }
    items = tmp;
    capacity = newCapacity;
  }

  public void enqueue(Item item) {
    // add the item
    if (item == null)
      throw new java.lang.NullPointerException("Item should not be null.");
    if (N == capacity) {
      resize(capacity * 2);
    }
    items[++head] = item;
    // head++;
    N++;
  }

  private class RandIterator implements Iterator<Item> {
    private int count;
    private int[] rand;

    RandIterator() {
      count = 0;
      rand = new int[N];
      boolean[] met = new boolean[N];
      for (int i = 0; i < N; i++)
        met[i] = false;
      for (int i = 0; i < N; i++) {
        boolean flag = true;
        int x = -1;
        while (flag) {
          x = StdRandom.uniform(N);
          if (!met[x]) {
            flag = false;
            met[x] = true;
          }
        }
        rand[i] = x;
      }
    }

    public boolean hasNext() {
      if (N == 0)
        return false;
      else {
        return N - count != 0;
      }
    }

    public Item next() {
      if (!hasNext())
        throw new java.util.NoSuchElementException("No next element.");
      Item item = items[rand[count]];
      // for (int i = 0; i < count; i++)
      // System.out.print(met[i]);
      // System.out.println(current);
      count++;
      return item;
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException("Do not support remove.");
    }
  }

  public Iterator<Item> iterator() {
    // return an independent iterator over items in random order
    return new RandIterator();
  }

  public Item dequeue() {
    // remove and return a random item
    if (N == 0)
      throw new java.util.NoSuchElementException("Remove from an empty deque.");
    if (N == 1 / 4 * capacity)
      resize(1 / 2 * capacity);
    int target = StdRandom.uniform(N);
    Item item = items[target];
    // System.out.print(target);
    // System.out.println(head);
    items[target] = items[N - 1];
    //for (int i = target; i < head - target; i++)
      //items[i] = items[i + 1];
    N--;
    head--;
    return item;
  }

  public Item sample() {
    // return (but do not remove) a random item
    int target = StdRandom.uniform(N);
    Item item = items[target];
    return item;
  }

  public static void main(String[] args) {
    // unit testing
    RandomizedQueue rq = new RandomizedQueue();
    rq.enqueue(1);
    // System.out.print(rq.size());
    rq.enqueue(2);
    // System.out.print(rq.size());
    rq.dequeue();
    rq.dequeue();
  }
}