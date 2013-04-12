import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class FlockingPanel extends JPanel {

	/** Panel width. **/
	public static int width = 400;
	/** Panel height. **/
	public static int height = 400;

	private static int numBalls = 15;
	private static ArrayList<Ball> theBall;
	private static javax.swing.Timer timer;
	private int speed = 30;

	// Create the flockingPanel, ball, timer and start the timer.
	public FlockingPanel() {
		setBackground(Color.white);

		theBall = new ArrayList<Ball>();

		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createLineBorder(Color.black));

		for (int i = 0; i < numBalls; i++)
			theBall.add(new Ball(width, height));

		timer = new javax.swing.Timer(speed, new TimeListener());
		timer.start();
	}

	/**
	 * If repainting occurs, repaint the ball.
	 * 
	 * @args g Repainting is within this graphics object.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Ball s : theBall)
			s.draw(g);
	}

	/**
	 * The listener for the time updates the ball each time.
	 * 
	 * @author guinnc
	 * 
	 */
	private class TimeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// theBall.update();
			for (Ball s : theBall){
				s.update(theBall);
				repaint();
			}
	}}

	protected static void stopTimer() {
		timer.stop();
	}
	public static void startTimer() {
		timer.start();
	}
	public static void setDelay(int time){
		timer.setDelay(time);
	}
	protected static void setBallAmount(int numberOfBalls) {
		theBall.clear();
		numBalls = numberOfBalls;
		for (int i = 0; i < numBalls; i++)
			theBall.add(new Ball(width, height));
	}

	
}
