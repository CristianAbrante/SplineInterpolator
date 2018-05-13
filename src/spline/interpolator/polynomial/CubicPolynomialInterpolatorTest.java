/**
 * 
 */
package spline.interpolator.polynomial;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import function.Function;
import function.polynomial.Polynomial;

import java.awt.geom.Point2D;

/**
 * <h2>PolynomialINterpolatorTest</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		12/05/2018
 * @version 1.0.0
 */
public class CubicPolynomialInterpolatorTest {
  
  private static final double EPSILON = 0.0001;
  CubicPolynomialInterpolator interpolator = new CubicPolynomialInterpolator();
  
  @Test
  public void firstInterpolationTests() {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(1.0, 2.0));
    points.add(new Point2D.Double(2.0, 3.0));
    points.add(new Point2D.Double(5.0, 6.0));
    points.add(new Point2D.Double(8.0, 8.0));
    
    ArrayList<Function> expectedFunc = new ArrayList<Function>();
    double[] coefficients = {1.0, 1.0230, -3.4483E-2, 1.1494E-2};
    expectedFunc.add(new Polynomial(createCoefficients(coefficients)));
    double[] coefficients2 = {1.2043, 7.1648E-1, 1.1877E-1, -1.4049E-2};
    expectedFunc.add(new Polynomial(createCoefficients(coefficients2)));
    double[] coefficients3 = {-1.8289, 2.5364, -2.4521E-1, 1.0217E-2};
    expectedFunc.add(new Polynomial(createCoefficients(coefficients3)));
    
    ArrayList<Function> functions = interpolator.interpolateFuntionsFrom(points);
    checkEqualPolynomialArray(functions, expectedFunc);
  }
  
  @Test
  public void secondInterpolationTests() {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(-8.0, 7.0));
    points.add(new Point2D.Double(3.0, 4.0));
    points.add(new Point2D.Double(5.0, 8.0));
    points.add(new Point2D.Double(7.0, -3.0));
    points.add(new Point2D.Double(90.0, 0.0));
    points.add(new Point2D.Double(91.0, 1.0));
    
    ArrayList<Function> expectedFunc = new ArrayList<Function>();
    double[] coefficients = {-1.9690, 7.8405E-1, 3.5722E-1, 1.4884E-2};
    expectedFunc.add(new Polynomial(createCoefficients(coefficients)));
    double[] coefficients2 = {14.0363, -15.2213, 5.6923, -5.7791E-1};
    expectedFunc.add(new Polynomial(createCoefficients(coefficients2)));
    double[] coefficients3 = {-1.236201E2, 6.73726E1, -1.08264E1, 0.5233};
    expectedFunc.add(new Polynomial(createCoefficients(coefficients3)));
    double[] coefficients4 = {5.6201E1, -9.6936, 1.8302E-1, -9.1385E-4};
    expectedFunc.add(new Polynomial(createCoefficients(coefficients4)));
    double[] coefficients5 = {-1.60952515E4, 5.286881E2, -5.7990, 2.1242E-2};
    expectedFunc.add(new Polynomial(createCoefficients(coefficients5)));
    
    ArrayList<Function> functions = interpolator.interpolateFuntionsFrom(points);
    checkEqualPolynomialArray(functions, expectedFunc);
  }
  
  private ArrayList<Double> createCoefficients(double[] coefficients) {
    ArrayList<Double> coefficientList = new ArrayList<Double>();
    for (int i = 0; i < coefficients.length; ++i) {
      coefficientList.add(coefficients[i]);
    }
    return coefficientList;
  }
  
  private void checkEqualPolynomialArray(ArrayList<Function> a1, ArrayList<Function> a2) {
    assertEquals(a1.size(), a2.size());
    for (int i = 0; i < a1.size(); ++i) {
      checkEqualPolynomials((Polynomial) a1.get(i), (Polynomial) a2.get(i));
    }
  }
  
  private void checkEqualPolynomials(Polynomial p1, Polynomial p2) {
    assertEquals(p1.getCoefficients().size(), 
                 p2.getCoefficients().size());
    for (int i = 0; i < p1.getCoefficients().size(); ++i) {
      assertEquals(p1.getCoefficients().get(i),p2.getCoefficients().get(i), EPSILON);
    }
  }
  
}
