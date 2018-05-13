/**
 * 
 */
package controller;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import spline.Spline;
import spline.interpolator.Interpolator;
import spline.interpolator.polynomial.CubicPolynomialInterpolator;

/**
 * <h2>Controller</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class Controller {
  
  private PointsManager        manager;
  private Interpolator        interpolator;
  private Spline              spline;
  
  public Controller() {
    manager = new PointsManager();
    interpolator = new CubicPolynomialInterpolator();
    spline = new Spline(interpolator, manager.getPoints());
  }
}
