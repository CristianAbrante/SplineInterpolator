/**
 * @author cristian
 * @date 11 may. 2018
 */
package function;

/**
 * <h2>Function</h2>
 * This abstract class represents a 
 * function (f) in the domain:
 * 
 * f: R -> R
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 	11 may. 2018
 * @version 	1.0.0
 */
public abstract class Function {
  
  /**
   * Returns the image value of the 
   * function after applying it.
   * 
   * @param x   the object value.
   * @return    the image value.
   */
  public abstract double getValue(double x);

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public abstract String toString();
}
