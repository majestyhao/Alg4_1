import java.util.Iterator;

/**
 * 
 */

/**
 * @author hao
 *
 */
public class Deque<Item> implements Iterable<Item> {
   private class Node {
     Item item;
     Node prior, next;
     
     Node(Item item) {
       this.item = item;
       prior = null;
       next = null;
     }
   }
   
   private Node head, rear;
   private int length; 
   
   public Deque() {
     // construct an empty deque
     head = null;
     rear = null;
     length = 0;
   }
   
   public boolean isEmpty()  {
     // is the deque empty?
     return (length == 0);
   }
   
   public int size() {
     // return the number of items on the deque
     return length;
   }
   
   public void addFirst(Item item) {
     // add the item to the front
     if (item == null)
       throw new java.lang.NullPointerException("Item should not be null.");
     Node newnode = new Node(item);
     length++;
     if (head == null) {
       head = newnode;
       rear = newnode;
       return;
     }
     newnode.next = head;
     head.prior = newnode;
     head = newnode;
   }
   
   public void addLast(Item item) {
     // add the item to the end
     if (item == null)
       throw new java.lang.NullPointerException("Item should not be null.");
     Node newnode = new Node(item);
     length++;
     if (rear == null) {
       head = newnode;
       rear = newnode;
       return;
     }
     rear.next = newnode;
     newnode.prior = rear;
     rear = newnode;
   }
   
   public Item removeFirst() {
     // remove and return the item from the front
     if (head == null)
       throw new java.util.NoSuchElementException("Remove from an empty deque.");
     Item item = head.item;
     head = head.next;
     if (head == null)
       rear = null;
     else
       head.prior = null;
     length--;
     return item;
   }
   
   public Item removeLast() {
     // remove and return the item from the end
     if (rear == null)
       throw new java.util.NoSuchElementException("Remove from an empty deque.");
     Item tmp = rear.item;
     rear = rear.prior;
     if (rear == null)
       head = null;
     else
       rear.next = null;
     length--;
     return tmp;
   }
   
   private class DequeIterator implements Iterator<Item> {
     private Node current = head;
     public boolean hasNext() {
       return current != null;
     }
     
     public Item next() {
       if (!hasNext())
         throw new java.util.NoSuchElementException("No next element.");
       Item item = current.item;
       current = current.next;
       return item;
     }
     
     public void remove() {
       throw new java.lang.UnsupportedOperationException("Do not support remove.");
     }
   }
   
   public Iterator<Item> iterator() {
     // return an iterator over items in order from front to end
     return new DequeIterator();
   }
   
   public static void main(String[] args) {
     // unit testing
     
   }
}