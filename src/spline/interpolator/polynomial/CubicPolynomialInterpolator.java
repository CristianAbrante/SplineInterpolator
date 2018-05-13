/**
 * @author cristian
 * @date 11 may. 2018
 */
package spline.interpolator.polynomial;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import function.Function;
import function.polynomial.Polynomial;
import spline.interpolator.Interpolator;

/**
 * <h2>CubicPolynomialInterpolator</h2>
 * This class perform the interpolation for
 * cubic polynomials
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	11 may. 2018
 * @version 	1.0.0
 */
public class CubicPolynomialInterpolator extends Interpolator {
  private final double DEFAULT_VALUE = 0.0;
  private ArrayList<Point2D> points;
  
  // Additional array used to calculate polynomial
  private ArrayList<Double> alpha;
  private ArrayList<Double> h;
  private ArrayList<Double> l;
  private ArrayList<Double> nu;
  private ArrayList<Double> zeta;
  
  // Coefficients of the polynomial.
  private ArrayList<Double> a;
  private ArrayList<Double> b;
  private ArrayList<Double> c;
  private ArrayList<Double> d;
  
  /**
   * @see spline.interpolator.Interpolator#interpolateFuntionsFrom(java.util.ArrayList)
   */
  @Override
  public ArrayList<Function> interpolateFuntionsFrom(ArrayList<Point2D> points) 
      throws NullPointerException, IllegalArgumentException {
    setPoints(points);
    //computeAlpha();
    
    // Compute the value of the array a (constant of polynomial)
    this.a = new ArrayList<Double>();
    for (int i = 0; i < getNumberOfPoints(); ++i) {
      this.a.add(getYAt(i));
    }
    
    // Compute the value of the array h
    this.h = new ArrayList<Double>();
    for (int i = 0; i < getNumberOfPoints() - 1; ++i) {
      this.h.add(getXAt(i + 1) - getXAt(i));
    }
    
    // Compute the value of the array alpha.
    this.alpha = new ArrayList<Double>();
    this.alpha.add(DEFAULT_VALUE);
    for (int i = 1; i < getNumberOfPoints() - 1; ++i) {
      double firstTerm = (3.0 / (getXAt(i + 1) - getXAt(i))) * (getYAt(i + 1) - getYAt(i));
      double secondTerm = (3.0 / (getXAt(i) - getXAt(i - 1))) * (getYAt(i) - getYAt(i - 1));
      this.alpha.add(firstTerm - secondTerm);
    }
    
    // Compute the value of the array l, nu, zeta
    this.l = new ArrayList<Double>();
    this.nu = new ArrayList<Double>();
    this.zeta = new ArrayList<Double>();
    
    l.add(1.0);
    nu.add(DEFAULT_VALUE);
    zeta.add(DEFAULT_VALUE);
    
    for (int i = 1; i < getNumberOfPoints() - 1; ++i) {
      // First, we compute l array.
      double firstTerm = 2.0 * (getXAt(i + 1) - getXAt(i - 1));
      double secondTerm = this.h.get(i - 1) * this.nu.get(i - 1);
      this.l.add(firstTerm - secondTerm);
      
      // Then, the nu array is computed.
      this.nu.add(this.h.get(i) / this.l.get(i));
      
      // Finally the zeta array is computed
      double numerator = this.alpha.get(i) - this.h.get(i - 1) * this.zeta.get(i - 1);
      this.zeta.add(numerator / this.l.get(i));
    }
    
    this.c = new ArrayList<Double>();
    this.b = new ArrayList<Double>();
    this.d = new ArrayList<Double>();
    for (int i = 0; i < getNumberOfPoints() - 1; ++i) {
      this.c.add(DEFAULT_VALUE);
      this.b.add(DEFAULT_VALUE);
      this.d.add(DEFAULT_VALUE);
    }
    
    this.c.add(getNumberOfPoints() - 1, DEFAULT_VALUE);    
    
    this.l.add(1.0);
    this.zeta.add(DEFAULT_VALUE);
    for (int j = getNumberOfPoints() - 2; j >= 0; --j) {
      this.c.set(j, this.zeta.get(j) - this.nu.get(j) * this.c.get(j + 1));
      
      // compute the value of array b
      double firstTerm = (this.a.get(j + 1) - this.a.get(j)) / this.h.get(j);
      double secondTerm = (this.h.get(j) * (this.c.get(j + 1) + 2.0 * this.c.get(j))) / 3.0;
      this.b.set(j, firstTerm - secondTerm);
      
      this.d.set(j, (this.c.get(j + 1) - this.c.get(j)) / (3.0 * this.h.get(j)));
    }
    
    // The array of cubic polynomials is created.
    ArrayList<Function> polynomials = new ArrayList<Function>();
    for (int i = 0; i < getNumberOfPoints() - 1; ++i) {
      polynomials.add(computeCubicPolynomial(i));
    }
    return polynomials;
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
  
  /**
   * Get the x value of the point at
   * index.
   * 
   * @param index of the point.
   * @return  the x value of the point at index.
   */
  private double getXAt(int index) {
    if (index >= 0 && index < getPoints().size()) {
      return getPoints().get(index).getX();
    } else {
      throw new IndexOutOfBoundsException("point index is greater than size");
    }
  }
  
  /**
   * Get the y value of the point at
   * index.
   * 
   * @param index of the point.
   * @return the y value of the point at index
   */
  private double getYAt(int index) {
    if (index >= 0 && index < getPoints().size()) {
      return getPoints().get(index).getY();
    } else {
      throw new IndexOutOfBoundsException("point index is greater than size");
    }
  }
  
  /**
   * @return the number of points of the interpolation
   */
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
  
  /**
   * Computes the value of the cubic polynomial 
   * given the values of the a, b, c and d 
   * coefficients.
   * 
   * @param index of the polynomial.
   * @return the constructed polynomial.
   */
  private Polynomial computeCubicPolynomial(int index) {
    ArrayList<Double> coefficients = new ArrayList<Double>();
    
    double a = this.a.get(index) 
             - this.b.get(index) * getXAt(index) 
             + this.c.get(index) * Math.pow(getXAt(index), 2.0)
             - this.d.get(index) * Math.pow(getXAt(index), 3.0);
    coefficients.add(a);
    
    double b = this.b.get(index)
             - 2.0 * this.c.get(index) * getXAt(index)
             + 3.0 * this.d.get(index) * Math.pow(getXAt(index), 2.0);
    coefficients.add(b);
    
    double c = this.c.get(index)
             - 3.0 * this.d.get(index) * getXAt(index);
    coefficients.add(c);
    coefficients.add(this.d.get(index));
    return new Polynomial(coefficients);
  }
}
