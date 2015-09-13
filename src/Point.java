/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.*;

public class Point implements Comparable<Point> {
  
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
      if (that == null)
        throw new java.lang.NullPointerException();
      if (this.x - that.x == 0) {
        if (that.y != this.y)
          return Double.POSITIVE_INFINITY;
        else 
          return Double.NEGATIVE_INFINITY;
      }
      if (this.y == that.y)
        return +0.0;
   
      return (double) (that.y - this.y) / (that.x - this.x); 
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
      if (that == null)
        throw new java.lang.NullPointerException();
      if (this.y < that.y)
        return -1;
      if (this.y > that.y)
        return 1;
      if (this.x < that.x)
        return -1;
      if (this.x > that.x)
        return 1;
      return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    private class SlopeOrder implements Comparator<Point> {
      public int compare(Point q1, Point q2) {
        if (q1 == null || q2 == null)
          throw new java.lang.NullPointerException();
        double slope1 = Point.this.slopeTo(q1);
        double slope2 = Point.this.slopeTo(q2);
        //if (slope1 == Double.POSITIVE_INFINITY && slope2 == Double.NEGATIVE_INFINITY
           // || slope2 == Double.POSITIVE_INFINITY && slope1 == Double.NEGATIVE_INFINITY)
          //return 0;
        if (slope1 < slope2)
          return -1;
        if (slope1 > slope2)
          return 1;
        return 0;
      }
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}