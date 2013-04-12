
/**
 * Represents the position in (x,y) of an object.
 * @author guinnc
 * @version October 27, 2011
 */
public class Position {
	private double x; /** The x coordinate. */
	private double y; /** The y coordinate. */
	
	/**
	 * The constructor sets the original value of x to a and the original
	 * value of y to b.
	 * @param a The starting value for x.
	 * @param b The starting value for y.
	 */
	public Position(double a, double b) {
		x = a;
		y = b;
	}
	
	/**
	 * setPosition changes the values of x and y to a and b, respectively.
	 * @param a The new value for x.
	 * @param b The new value for y.
	 */
	public void setPosition(double a, double b) {
		x = a;
		y = b;
	}
	
	/**
	 * getX is the accessor for x.
	 * @return x.
	 */
	public double getX() {
		return x;
	}
	
	/** 
	 * getY is the accessor for y.
	 * @return y.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * add increments x and y by a and b, respectively.
	 * @param a The amount to add to x.
	 * @param b The amount to add to y.
	 */
	public void add(double a, double b) {
		x += a;
		y += b;
	}
	
	/**
	 * add increments x and y by the amounts indicated by p2.
	 * @param p2 The x value of p2 will be added to x.  The y value of p2 will 
	 * be added to y.
	 */
	public void add(Position p2) {
		x += p2.x;
		y += p2.y;
	}
	
	/**
	 * add increments x and y by the amounts indicated by v2.
	 * @param p2 The changeX value of p2 will be added to x.  The changeY value of p2 will 
	 * be added to y.
	 */	
	public void add(MyVector v2) {
		x += v2.getChangeX();
		y += v2.getChangeY();
		
	}
	
	
	/**
	 * subtract decrements x and y by a and b, respectively.
	 * @param a The amount to subtract from x.
	 * @param b The amount to subtract from y.
	 */
	public void subtract(double a, double b) {
		x -= a;
		y -= b;
	}
	
	/**
	 * subtract decrements x and y by the amounts indicated by p2.
	 * @param p2 The x value of p2 will be subtracted from x.  The y value of p2 will 
	 * be subtracted from y.
	 */
	public void subtract(Position p2) {
		x -= p2.x;
		y -= p2.y;
	}
	
	/**
	 * add decrements x and y by the amounts indicated by v2.
	 * @param p2 The changeX value of p2 will be subtracted from x.  The changeY value of p2 will 
	 * be subtracted from y.
	 */	
	public void subtract(MyVector v2) {
		x -= v2.getChangeX();
		y -= v2.getChangeY();
		
	}
}
