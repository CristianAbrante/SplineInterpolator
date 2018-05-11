/**
 * @author cristian
 * @date 11 may. 2018
 */
package spline.interpolator;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import function.Function;

/**
 * <h2>Interpolator</h2>
 * This class is used to interpolate
 * functions from a given set of
 * points.
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	11 may. 2018
 * @version 	1.0.0
 */
public abstract class Interpolator {
  /**
   * Method that interpolates n-1 
   * functions from a given set 
   * of n points.
   * 
   * @param points  the points that defines the areas to interpolate.
   * @return        the interpolated functions.
   * @throws NullPointerException if points are null.
   */
  /**
   * Method that interpolates n-1 
   * functions from a given set 
   * of n points.
   * 
   * @param points  the points that defines the areas to interpolate.
   * @return        the interpolated functions.
   * @throws NullPointerException if points are null.
   * @throws IllegalArgumentException If points are not in the correct range.
   */
  public abstract ArrayList<Function> interpolateFuntionsFrom(ArrayList<Point2D> points) 
      throws NullPointerException, IllegalArgumentException;
}
