/**
 * 
 */
package controller;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import drawable.DrawableSpline;
import spline.Spline;
import spline.interpolator.Interpolator;
import spline.interpolator.polynomial.CubicPolynomialInterpolator;
import view.MainFrame;
import view.SettingsPanel;
import view.VisualPanel;

/**
 * <h2>Controller</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class Controller {
  
  private final int WIDTH = 500;
  private final int HEIGHT = 500;
  private final double POINT_RADIUS = 5;
  
  private int numberOfPoints = 6;
  
  private PointsManager        manager;
  private Interpolator        interpolator;
  private Spline              spline;
  
  private DrawableSpline      drawableSpline;
  private VisualPanel         visualPanel;
  private SettingsPanel       settingsPanel;
  private MainFrame           frame;
  
  public Controller() {
    manager = new PointsManager();
    manager.setRandomPoints(numberOfPoints, POINT_RADIUS, 0, WIDTH, 0, HEIGHT);
    interpolator = new CubicPolynomialInterpolator();
    spline = new Spline(interpolator, manager.getPoints());
    drawableSpline = new DrawableSpline(spline);
    drawableSpline.setPointRadius((int) POINT_RADIUS);
    
    visualPanel = new VisualPanel(this, WIDTH, HEIGHT);
    visualPanel.setSpline(drawableSpline);
    settingsPanel = new SettingsPanel();
    frame = new MainFrame("Spline Interpolator", settingsPanel, visualPanel);
  }
}
