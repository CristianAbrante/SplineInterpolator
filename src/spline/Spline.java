/**
 * 
 */
package spline;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import function.Function;
import spline.interpolator.Interpolator;

/**
 * <h2>Spline</h2>
 * The spline is a picewise function
 * defined between different points or
 * knots.
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class Spline extends Function {
  private ArrayList<Point2D>  points;
  private Interpolator        interpolator;
  private ArrayList<Function> interpolatedFunctions;
  
  /**
   * Constructor of Spline
   * 
   * @param knots ordered knots.
   * @param interpolator used to create the functions.
   */
  public Spline(Interpolator interpolator, ArrayList<Point2D> points) {
    if (interpolator != null) {
      this.interpolator = interpolator;    
    } else {
      throw new NullPointerException("interpolator can't be null.");
    }
    setPoints(points);
  }

  /**
   * @return the knots
   */
  public ArrayList<Point2D> getPoints() {
    return points;
  }
  
  /**
   * @param knots the knots to set
   */
  public void setPoints(ArrayList<Point2D> points) {
    if (points != null) {
      this.points = points;
      setInterpolatedFunctions(getInterpolator().interpolateFuntionsFrom(getPoints()));
    } else {
      throw new NullPointerException("points can't be null.");
    }
  }
  
  /**
   * Gets the point at specified indes
   * @param index of the point
   * @return the point at index
   */
  public Point2D getPoint(int index) {
    if (index >= 0 && index < getPoints().size()) {
      return getPoints().get(index);
    } else {
      throw new IndexOutOfBoundsException("point index is greater than size");
    }
  }
  
  /**
   * @return first point of the spline.
   */
  public Point2D getFirstPoint() {
    return getPoint(0);
  }
  
  /**
   * @return last point of the spline.
   */
  public Point2D getLastPoint() {
    return getPoint(getNumberOfPoints() - 1);
  }
  
  /**
   * @return the number of points of the interpolation
   */
  public int getNumberOfPoints() {
    return getPoints().size();
  }
  
  /**
   * @return the interpolator
   */
  public Interpolator getInterpolator() {
    return interpolator;
  }
  
  /**
   * @param interpolator the interpolator to set
   */
  public void setInterpolator(Interpolator interpolator) {
    if (interpolator != null) {
      this.interpolator = interpolator;
      setInterpolatedFunctions(getInterpolator().interpolateFuntionsFrom(getPoints())); 
    } else {
      throw new NullPointerException("interpolator can't be null.");
    }
  }

  /**
   * @see function.Function#getValue(double)
   */
  @Override
  public double getValue(double x) {
    return getFunctionForTheValue(x).getValue(x);
  }

  /**
   * @see function.Function#toString()
   */
  @Override
  public String toString() {
    String result = new String();
    for (Function function : getInterpolatedFunctions()) {
      result += function.toString() + '\n';
    }
    return result;
  }
  
  // PRIVATE METHODS
  
  /**
   * @return the interpolatedFunctions
   */
  private ArrayList<Function> getInterpolatedFunctions() {
    return interpolatedFunctions;
  }

  /**
   * @param interpolatedFunctions the interpolatedFunctions to set
   */
  private void setInterpolatedFunctions(ArrayList<Function> interpolatedFunctions) {
    if (interpolatedFunctions != null) {
      this.interpolatedFunctions = interpolatedFunctions;      
    } else {
      throw new NullPointerException("interpolated functions can't be null");
    }
  }
  
  /**
   * Get the x value of the point at
   * index.
   * 
   * @param index of the point.
   * @return  the x value of the point at index.
   */
  private double getXAt(int index) {
    return getPoint(index).getX();
  }
  
  /**
   * Get the y value of the point at
   * index.
   * 
   * @param index of the point.
   * @return the y value of the point at index
   */
  private double getYAt(int index) {
    return getPoint(index).getY();
  }
  
  /**
   * Returns the function to apply for the 
   * specified x value.
   * 
   * @param x the value.
   * @return the function for the specified value.
   */
  private Function getFunctionForTheValue(double x) {
    for (int i = 0; i < getNumberOfPoints() - 1; ++i) {
      if (Double.compare(x, getXAt(i)) >= 0
       && Double.compare(x, getXAt(i + 1)) < 0) {
        return getInterpolatedFunctions().get(i);
      }
    }
    throw new IndexOutOfBoundsException("double is not in the range " + getXAt(0) + " - " + getXAt(getNumberOfPoints() - 1));
  }
}
