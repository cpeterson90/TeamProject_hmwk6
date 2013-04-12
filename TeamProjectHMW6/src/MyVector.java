
/**
 * MyVector defines the rate of change of the x and y coordinate.  
 * @author guinnc
 * @version Oct 27, 2011
 */
public class MyVector {
	private double changeX; /** The change in the X coordinate. **/
	private double changeY; /** The change in the y coordinate. **/
	
	/**
	 * The constructor sets the starting values.
	 * @param a The starting value for the change in X.
	 * @param b The starting value for the change in Y.
	 */	 
	public MyVector(double a, double b) {
		changeX = a;
		changeY = b;
	}
	
	/**
	 * set changes the values of the change in X and Y.
	 * @param a The new value for the change in X.
	 * @param b The new value for the change in Y.
	 */	 
	public void set(double a, double b) {
		changeX = a;
		changeY = b;
	}
	
	/**
	 * getChangeX is the accessor for changeX.  
	 * @return changeX.  
	 */
	public double getChangeX() {
		return changeX;
	}
	
	/**
	 * getChangeY is the accessor for changeY.
	 * @return changeY;
	 */
	public double getChangeY() {
		return changeY;
	}
	
	/**
	 * add modifies the change in X and change in Y by adding a and b.
	 * @param a The amount to add to changeX.
	 * @param b The amount to add to changeY.
	 */
	public void add(double a, double b) {
		changeX += a;
		changeY += b;
	}
	
	/**
	 * add modifies the change in X and change in Y by adding the values
	 * indicated by the MyVector value, v2. 
	 * @param v2 Contains the amount to add to changeX and changeY.  
	 */
	public void add(MyVector v2) {
		changeX += v2.changeX;
		changeY += v2.changeY;
	}
	
	
	/**
	 * subtract modifies the change in X and change in Y by subtracting a and b.
	 * @param a The amount to subtract from changeX.
	 * @param b The amount to subtract from changeY.
	 */
	public void subtract(double a, double b) {
		changeX -= a;
		changeY -= b;
	}
	
	/**
	 * subtract modifies the change in X and change in Y by subtracting the values
	 * indicated by the MyVector value, v2. 
	 * @param v2 Contains the amount to subtract from changeX and changeY.  
	 */
	public void subtract(MyVector v2) {
		changeX -= v2.changeX;
		changeY -= v2.changeY;
	}
}
