/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * <h2>PointsManagerTest</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class PointsManagerTest {
  
  ArrayList<Point2D>  points;
  PointsManager       manager;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    manager = new PointsManager();
  }

  @Test
  public void firstTest() {
    points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(1.0, 2.0));
    points.add(new Point2D.Double(5.0, 3.0));
    points.add(new Point2D.Double(9.0, 3.0));
    points.add(new Point2D.Double(15.0, 8.0));
    
    manager.setPoints(points);
    assertTrue(manager.addPoint(new Point2D.Double(12, 9), 1.0));
    assertTrue(manager.addPoint(new Point2D.Double(3, 9), 1.0));
    assertTrue(manager.addPoint(new Point2D.Double(7, 9), 1.0));
    assertTrue(manager.addPoint(new Point2D.Double(21, 9), 1.0));
    
    assertFalse(manager.addPoint(new Point2D.Double(6, 3), 1.0));
  }
}
