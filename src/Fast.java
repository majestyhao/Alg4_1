import java.util.Arrays;
import edu.princeton.cs.algs4.*;

/**
 * 
 */

/**
 * @author hao
 * 
 */
public class Fast {
  private int lineCount = 0;
  private String[] lines;
  private void CollinerWrapper(Point[] pts) {
    Arrays.sort(pts);
    lines = new String[1];
    for (int i = 0; i < pts.length - 1; i++) {
      Point[] points = new Point[pts.length - i - 1];
      Point point = pts[i];
      for (int j = 0; j < pts.length - i - 1; j++)
        points[j] = pts[i + j + 1];
      colliner(points, point);
      //StdOut.println();
      //StdOut.println();
    }
  }

  private void colliner(Point[] points, Point point) {
    Arrays.sort(points, point.SLOPE_ORDER);
    int count = 2, start = 0;
    double prior = point.slopeTo(points[0]);
    //StdOut.println(point.toString());
    //StdOut.print(points[0].toString());
    //StdOut.print(prior);
    for (int i = 1; i < points.length; i++) {
      if (point.slopeTo(points[i]) == prior) {
        //StdOut.print(points[i].toString());
        prior = point.slopeTo(points[i]);
        //StdOut.print(prior);
        count++;
      } else {
        //StdOut.println();
        //StdOut.print(points[i].toString());
        prior = point.slopeTo(points[i]);
        //StdOut.print(prior);
        if (count >= 4) {
          showLine(points, point, start, count);
        }
        count = 2;
        start = i;
      }
    } 
    if (count >= 4)
      showLine(points, point, start, count);
  }

  private void showLine(Point[] pts, Point point, int start, int count) {
    Point[] points = new Point[count - 1];
    for (int i = 0; i < count - 1; i++)
      points[i] = pts[start + i];
    Arrays.sort(points);
    String match = points[count - 2].toString() + points[count - 3].toString();
    //StdOut.println(match);
    for (int i = 0; i < lineCount; i++) {
      if (match.equals(lines[i]))
        return;
    }
   

    lines[lineCount++] = points[count - 2].toString() + 
        points[count - 3].toString();
    
    if (lineCount  == lines.length) {
      String[] newLines = new String[2 * lineCount];
      for (int i = 0; i < lines.length; i++) 
        newLines[i] = lines[i];
      lines = newLines;
    }
    //StdOut.println(lines[lineCount]);
    //lineCount++;
    
    points = new Point[count];
    for (int i = 0; i < count - 1; i++)
      points[i] = pts[start + i];
    points[count - 1] = point; 
    Arrays.sort(points);
    
    for (int i = 0; i < points.length; i++) {
      // order them based on their coordin
      // print out and draw the line
      StdOut.print(points[i].toString());
      
      if (i == count - 1) {
        points[0].drawTo(points[i]);
        StdOut.print('\n');
        //StdOut.print('\t');
      } else {
        StdOut.print(" -> ");
      }
    }
    //for (int i = 0; i < count - 1; i++)
      //pts[start + i] = points[i];
  }

  public static void main(String[] args) {
    Fast fs = new Fast();
    In in = new In(args[0]); // input file
    int N = in.readInt();
    Point[] points = new Point[N];
    
    // rescale coordinates and turn on animation mode
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);
    StdDraw.setPenRadius(0.01); // make the points a bit larger

    // Arrays.sort(points, points[0].SLOPE_ORDER);
    int count = 0;
    while (!in.isEmpty()) {
      int x = in.readInt();
      int y = in.readInt();
      // StdOut.print(x);
      // StdOut.print(' ');
      // StdOut.println(y);
      points[count] = new Point(x, y);
      points[count].draw();
      count++;
    }



    fs.CollinerWrapper(points);

    // display to screen all at once
    StdDraw.show(0);

    // reset the pen radius
    StdDraw.setPenRadius();
  }
}
