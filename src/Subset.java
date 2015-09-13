/**
 * 
 */

/**
 * @author hao
 *
 */
import edu.princeton.cs.algs4.*;

public class Subset {
  private RandomizedQueue rq;
  
  public Subset() {
    rq = new RandomizedQueue();
  }
  
  private void addElement(String item) {
    rq.enqueue(item);
  }
  
  private void printElement() {
    String tmp = (String) rq.dequeue(); 
    StdOut.println(tmp);
  }
  
  public static void main(String[] args) {
    Subset sb = new Subset();
    int k = Integer.valueOf(args[0]);
    while (!StdIn.isEmpty()) {
      sb.addElement(StdIn.readString());
    }
    //for (int i = 1; i < args.length; i++) {
      //sb.addElement(args[i]);
    //}
    for (int i = 0; i < k; i++)
      sb.printElement();
    
  }
}