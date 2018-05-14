/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.SwingUtilities;

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
public class Controller implements ActionListener, MouseListener, MouseMotionListener {
  /**
   * Constants defined for width height and
   * points radius respectively.
   */
  private final int WIDTH = 1000;
  private final int HEIGHT = 700;
  private final double POINT_RADIUS = 10;
  
  private int numberOfPoints = 8;
  
  /**
   * Manager of the points, interpolator
   * and spline.
   */
  private PointsManager        manager;
  private Interpolator        interpolator;
  private Spline              spline;
  
  /**
   * Panels and visual elements
   */
  private DrawableSpline      drawableSpline;
  private VisualPanel         visualPanel;
  private SettingsPanel       settingsPanel;
  private MainFrame           frame;
  
  private boolean             pointSelected;
  private int                 currentSelectedPoint;
  
  public Controller() {
    manager = new PointsManager();
    interpolator = new CubicPolynomialInterpolator();
    drawableSpline = new DrawableSpline(spline);
    drawableSpline.setPointRadius((int) POINT_RADIUS);
    
    visualPanel = new VisualPanel(this, WIDTH, HEIGHT);
    visualPanel.setSpline(drawableSpline);
    visualPanel.addMouseListener(this);
    visualPanel.addMouseMotionListener(this);
    settingsPanel = new SettingsPanel(this);
    frame = new MainFrame("Spline Interpolator", settingsPanel, visualPanel);
  }

  /** (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
    case SettingsPanel.GENERATE: {
      try {
        numberOfPoints = settingsPanel.getNumberOfPoints();
        if (numberOfPoints >= 2) {
          manager.setRandomPoints(numberOfPoints, POINT_RADIUS, 0, WIDTH, 0, HEIGHT);
          spline = new Spline(interpolator, manager.getPoints());
          drawableSpline.setSpline(spline);
          frame.repaint();
        }        
      }
      catch(NumberFormatException nfe) {
        ;
      }
      break;
    }
    case SettingsPanel.CLEAR: {
      if (spline != null) {
        manager.clear();
        drawableSpline.setSpline(null);
        frame.repaint();
      }
    }
    }
  }

  /**
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    if (SwingUtilities.isRightMouseButton(e)) {
      if (spline != null) {
        manager.addPoint(new Point2D.Double(e.getX(), e.getY()), POINT_RADIUS);
        spline = new Spline(interpolator, manager.getPoints());
        drawableSpline.setSpline(spline);
        frame.repaint();      
      }      
    }
  }

  /**
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
      Point2D point = new Point2D.Double(e.getX(), e.getY());
      currentSelectedPoint = manager.getIndexOf(point, POINT_RADIUS);
      if (currentSelectedPoint != -1) {
        pointSelected = true;
      }      
  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    pointSelected = false;
  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    if (spline != null && pointSelected) {
      if (isInBounds(e.getX(), e.getY(), currentSelectedPoint)) {
        manager.getPoint(currentSelectedPoint).setLocation(new Point2D.Double(e.getX(), e.getY()));
        spline.setPoints(manager.getPoints());
        frame.repaint();        
      }
    }
  }

  /* (non-Javadoc)
   * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
  }
  
  private boolean isInBounds(int x, int y, int index) {
    double xdouble = (double) x;
    if (index == 0) {
      return Double.compare(xdouble, manager.getPoint(index + 1).getX()) < 0;
    }
    if (index == manager.getPoints().size() - 1) {
      return Double.compare(xdouble, manager.getPoint(index - 1).getX()) > 0;
    }
    return Double.compare(xdouble, manager.getPoint(index - 1).getX()) > 0
        && Double.compare(xdouble, manager.getPoint(index + 1).getX()) < 0;
  }
}
