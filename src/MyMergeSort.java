/**
 * 
 */

/**
 * @author hao
 *
 */
public class MyMergeSort {
  public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid + 1, hi);
    
    // copy
    for (int k = lo; k <= hi; k++) 
      aux[k] = a[k];
    
    // merge
    int i = lo, j = mid + 1;
    for (int k = 0; k <= hi; k++) {
      if (i > mid) // i exhausted, copy rest j
        a[k] = aux[j++];
      else if (j > hi) // j exhausted
        a[k] = aux[i++];
      else if (less(aux[j], aux[i]))
        a[k] = a[j++];
      else 
        a[k] = a[i++];
    }
    
    assert isSorted(a, lo, hi);
  }

}
