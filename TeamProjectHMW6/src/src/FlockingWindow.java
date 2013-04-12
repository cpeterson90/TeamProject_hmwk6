import java.awt.BorderLayout;

import javax.swing.JFrame;

import javax.swing.*;

import java.awt.*;

/**
 * 
 * @author Guinn
 *  @version 10/26/11
 */
public class FlockingWindow extends JFrame {
	javax.swing.Timer timer;
	java.util.ArrayList swarm;
	
	public static Image ant = new ImageIcon("FightingAnt.gif").getImage();
	public static Image wolf = new ImageIcon("wolf.gif").getImage();
	/**
	 * Constructor adds a big panel for displaying the flock
	 * and a smaller panel for controls.
	 * 
	 */
	public FlockingWindow() {
		setTitle("Flocking Behavior");
		timer = new javax.swing.Timer(30, null);
	       swarm = new java.util.ArrayList();
	      FlockingPanel flockPanel = new FlockingPanel(timer, swarm);
	       ControlPanel controls = new ControlPanel(timer, swarm);
		setLayout(new BorderLayout());
		add(flockPanel, BorderLayout.CENTER);
		add(controls, BorderLayout.EAST);
		
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		FlockingWindow window = new FlockingWindow();

	}

}

