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
  private Color   color;
  private final Stroke STROKE = new BasicStroke(3f);
  private Path2D  splineLine;
  
  /**
   * Constructor of DrawableSpline
   * 
   * @param spline
   * @param color
   */
  public DrawableSpline(Spline spline, Color color) {
    setSpline(spline);
    setColor(color);
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
    if (spline != null) {
      this.spline = spline;      
    } else {
      throw new NullPointerException("spline can't be null.");
    }
  }
  
  /**
   * @return the color
   */
  public Color getColor() {
    return color;
  }
  
  /**
   * @param color the color to set
   */
  public void setColor(Color color) {
    if (color != null) {
      this.color = color;      
    } else {
      throw new NullPointerException("color can't be null.");
    }
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
    g.setColor(getColor());
    g.setStroke(STROKE);
    setLine();
    g.draw(splineLine);
    g.setStroke(new BasicStroke());
    g.setColor(Color.BLACK);
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
    double increment = 1.0;
    splineLine = new Path2D.Double();
    double firstX = getSpline().getFirstPoint().getX();
    double lastX = getSpline().getLastPoint().getX();
    splineLine.moveTo(firstX, getSpline().getValue(firstX));
    for (double x = firstX + increment; Double.compare(x, lastX) <= 0; x += increment) {
      splineLine.lineTo(x, getSpline().getValue(x));
    }
  }
}
