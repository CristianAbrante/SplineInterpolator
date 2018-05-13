/**
 * 
 */
package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import controller.Controller;
import drawable.DrawableSpline;

/**
 * <h2>VisualPanel</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class VisualPanel extends JPanel {
  private DrawableSpline spline;
  
  public VisualPanel(Controller controller, int width, int height) {
    super();
    setSize(width, height);
  }
  
  /**
   * Constructor of VisualPanel
   * 
   * @param spline
   */
  public VisualPanel(DrawableSpline spline, Controller controller, int width, int height) {
    super();
    setSpline(spline);
    setSize(width, height);
  }

  /**
   * @return the spline
   */
  public DrawableSpline getSpline() {
    return spline;
  }

  /**
   * @param spline the spline to set
   */
  public void setSpline(DrawableSpline spline) {
    if (spline != null) {
      this.spline = spline;      
    } else {
      throw new NullPointerException("spline can't be null");
    }
  }

  /**
   * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (getSpline() != null) {
      getSpline().draw(g);      
    }
  }
}
