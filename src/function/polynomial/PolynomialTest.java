package function.polynomial;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PolynomialTest {
  
  private final double EPSILON = 10E-6;
  Polynomial polynomial = new Polynomial(); 

  @Test
  public void defaultBehaviour() {
    assertEquals(0.0, polynomial.getValue(0.0), EPSILON);
  }
  
  @Test(expected = NullPointerException.class)
  public void errorNullCoeffcients() {
    polynomial.setCoefficients(null);
  }
  
  @Test(expected = NullPointerException.class)
  public void errorNullCoeffcientElement() {
    ArrayList<Double> coefficients = new ArrayList<Double>();
    for (double i = 0.0; Double.compare(i, 3.0) < 0; i += 1.0) {
      coefficients.add(i);
    }
    coefficients.add(null);
    polynomial.setCoefficients(coefficients);
  }
  
  @Test
  public void valueTest() {
    ArrayList<Double> coefficients = new ArrayList<Double>();
    coefficients.add(-1.0);
    coefficients.add(+3.0);
    coefficients.add(+7.8);
    coefficients.add(+41.0);
    polynomial.setCoefficients(coefficients);
    
    for (double x = 0.0; Double.compare(x, 50.0) < 0; x += 1.0) {
      double expectedValue = 0.0;
      double exponent = 0.0;
      for (Double coefficient : coefficients) {
        expectedValue += coefficient * Math.pow(x, exponent);
        exponent += 1.0;
      }
      System.out.println(expectedValue);
      assertEquals(polynomial.getValue(x), expectedValue, EPSILON);
    }
  }
}
