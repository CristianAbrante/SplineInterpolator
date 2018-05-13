/**
 * 
 */
package controller;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import sun.security.util.ECUtil;

/**
 * <h2>PointsManager</h2>
 * This class manage a sorted
 * list of points by the x coordinates.
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		13/05/2018
 * @version 1.0.0
 */
public class PointsManager {
  private ArrayList<Point2D>  points;
  
  /**
   * Constructor of PointsManager
   */
  public PointsManager() {
    this(new ArrayList<Point2D>());
  }
  
  /**
   * Constructor of PointsManager
   * 
   * @param points for managing
   */
  public PointsManager(ArrayList<Point2D> points) {
    setPoints(points);
  }
  
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
    if (arePointsSorted(points)) {
      this.points = points;      
    } else {
      throw new IllegalArgumentException("points are not in range.");
    }
  }
  
  /**
   * Returns the point at specified index.
   * 
   * @param index
   * @return 
   */
  public Point2D getPoint(int index) {
    if (index >= 0 && index < getPoints().size()) {
      return getPoints().get(index);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }
  
  /**
   * Deletes the point at speccified index.
   * 
   * @param index
   */
  public void deletePoint(int index) {
    if (index >= 0 && index < getPoints().size()) {
      getPoints().remove(index);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }
  
  /**
   * Add the point sorted by x, with specified radius
   * 
   * @param point to insert
   * @param radius that the points should respect.
   * @return true if point was inserted and false otherwise.
   */
  public boolean addPoint(Point2D point, double radius) {
    if (point != null) {
      boolean pointInserted = false;
      int i = 0;
      while(!pointInserted && i < getPoints().size()) {
        double distance = euclideanDistance(getPoint(i), point);
        if (Double.compare(Math.abs(distance), 2 * radius) > 0) {
          // point.getX < points.get(i).getX
          if (Double.compare(point.getX(), getPoint(i).getX()) < 0) {
            pointInserted = true;
            getPoints().add(i, point);
          }
        } else {
          return false;
        }
        i++;
      }
      if (!pointInserted) {
        getPoints().add(point);
      }
      return true;
    } else {
      throw new NullPointerException("point can't be null");
    }
  }
  
  /**
   * Creates a set of random points sorted
   * 
   * @param numberOfPoints
   * @param radius
   * @param minX
   * @param maxX
   * @param minY
   * @param maxY
   */
  public void setRandomPoints(int numberOfPoints, double radius, double minX, double maxX, double minY, double maxY) {
    setPoints(new ArrayList<Point2D>());
    Random r = new Random();
    int insertedPoints = 0;
    while (insertedPoints < numberOfPoints) {
      double xValue = minX + (maxX - minX) * r.nextDouble();
      double yValue = minY + (maxY - minY) * r.nextDouble();
      if (addPoint(new Point2D.Double(xValue, yValue), radius)) {
        insertedPoints += 1;
      }
    }
  }
  
  /**
   * Returns the index of the point that is inside the
   * radius.
   * 
   * @param point
   * @param radius
   * @return the index and -1 if no one was found.
   */
  public int getIndexOf(Point2D point, double radius) {
    for (int i = 0; i < getPoints().size(); ++i) {
      double distance = euclideanDistance(getPoint(i), point);
      if (Double.compare(distance, radius) <= 0) {
        return i;
      }
    }
    return -1;
  }
  
  /**
   * Returns the euclidean distance of two points.
   * 
   * @param p1
   * @param p2
   * @return
   */
  public static double euclideanDistance(Point2D p1, Point2D p2) {
    double first = Math.pow(p2.getX() - p1.getX(), 2.0);
    double second = Math.pow(p2.getY() - p1.getY(), 2.0);
    return Math.sqrt(first + second);
  }
  
  /**
   * Return true if points are sorted.
   * 
   * @param points
   * @return
   */
  public static boolean arePointsSorted(ArrayList<Point2D> points) {
    if (points != null) {
      for (int i = 0; i < points.size() - 1; ++i) {
        Point2D p1 = points.get(i);
        Point2D p2 = points.get(i + 1);
        if (p1 != null && p2 != null) {
          if (Double.compare(p1.getX(), p2.getX()) >= 0) {
            return false;
          }
        } else {
          return false;
        }
      }
    } else {
      return false;
    }
    return true;
  }
}
