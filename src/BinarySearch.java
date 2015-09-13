/**
 * 
 */

/**
 * @author hao
 *
 */
public class BinarySearch {
  public static int binarySearch(int target, int[] args) {
    int lo = 0;
    int hi = args.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (target > args[mid])
        lo = mid + 1;
      else if (target < args[mid])
        hi = mid - 1;
      else
        return mid;
    }
    return -1;  
  }

}
