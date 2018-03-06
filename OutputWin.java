package astar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class OutputWin extends JFrame {
	JButton[] tileArray;
	AStar astar;
	int size;
	int blankX;
	int blankY;
	
	LinkedList<Direction> moves;
	
	GridLayout grid;
	BorderLayout border;
	FlowLayout flow;
	
	JPanel panelMain;
	JPanel panelMoves;
	JPanel panelExtra;
	
	JButton nextStep;
	JLabel visitedStatesLabel;
	JLabel searchDepthLabel;
	JTextArea movesLabel;
	
	JScrollPane scroll;
	
    public OutputWin(AStar as) {
		super( "N puzzle solver" );
		
		astar = as;
		size = as.root.size;
		
		moves = new LinkedList<>(astar.movesList);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelMain = new JPanel();
		panelMoves = new JPanel();
		panelExtra = new JPanel();
		
		setSize(800, 900);
		setLocationRelativeTo(null);
		
		grid = new GridLayout(size, size);
		border = new BorderLayout();
		flow = new FlowLayout();
		
		panelMain.setLayout(grid);
		panelExtra.setLayout(flow);
		panelMoves.setLayout(flow);
		
		nextStep = new JButton("Next step");
		
		nextStep.addActionListener(e -> {
			showSteps();
		});
		
		
		visitedStatesLabel = new JLabel("Visited states: " + astar.getVisitedStates() + "     ");
		searchDepthLabel = new JLabel("Search depth: " + astar.getSearchDepth() + "     ");
		movesLabel = new JTextArea(moves.toString());
		movesLabel.setEditable(false);
		
		scroll = new JScrollPane (movesLabel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setMinimumSize(new Dimension(800, 35));
		scroll.setPreferredSize(new Dimension(800, 35));

		
		panelExtra.add(visitedStatesLabel);
		panelExtra.add(searchDepthLabel);
		panelExtra.add(nextStep);
		
		panelMoves.add(scroll);
		
		setLayout(border);		
		
		tileArray = new JButton[size * size];

		for(int i=0; i < (size * size); i++) {
			tileArray[i] = new JButton("" + astar.root.pBoard[i]);
			tileArray[i].setEnabled(false);
			tileArray[i].setFont(new Font("Arial", Font.PLAIN, 40));
			
			if( astar.root.pBoard[i] == 0) {
				tileArray[i].setBackground(Color.BLACK);
				if(!moves.isEmpty()) {
					tileArray[i].setText(moves.getFirst().toString());
				}
				blankY = i / size;
				blankX = i % size;
			}
			
			panelMain.add(tileArray[i]);
		}

		add(panelMain, BorderLayout.CENTER);
		add(panelMoves, BorderLayout.NORTH);
		add(panelExtra, BorderLayout.SOUTH);
		setVisible(true);
    }
    
    public void showSteps() {
    	String tempText = new String();
    	Direction dir;
    	Direction nextDir;
    	while((dir = moves.pollFirst()) != null) {
    		if((nextDir = moves.peekFirst()) == null) {
    			nextDir = Direction.NONE;
    		}
    		
    		if( dir.equals(Direction.UP) ) {
    			tempText = tileArray[(blankY-1)*size+ blankX].getText();
    			tileArray[(blankY-1)*size+ blankX].setText(nextDir.toString());
    			tileArray[(blankY-1)*size+ blankX].setBackground(Color.BLACK);
    			tileArray[blankY*size+ blankX].setText(tempText);
    			tileArray[blankY*size+ blankX].setBackground(null);
    			blankY = blankY -1;
    		}
    		else if( dir.equals(Direction.DOWN) ) {
    			tempText = tileArray[(blankY+1)*size+ blankX].getText();
    			tileArray[(blankY+1)*size+ blankX].setText(nextDir.toString());
    			tileArray[(blankY+1)*size+ blankX].setBackground(Color.BLACK);
    			tileArray[blankY*size+ blankX].setText(tempText);
    			tileArray[blankY*size+ blankX].setBackground(null);
    			blankY = blankY +1;
    		}
    		else if( dir.equals(Direction.RIGHT) ) {
    			tempText = tileArray[blankY*size+ blankX + 1].getText();
    			tileArray[blankY*size+ blankX + 1].setText(nextDir.toString());
    			tileArray[blankY*size+ blankX + 1].setBackground(Color.BLACK);
    			tileArray[blankY*size+ blankX].setText(tempText);
    			tileArray[blankY*size+ blankX].setBackground(null);
    			blankX = blankX +1;
    		}
    		else if( dir.equals(Direction.LEFT) ) {
    			tempText = tileArray[blankY*size+ blankX - 1].getText();
    			tileArray[blankY*size+ blankX - 1].setText(nextDir.toString());
    			tileArray[blankY*size+ blankX - 1].setBackground(Color.BLACK);
    			tileArray[blankY*size+ blankX].setText(tempText);
    			tileArray[blankY*size+ blankX].setBackground(null);
    			blankX = blankX -1;
    		}
    		break;	
    	}
    }
}
