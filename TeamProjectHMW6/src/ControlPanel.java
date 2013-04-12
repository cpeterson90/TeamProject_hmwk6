import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel implements ActionListener {

	private JButton start, stop, exit;
	private JTextField howMany;
	private JSlider speed;
	private JPanel buttons, spacePanel, spacePanel2;// spacer panel, not sure if
													// this is the best way to
													// space, but seems to work.
	
	private static String oldNum = "0";
	
	public ControlPanel() {
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(250, 400));
		setLayout(new GridLayout(7, 1));

		// buttons panel{start, stop}
		buttons = new JPanel();
		start = new JButton("Start");
		start.addActionListener(this);
		stop = new JButton("Stop");
		stop.addActionListener(this);
		buttons.add(start);
		buttons.add(stop);
		add(buttons);

		spacePanel = new JPanel();
		add(spacePanel);

		// how many creatures
		howMany = new JTextField("0", 15);
		howMany.addActionListener(this);
		howMany.setBorder(BorderFactory
				.createTitledBorder("How many creatures"));
		add(howMany);

		// spacePanel2 = new JPanel();
		add(spacePanel);

		// slider
		speed = new JSlider(JSlider.HORIZONTAL);
		speed.setBorder(BorderFactory.createTitledBorder("Speed"));
		speed.addChangeListener(new SliderListener());
		speed.setMajorTickSpacing(20);
	    speed.setMinorTickSpacing(5);
	    speed.setPaintTicks(true);
	    speed.setPaintLabels(true);
		add(speed);

		add(spacePanel);

		// exit button
		exit = new JButton("Exit");
		exit.addActionListener(this);
		add(exit, BorderLayout.SOUTH);

	}// constructor

	// Check for number
	public boolean isNumeric(String str) {
		try {
			int num = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}// end isNumeric

	@Override
	public void actionPerformed(ActionEvent e) {
		String function = e.getActionCommand();

		if (function.equals("Exit")) {
			System.exit(0);
		}// if
		else if (function.equals("Start")) {
			// start function
			FlockingPanel.startTimer();

		}// if
		else if (function.equals("Stop")) {
			// stop function
			FlockingPanel.stopTimer();
	
		}// if

		
		String textField = howMany.getText();
		if (!textField.equals(oldNum))
			if (isNumeric(textField)) {
				oldNum = textField;
				System.out.println("isNumeric");
				int num = Integer.parseInt(textField);
				FlockingPanel.setBallAmount(num);

			} else {// we did not get a number let user know
				howMany.setText("Enter numbers.");
			}

	}// actionPerformed

	// private inner class to handle change events
	// for the slider.
	private class SliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			// react to slide events
			  JSlider source = (JSlider)e.getSource();
		        if (!source.getValueIsAdjusting()) {
		            int fps = (int)source.getValue();
		            FlockingPanel.setDelay(fps);
		        }
		}
	}

}// controlPanel class
