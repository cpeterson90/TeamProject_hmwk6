import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * @author Guinn
 * @version 10/26/11
 */

public class FlockingPanel extends JPanel implements ActionListener {

	private javax.swing.Timer theTimer;
	private int speed = 30;
	ArrayList<Ball> swarm;
	int howMany = 15;
	int howManyPredators = 5;
	Ball b;
	Predator p;


	public FlockingPanel(javax.swing.Timer t, java.util.ArrayList s) {
		theTimer = t;
		swarm = s;
		setBackground(Color.white);
		setPreferredSize(new Dimension(400, 400));
		for (int i = 0; i < howMany; i++) {
			b = new Ball(400, 400);
			
			swarm.add(b);
		}

		for (int i = 0; i < howManyPredators; i++) {
			p = new Predator(400, 400);
			swarm.add(p);
		}
		theTimer.addActionListener(this); // add an action listener
		theTimer.setDelay(speed);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < swarm.size(); i++) {
			if(swarm.get(i) instanceof Ball){
				Ball b = swarm.get(i);
				b.draw(g);
			}else
				p = (Predator) swarm.get(i);
				p.draw(g);
		}

	}

	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < swarm.size(); i++) {
			Ball b = swarm.get(i);
			b.update(swarm);
		}
		repaint();
	}

}
