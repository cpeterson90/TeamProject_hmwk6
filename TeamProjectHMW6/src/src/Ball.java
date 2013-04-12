import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Ball does this.
 * 
 * @author guinnc
 * @version October 27, 2011
 */
public class Ball implements Serializable {
	/** The Position of the ball. **/
	protected Position position;

	/** The vector of direction of the ball. */
	protected MyVector vector;

	/** The maximum value in the x-coordinate. */
	protected int maxX;
	/** The maximum value in the y-coordinate. */
	protected int maxY;
	/** The width of the ball. **/
	public static final int BALLWIDTH = 10;

	protected Color myColor;

	/**
	 * The constructor.
	 * 
	 * @param maxX1
	 *            The starting x.
	 * @param maxY1
	 *            The starting y.
	 */
	public Ball(final int maxX1, final int maxY1) {
		maxX = maxX1;
		maxY = maxY1;
		int x = (int) (maxX * Math.random());
		int y = (int) (maxY * Math.random());
		position = new Position(x, y);

		// random numbers between -10 and 10
		int changeX = (int) (21 * Math.random()) + -10;
		int changeY = (int) (21 * Math.random()) + -10;
		vector = new MyVector(changeX, changeY);

		myColor = Color.red;


	}

	public Ball(final int maxX1, final int maxY1,
			double a, double b, double c, double d,
			Color col) {
		maxX = maxX1;
		maxY = maxY1;
		position = new Position(a, b);
		vector = new MyVector(c, d);
		myColor = col;

	}

	/**
	 * update changes the position of the ball; it does bounds checking.
	 */
	public void update(ArrayList<Ball> swarm) {

		double x = position.getX();
		double y = position.getY();
		double changeX = vector.getChangeX();
		double changeY = vector.getChangeY();


		MyVector moveTowards = rule1(swarm);
		changeX += moveTowards.getChangeX();
		changeY += moveTowards.getChangeY();
		
		
		MyVector moveAway = rule2(swarm);
		changeX += moveAway.getChangeX();
		changeY += moveAway.getChangeY();
		
		MyVector moveSimilar = rule3(swarm);
		changeX += moveSimilar.getChangeX();
		changeY += moveSimilar.getChangeY();

		if (x + changeX > maxX) {
			changeX = -1 * changeX;
		} else if (x + changeX < 0) {
			changeX = -1 * changeX;
		}
		if (y + changeY > maxY) {
			changeY = -1 * changeY;
		} else if (y + changeY < 0) {
			changeY = -1 * changeY;
		}




		x += changeX;
		y += changeY;

		position.setPosition(x, y);
		vector.set(changeX, changeY);

	}


	private MyVector rule2(ArrayList<Ball> swarm){
		MyVector rule2v = new MyVector(0,0);
		for (int i = 0; i<swarm.size();i++){
			Ball b = swarm.get(i);

			if (b.getClass() == this.getClass()){

				int dist = getDistance(b);

				if (dist < 12){
					double c_x = b.position.getX() - this.position.getX();
					double c_y = b.position.getY() - this.position.getY();
					rule2v.subtract(c_x , c_y);
				}
			}
		}
		return rule2v;
	}

	private MyVector rule3(ArrayList<Ball> swarm){
		MyVector rule2v = new MyVector(0,0);
		int count = 0;
		for (int i = 0; i<swarm.size();i++){
			Ball b = swarm.get(i);

			if (b.getClass() == this.getClass()){

				int dist = getDistance(b);

				if (dist < 100){
					rule2v.add(b.vector);
					count++;
				}
			}
		}
		if (count > 0) {
			// compute average
			double avgCx = rule2v.getChangeX()/count;
			double avgCy = rule2v.getChangeY()/count;

			// find difference
			double diffCx = avgCx - vector.getChangeX();
			double diffCy = avgCy - vector.getChangeY();

			// return a vector 1/8 of that
			return new MyVector(diffCx / 8, diffCy / 8);
		}
		else {
			return new MyVector(0, 0);
		}

	}

	private MyVector rule1(ArrayList<Ball> swarm){
		Position sum = new Position(0,0);
		int count = 0;
		for (int i = 0; i<swarm.size();i++){
			Ball b = swarm.get(i);

			if (b.getClass() == this.getClass()){

				int dist = getDistance(b);

				if (dist < 100){   // limited vision
					sum.add(b.position);
					count++;
				}
			}
		}
		if (count > 0) {
			// compute average
			double avgPx = sum.getX()/count;
			double avgPy = sum.getY()/count;

			// find difference
			double diffPx = avgPx - position.getX();
			double diffPy = avgPy - position.getY();

			// return a vector 1/100 of that
			return new MyVector(diffPx / 100, diffPy / 100);
		}
		else {
			return new MyVector(0, 0);
		}

	}

	/**
	 * Draw the ball.
	 * 
	 * @param g
	 *            The drawing pane's graphic object.
	 */
	public void draw(Graphics g) {
		int x = (int) position.getX();
		int y = (int) position.getY();
		//g.setColor(myColor);
		//g.fillOval(x - 5, y - 5, BALLWIDTH, BALLWIDTH);
		g.drawImage(FlockingWindow.ant, x -5 , y -5, 30, 30, null);
	}

	
	public void draw2(Graphics g) {

		double changeX = vector.getChangeX();
		double changeY = vector.getChangeY();
		double x = position.getX();
		double y = position.getY();
		
		// calculate angle
		double angleInDegrees = 0;
		if (changeX == 0 && changeY == 0)
			angleInDegrees = 0;
		else {
			double angleInRadians = Math.atan2(changeX, -changeY);
			if (angleInRadians < 0)
				angleInRadians += 2 * Math.PI;
			angleInDegrees = Math.toDegrees(angleInRadians);

		}
		AffineTransform affineTransform = new AffineTransform();
		// set the translation to the mid of the component
		affineTransform.setToTranslation(x, y);
		// my image is a little big, make it smaller by 1/4
		affineTransform.scale(0.33, 0.33);
		// rotate with the anchor point as the mid of the image
		affineTransform.rotate(-(360 - Math.toRadians(angleInDegrees)),
				FlockingWindow.ant.getWidth(null) / 2, FlockingWindow.ant.getHeight(null) / 2);


		Graphics2D g2d = (Graphics2D) g; // Create a Java2D version of g.


		g2d.drawImage(FlockingWindow.ant, affineTransform, null);


	}
	/**
	 * Compute the distance to the other Ball object.
	 * 
	 * @param otherBall
	 *            The other ball.
	 * @return The distance.
	 */
	public int getDistance(Ball otherBall) {
		double dX = otherBall.position.getX() - position.getX();
		double dY = otherBall.position.getY() - position.getY();

		return (int) Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
	}

}
