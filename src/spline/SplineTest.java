/**
 * 
 */
package spline;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import spline.interpolator.polynomial.CubicPolynomialInterpolator;
import spline.interpolator.Interpolator;

/**
 * <h2>SplineTest</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class SplineTest {
  
  private Spline spline;
  ArrayList<Point2D> points = new ArrayList<Point2D>();
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    Interpolator interpolator = new CubicPolynomialInterpolator();
    points.add(new Point2D.Double(1.0, 2.0));
    points.add(new Point2D.Double(2.0, 3.0));
    points.add(new Point2D.Double(5.0, 6.0));
    points.add(new Point2D.Double(8.0, 8.0));
    
    spline = new Spline(interpolator, points);
  }

  @Test
  public void test() {
    for (double x = points.get(0).getX(); Double.compare(x, points.get(3).getX()) <= 0; x += 0.02) {
      System.out.println(x + " " + spline.getValue(x));
    }
  }

}
