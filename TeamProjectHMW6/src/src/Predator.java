import java.awt.Color;
import java.awt.Graphics;


public class Predator extends Ball{

	public Predator (int x, int y){
		super(x, y);
	}
	
	
	/**
	 * Overrides the default Ball drawing routine.
	 */
	public void draw(Graphics g) {
		int x = (int) position.getX();
		int y = (int) position.getY();
		//g.setColor(myColor);
		//g.fillOval(x - 5, y - 5, BALLWIDTH, BALLWIDTH);
		g.drawImage(FlockingWindow.wolf, x -5 , y -5, 30, 30, null);
	}
}
