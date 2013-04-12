import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * 
 * @author Matthew Murray
 * @version 10/26/11
 */
public class ControlPanel extends JPanel implements ActionListener 
{
	javax.swing.Timer timer;
	java.util.ArrayList theSwarm;
	private int reverse;
	protected int totalSpeed;
	protected String text;
	protected int numberOfCreatures;
protected JButton start;
protected JButton stop;
protected JButton exit;
protected JTextField amount;
protected JSlider speed;
protected JPanel topButtons;
protected JPanel upperMid;
protected JPanel lowerMid;
protected JPanel bottom;
Ball b;
	public ControlPanel(Timer t, ArrayList s) {
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(175, 400));
		setLayout(new GridLayout(4,1,15, 60));
		
		timer = t;
		theSwarm = s;
		start = new JButton("Start");
		start.addActionListener(this);
		stop = new JButton("Stop");
		stop.addActionListener(this);
		topButtons = new JPanel();
		topButtons.setBackground(Color.LIGHT_GRAY);
				
		topButtons.setLayout(new FlowLayout(1));
		topButtons.add(start);
		topButtons.add(stop);
		add(topButtons);
		
		amount = new JTextField(11);
		amount.setText("" + theSwarm.size());
		amount.addActionListener(new TypeListener());
		amount.setBorder(BorderFactory.createTitledBorder("How Many Creatures?"));
		upperMid = new JPanel();
		upperMid.setBackground(Color.LIGHT_GRAY);
		upperMid.add(amount);
		add(upperMid);
		
		speed = new JSlider(0,100,50);
		speed.addChangeListener(new SpeedListener());
		speed.setPreferredSize(new Dimension(150, 50));
		speed.setBorder(BorderFactory.createTitledBorder("Speed"));
		lowerMid = new JPanel();
		lowerMid.setLayout(new FlowLayout());
		lowerMid.setBackground(Color.LIGHT_GRAY);
		lowerMid.add(speed);
		
		add(lowerMid);
		
		
		
		exit = new JButton("Exit");
		exit.addActionListener(this);
		bottom = new JPanel();
		bottom.setBackground(Color.LIGHT_GRAY);
		bottom.setLayout(new BorderLayout());
		bottom.add(exit, BorderLayout.SOUTH);
		add(bottom);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equalsIgnoreCase("start")){
			timer.start();
		}
		else if (cmd.equalsIgnoreCase("stop")) {
			timer.stop();
		}
		else if (cmd.equalsIgnoreCase("exit")){
			System.exit(0);
		}
		}

	protected class TypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField test = (JTextField) e.getSource();
			text = test.getText();
			
			try{
				numberOfCreatures = Integer.parseInt(text);
				theSwarm.clear();
				for(int i = 0; i < numberOfCreatures; i++){
					b = new Ball(400,400);
					theSwarm.add(b);
				}
				
			}
			catch (NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, "Enter a Valid Integer!");
			}
			
		}
		
}
	protected class SpeedListener implements ChangeListener{
		
		public void stateChanged(ChangeEvent change){
			
			reverse = speed.getValue();
			totalSpeed = 100 - reverse;
			timer.setDelay(totalSpeed);
			
		}
	}
}

