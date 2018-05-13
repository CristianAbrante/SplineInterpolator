/**
 * @author cristian
 * @date 11 may. 2018
 */
package function.polynomial;

import java.util.ArrayList;

import function.Function;

/**
 * <h2>Polynomial</h2>
 * This class represents a polynomial
 * which is a linear function which has
 * coefficients and apply this expression:
 * 
 * f(x) = a1 + a2x + a3x^2 + ... + anx^n
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	11 may. 2018
 * @version 	1.0.0
 */
public class Polynomial extends Function {
  
  private ArrayList<Double> coefficients;
  private final double DEFAULT_VALUE = 0.0;
  
  /**
   * Default constructor of Polynomial
   */
  public Polynomial() {
   ; 
  }
  
  /**
   * Constructor of Polynomial
   *
   * @param coefficients
   */
  public Polynomial(ArrayList<Double> coefficients) {
    super();
    setCoefficients(coefficients);
  }

  /**
   * @return the coefficients
   */
  public ArrayList<Double> getCoefficients() {
    return coefficients;
  }

  /**
   * Method that sets the coefficients of the polynomial.
   * 
   * @param coefficients the coefficients to set
   * @throws NullPointerException if any coefficient or coefficients are null.
   */
  public void setCoefficients(ArrayList<Double> coefficients) 
      throws NullPointerException {
    if (coefficients != null) {
      this.coefficients = coefficients;
      for (Double coeficcient : getCoefficients()) {
        if (coeficcient == null) {
          throw new NullPointerException("any coeffcient can't be null");
        }
      }
    } else {
      throw new NullPointerException("coeffcients can't be null");
    }
  }

  /**
   * @see function.Function#getValue(double)
   */
  @Override
  public double getValue(double x) {
    if (getCoefficients() != null) {
      double imageValue = 0.0;
      double currentExponent = 0.0;
      for (Double coefficient : getCoefficients()) {
        imageValue += coefficient * Math.pow(x, currentExponent);
        currentExponent += 1.0;
      }
      return imageValue;
    } else {
      return DEFAULT_VALUE;
    }
  }

  /**
   * @see function.Function#toString()
   */
  @Override
  public String toString() {
    String result = new String();
    int currentExponent = 0;
    for (Double coefficient : getCoefficients()) {
      result += String.format("+ %.4fx^(%d) ", coefficient, currentExponent);
      currentExponent += 1;
    }
    return result;
  }
}
