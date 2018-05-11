/**
 * @author cristian
 * @date 11 may. 2018
 */
package spline.interpolator.polynomial;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import function.Function;
import spline.interpolator.Interpolator;

/**
 * <h2>PolynomialInterpolator</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	11 may. 2018
 * @version 	1.0.0
 */
public class PolynomialInterpolator extends Interpolator {
  private final double DEFAULT_VALUE = 0.0;
  private ArrayList<Point2D> points; 
  private ArrayList<Double> alpha;
  
  /**
   * @see spline.interpolator.Interpolator#interpolateFuntionsFrom(java.util.ArrayList)
   */
  @Override
  public ArrayList<Function> interpolateFuntionsFrom(ArrayList<Point2D> points) 
      throws NullPointerException, IllegalArgumentException {
    setPoints(points);
    computeAlpha();
    
    return null;
  }
  
  // PRIVATE METHODS
  
  /**
   * @return the points
   */
  public ArrayList<Point2D> getPoints() {
    return points;
  }

  /**
   * @param points the points to set
   */
  public void setPoints(ArrayList<Point2D> points) {
    if (points == null) {
      throw new NullPointerException("points can't be null");
    }
    if (!arePointsInRange(points)) {
      throw new IllegalArgumentException("points are not in the correct range");
    }
    
    this.points = points;
  }
  
  private double getXAt(int index) {
    if (index >= 0 && index < getPoints().size()) {
      return getPoints().get(index).getX();
    } else {
      throw new IndexOutOfBoundsException("point index is greater than size");
    }
  }
  
  private double getYAt(int index) {
    if (index >= 0 && index < getPoints().size()) {
      return getPoints().get(index).getY();
    } else {
      throw new IndexOutOfBoundsException("point index is greater than size");
    }
  }
  
  private int getNumberOfPoints() {
    return getPoints().size();
  }
  
  /**
   * Method that returns true if points are
   * in the correct range.
   *  x0 < x1 < ... < xn
   *  
   * @param points
   * @return
   */
  private boolean arePointsInRange(ArrayList<Point2D> points) {
    for (int i = 0; i < points.size() - 1; ++i) {
      Point2D p1 = points.get(i);
      Point2D p2 = points.get(i + 1);
      if (Double.compare(p1.getX(), p2.getX()) >= 0) {
        return false;
      }
    }
    return true;
  }
  
  private void computeAlpha() {
    this.alpha = new ArrayList<Double>();
    this.alpha.add(DEFAULT_VALUE);
    for (int i = 1; i < getNumberOfPoints() - 1; ++i) {
      double firstTerm = (3.0 / (getXAt(i + 1) - getXAt(i))) * (getYAt(i + 1) - getYAt(i));
      double secondTerm = (3.0 / (getXAt(i) - getXAt(i - 1))) * (getYAt(i) - getYAt(i - 1));
      this.alpha.add(firstTerm - secondTerm);
    }
  }
}
