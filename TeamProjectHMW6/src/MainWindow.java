import java.awt.BorderLayout;

import javax.swing.JFrame;


public class MainWindow extends JFrame {

	/**
	 * @param args
	 */
	public MainWindow(){
		
		setTitle("Flocking Behavior");
		setSize(400, 400);
		FlockingPanel flockPanel = new FlockingPanel();
		ControlPanel controls = new ControlPanel();
		setLayout(new BorderLayout());
		add(flockPanel, BorderLayout.CENTER);
		add(controls, BorderLayout.EAST);
		
//		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		MainWindow mw = new MainWindow();

	}

}
