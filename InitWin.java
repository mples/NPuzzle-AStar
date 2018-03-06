package astar;

import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InitWin extends JFrame {
	JPanel panel;
	JPanel fileNamePanel;
	JPanel timePanel;
	JPanel buttonPanel;
	JLabel fileNameLabel;
	JTextField fileNameField;
	JLabel timeLabel;
	JTextField timeField;
	JButton startButton;
	FlowLayout flow;
	BoxLayout box;
	
	String fileName;
	long time;
	
	public InitWin() {
		super( "N puzzle solver" );
		setSize(600, 180);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		
		flow = new FlowLayout();
		box = new BoxLayout(panel, BoxLayout.Y_AXIS);		
		
		fileNamePanel = new JPanel();
		fileNamePanel.setLayout(flow);
		timePanel = new JPanel();
		timePanel.setLayout(flow);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(flow);
		
		fileNameLabel = new JLabel("Input file: ");
		fileNameField = new JTextField(30);  
		fileNameField.setText("/input/puzzle.txt");		

		fileNamePanel.add(fileNameLabel);
		fileNamePanel.add(fileNameField);
		
		timeLabel = new JLabel("Stop time: ");
		timeField = new JTextField(30);
		timeField.setText("1000000000000000");
		timePanel.add(timeLabel);
		timePanel.add(timeField);
		
		startButton = new JButton("Start");
		startButton.addActionListener(e -> {
			startSolver();
		});
		buttonPanel.add(startButton);
		
		panel.add(fileNamePanel);
		panel.add(timePanel);
		panel.add(buttonPanel);
		
		panel.setLayout(box);
		
		add(panel);
		
		setVisible(true);
	}
	
//	public void startSolver() {
//		fileName = fileNameField.getText();
//		try {
//			time = Long.parseLong(timeField.getText());
//		}
//		catch(NumberFormatException e) {
//			time = Long.MAX_VALUE;
//		}
//		try {
//			startButton.setText("Wait...");
//			
//			AStar as = new AStar(fileName, time);		
//			if(as.isSolvable() == false) {
//				JOptionPane.showMessageDialog(this, "Given state is unsolvable", "N puzzle solver", JOptionPane.ERROR_MESSAGE);
//				System.exit(0);
//			}
//	        as.solve();
//	        new OutputWin(as);
//	        setVisible(false);	
//		}
//        catch(IOException e) {
//        	System.out.println("Wrong file name");
//        }
//	}
	
	public void startSolver() {
		fileName = fileNameField.getText();
		try {
			time = Long.parseLong(timeField.getText());
		}
		catch(NumberFormatException e) {
			time = Long.MAX_VALUE;
		}
		startButton.setText("Wait...");
		startButton.setEnabled(false);
		
		Runnable computation = new Runnable(){
			public void run(){
				try {
					AStar as = new AStar(fileName, time);		
					if(as.isSolvable() == false) {
						JOptionPane.showMessageDialog(null, "Given state is unsolvable", "N puzzle solver", JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}
			        as.solve();
			        new OutputWin(as);
			        setVisible(false);	
				}
		        catch(IOException e) {
		        	System.out.println("Wrong file name");
		        }
			}
		};
		Thread thread = new Thread(computation);
		thread.start();
		

	}
}
