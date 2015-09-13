/**
 * 
 */

/**
 * @author hao
 * 
 */

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.*;

public class Brute {
  
  private boolean collinear(Point[] points) {
    if (points[0].slopeTo(points[1]) == points[1].slopeTo(points[2]) &&
        points[1].slopeTo(points[2]) == points[2].slopeTo(points[3]))
      return true;
    else
      return false;
  }
  
  private void CollinerWrapper(Point[] points) {
    if (collinear(points)) {
      // order them based on their coordin
      Arrays.sort(points);
      // print out and draw the line
      for (int i = 0; i < 4; i++) {
        StdOut.print(points[i].toString());
        //points[i].draw();
        if (i == 3) {
          StdOut.print('\n');
          points[0].drawTo(points[i]);
        }
        else {
          StdOut.print(" -> ");
        }
      }
    } 
  }
  
  /*
   * To check whether the 4 points p, q, r, and s are collinear, check whether the slopes between p
   * and q, between p and r, and between p and s are all equal. The order of growth of the running
   * time of your program should be N4 in the worst case and it should use space proportional to N.
   */
  public static void main(String[] args) {
    Brute br = new Brute();
    In in = new In(args[0]); // input file
    int N = in.readInt();
    Point[] points = new Point[N];
    
    // rescale coordinates and turn on animation mode
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);
    StdDraw.setPenRadius(0.01);  // make the points a bit larger
    
    //Arrays.sort(points, points[0].SLOPE_ORDER);
    int count = 0;
    while (!in.isEmpty()) {
      int x = in.readInt();
      int y = in.readInt();
      //StdOut.print(x);
      //StdOut.print(' ');
      //StdOut.println(y);
      points[count] = new Point(x, y);
      points[count].draw();
      count++;
    }
    
    
    
    for (int i = 0; i < N - 3; i++) {
      for (int j = i + 1; j < N - 2; j++) {
        for (int k = j + 1; k < N - 1; k++) {
          for (int t = k + 1; t < N; t++) {
            Point[] fourPoints = new Point[4];
            fourPoints[0] = points[i];
            //StdOut.print(points[i].toString());
            fourPoints[1] = points[j];
            //StdOut.print(points[j].toString());
            fourPoints[2] = points[k];
            //StdOut.print(points[k].toString());
            fourPoints[3] = points[t];
            //StdOut.println(points[t].toString());
            br.CollinerWrapper(fourPoints);
          }
        }
      }
    }
    // display to screen all at once
    StdDraw.show(0);

    // reset the pen radius
    StdDraw.setPenRadius();
  }

}
