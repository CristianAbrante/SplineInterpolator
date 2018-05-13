/**
 * 
 */
package drawable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Random;

import spline.Spline;

/**
 * <h2>DrawableSpline</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class DrawableSpline {
  private Spline  spline;
  private Color   splineColor;
  private Color   pointsColor = Color.RED;
  private int     pointRadius = 5;
  private final Stroke STROKE = new BasicStroke(3f);
  private Path2D  splineLine;
  
  /**
   * Constructor of DrawableSpline
   * 
   * @param spline
   * @param color
   */
  public DrawableSpline(Spline spline, Color splineColor) {
    setSpline(spline);
    setSplineColor(splineColor);
  }
  
  /**
   * Constructor of DrawableSpline
   * 
   * @param spline
   */
  public DrawableSpline(Spline spline) {
    this(spline, getRandomColor());
  }
  
  /**
   * @return the spline
   */
  public Spline getSpline() {
    return spline;
  }
  
  /**
   * @param spline the spline to set
   */
  public void setSpline(Spline spline) {
    this.spline = spline;
  }
  
  /**
   * @return the splineColor
   */
  public Color getSplineColor() {
    return splineColor;
  }

  /**
   * @param splineColor the splineColor to set
   */
  public void setSplineColor(Color splineColor) {
    if (splineColor != null) {
      this.splineColor = splineColor;      
    } else {
      throw new NullPointerException();
    }
  }

  /**
   * @return the pointsColor
   */
  public Color getPointsColor() {
    return pointsColor;
  }

  /**
   * @param pointsColor the pointsColor to set
   */
  public void setPointsColor(Color pointsColor) {
    if (pointsColor != null) {
      this.pointsColor = pointsColor;      
    } else {
      throw new NullPointerException();
    }
  }

  /**
   * @return the pointRadius
   */
  public int getPointRadius() {
    return pointRadius;
  }

  /**
   * @param pointRadius the pointRadius to set
   */
  public void setPointRadius(int pointRadius) {
    if (pointRadius <= 0) {
      throw new IllegalArgumentException();
    }
    this.pointRadius = pointRadius;
  }
  
  /**
   * Draw the spline in the graphic element
   * @param g
   */
  public void draw(Graphics g) {
    draw((Graphics2D) g);
  }
  
  /**
   * Draw the spline in the graphic element
   * @param g
   */
  public void draw(Graphics2D g) {
    if (getSpline() != null) {
      g.setColor(getSplineColor());
      g.setStroke(STROKE);
      setLine();
      g.draw(splineLine);
      
      g.setColor(getPointsColor());
      for (Point2D point : getSpline().getPoints()) {
        double x = point.getX();
        double y = point.getY();
        g.fillOval((int) (x - getPointRadius()), (int) (y - getPointRadius()), 2 * getPointRadius(), 2 * getPointRadius());
      }
      
      g.setStroke(new BasicStroke());
      g.setColor(Color.BLACK);      
    }
  }
  
  // PRIVATE METHODS
  
  /**
   * Method that creates a random color
   * @return
   */
  private static Color getRandomColor() {
    Random r = new Random();
    return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
  }
  
  /**
   * Method that sets the line when it is going
   * to be repainted.
   */
  private void setLine() {
    double increment = 0.1;
    splineLine = new Path2D.Double();
    double firstX = getSpline().getFirstPoint().getX();
    double lastX = getSpline().getLastPoint().getX();
    splineLine.moveTo(firstX, getSpline().getValue(firstX));
    for (double x = firstX + increment; Double.compare(x, lastX) <= 0; x += increment) {
      splineLine.lineTo(x, getSpline().getValue(x));
    }
  }
}
