import java.awt.*;
import java.util.ArrayList;

/**
 * This ball bounces around.
 * 
 * @author guinnc
 * 
 */
public class Ball {

	/** The Position of the ball. **/
	protected Position position;

	/** The vector of direction of the ball. */
	protected MyVector vector;

	private int maxX;
	private int maxY;

	private Color c;

	/** This sets the width of every ball object. **/
	public static int ballWidth = 10;

	/**
	 * Create a ball. This passes in the maximum window boundaries. **
	 * 
	 * @param maxX
	 *            The maximum X coordinate.
	 * @param maxY
	 *            The maximum Y coordinate.
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
		c = new Color((int) (256 * Math.random()), (int) (256 * Math.random()),
				(int) (256 * Math.random()));
	}

	/**
	 * Update the balls position by adding changeX to X and changeY to Y. Make
	 * sure the ball doesn't go out of bounds. If it does, reverse the direction
	 * of changeX and changeY.
	 */
	public void update(ArrayList<Ball> swarm) {
		double x = position.getX();
		double y = position.getY();
		double changeX = vector.getChangeX();
		double changeY = vector.getChangeY();
		MyVector rule1 = moveTowardsCenter(swarm);
		changeX += rule1.getChangeX();
		changeY += rule1.getChangeY();

		MyVector rule2 = avoidOthers(swarm);
		changeX += rule2.getChangeX();
		changeY += rule2.getChangeY();
		
		
		
		// don't go pass the boundary
		if (x + changeX > maxX)
			changeX = -1 * changeX;
		else if (x + changeX < 0)
			changeX = -1 * changeX;
		if (y + changeY > maxY)
			changeY = -1 * changeY;
		else if (y + changeY < 0)
			changeY = -1 * changeY;

		x += changeX;
		y += changeY;

		position.setPosition(x, y);
		vector.set(changeX, changeY);
	}

	/**
	 * Draw the ball.
	 * 
	 * @param g
	 *            The ball is drawn within this graphics object.
	 * 
	 * 
	 * */
	public void draw(Graphics g) {
		int x = (int) position.getX();
		int y = (int) position.getY();
		g.setColor(c);
		g.fillOval(x - 5, y - 5, ballWidth, ballWidth);
	}

	public MyVector moveTowardsCenter(ArrayList<Ball> swarm) {
		// Vector pcJ
		// FOR EACH BOID b
		// IF b != bJ THEN
		// pcJ = pcJ + b.position
		// END IF
		// END
		// pcJ = pcJ / N-1
		// RETURN (pcJ - bJ.position) / 100
		Position sum = new Position(0, 0);
		int count = 0;
		for (Ball b : swarm) {
			sum.add(b.position);
			count++;
		}

		double averageX = sum.getX() / count;
		double averageY = sum.getY() / count;

		double dx = averageX - position.getX();
		double dy = averageY - position.getY();

		dx /= 100;
		dy /= 100;

		MyVector v = new MyVector(dx, dy);
		return v;
	}

	public MyVector avoidOthers(ArrayList<Ball> swarm) {
		// PROCEDURE rule2(boid bJ)
		//
		// Vector c = 0;
		//
		// FOR EACH BOID b
		// IF b != bJ THEN
		// IF |b.position - bJ.position| < 100 THEN
		// c = c - (b.position - bJ.position)
		// END IF
		// END IF
		// END
		//
		// RETURN c
		//
		// END PROCEDURE

		MyVector v = null;
		for (int i = 0; i < swarm.size(); i++) {
			v = new MyVector(swarm.get(i).position.getX(), swarm.get(i).position.getY());
			if (swarm.get(i).getDistance(swarm.get(i++)) < 50){
				v = new MyVector (swarm.get(i).getDistance(swarm.get(i++)), (swarm.get(i).getDistance(swarm.get(i++)) ));
			}

		}

		return v;
	}

	
	public MyVector matchNearbySpeed (ArrayList<Ball> swarm){
//		PROCEDURE rule3(boid bJ)
//
//		Vector pvJ
//
//		FOR EACH BOID b
//			IF b != bJ THEN
//				pvJ = pvJ + b.velocity
//			END IF
//		END
//
//		pvJ = pvJ / N-1
//
//		RETURN (pvJ - bJ.velocity) / 8
//
//	END PROCEDURE
		int count = 0;
		for (Ball b : swarm) {
			
				
			count++;
		}
		
		MyVector v = null;
		return v;
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
