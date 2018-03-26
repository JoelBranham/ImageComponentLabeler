package branham.joel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Panel extends JPanel implements ActionListener{

	private JScrollPane scrollPane;
	private JTextArea messageBox;

	private JPanel startGridPanel;
	private JPanel resultGridPanel;

	private JPanel middleTopPanel;

	private JPanel topPanel;

	private JPanel topNumberPanel;
	private JPanel numberSpacePanel;
	private JPanel horizontalNumberPanel1;
	private JPanel horizontalNumberPanel2;
	private JLabel[] horizontalNumbers1;
	private JLabel[] horizontalNumbers2;

	private JPanel verticalNumberPanel;
	private JLabel[] verticalNumbers;

	private JPanel gridTitlesPanel;
	private JPanel startTextPanel;
	private JPanel spacePanel;
	private JPanel resultTextPanel;
	private JLabel startText;
	private JLabel resultText;

	private JButton[][] startGridLabels;
	private JButton[][] resultGridLabels;

	private Grid grid;

	private Color[] colors = {Color.green, Color.magenta, Color.orange, Color.pink, Color.red, Color.yellow};


	public Panel(Grid grid){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.grid = grid;

		Square[][] startGrid = grid.getStartGrid();
		Square[][] resultGrid = grid.getResultGrid();


		topNumberPanel = new JPanel();
		horizontalNumberPanel1 = new JPanel(new GridLayout(0, startGrid.length));
		horizontalNumbers1 = new JLabel[startGrid.length];
		for (int i = 0; i < horizontalNumbers1.length; i++){
			horizontalNumbers1[i] = new JLabel();
			horizontalNumbers1[i].setPreferredSize(new Dimension(40, 9));
			horizontalNumberPanel1.add(horizontalNumbers1[i]);
		}
		topNumberPanel.add(horizontalNumberPanel1);
		numberSpacePanel = new JPanel();
		numberSpacePanel.setPreferredSize(new Dimension(15,9));
		topNumberPanel.add(numberSpacePanel);
		horizontalNumberPanel2 = new JPanel(new GridLayout(0, startGrid.length));
		horizontalNumbers2 = new JLabel[startGrid.length];
		for (int i = 0; i < horizontalNumbers2.length; i++){
			horizontalNumbers2[i] = new JLabel(i + "");
			horizontalNumbers2[i].setHorizontalAlignment(SwingConstants.CENTER);
			horizontalNumbers2[i].setPreferredSize(new Dimension(40, 9));
			horizontalNumberPanel1.add(horizontalNumbers2[i]);
			horizontalNumberPanel2.add(horizontalNumbers2[i]);
		}
		topNumberPanel.add(horizontalNumberPanel2);
		add(topNumberPanel);

		topPanel = new JPanel();
		startGridPanel = new JPanel();
		startGridPanel.setLayout(new GridLayout(startGrid.length, startGrid[0].length));
		startGridLabels = new JButton[startGrid.length][startGrid[0].length];

		for (int i = 0; i < startGrid.length; i++){
			for (int j = 0; j < startGrid[0].length; j++){
				startGridLabels[i][j] = new JButton();
				startGridLabels[i][j].addActionListener(this);
				startGridLabels[i][j].setOpaque(true);
				startGridLabels[i][j].setPreferredSize(new Dimension(40, 40));
				startGridLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				startGridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
				startGridPanel.add(this.startGridLabels[i][j]);
			}
		}
		topPanel.add(startGridPanel);

		verticalNumberPanel = new JPanel();
		verticalNumberPanel.setLayout(new GridLayout(startGrid.length, 0));
		verticalNumbers = new JLabel[startGrid.length];
		for (int i = 0; i < verticalNumbers.length; i++){
			verticalNumbers[i] = new JLabel(i + "");
			verticalNumbers[i].setPreferredSize(new Dimension(20, 40));
			verticalNumbers[i].setHorizontalAlignment(SwingConstants.CENTER);
			verticalNumberPanel.add(verticalNumbers[i]);
		}
		topPanel.add(verticalNumberPanel);

		resultGridPanel = new JPanel();
		resultGridPanel.setLayout(new GridLayout(resultGrid.length, resultGrid[0].length));
		resultGridLabels = new JButton[resultGrid.length][resultGrid[0].length];

		for (int i = 0; i < resultGrid.length; i++){
			for (int j = 0; j < resultGrid[0].length; j++){
				int tileNum = resultGrid[i][j].getNum();
				resultGridLabels[i][j] = new JButton();
				resultGridLabels[i][j].setOpaque(true);
				resultGridLabels[i][j].setPreferredSize(new Dimension(40, 40));
				resultGridLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				resultGridLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
				resultGridPanel.add(resultGridLabels[i][j]);
			}
		}
		topPanel.add(resultGridPanel);
		add(topPanel);

		gridTitlesPanel = new JPanel();
		startTextPanel = new JPanel();
		startText = new JLabel("INPUT");
		startText.setFont(new Font("Serif", Font.BOLD, 15));
		startText.setPreferredSize(new Dimension(100, 15));
		startTextPanel.add(startText);
		gridTitlesPanel.add(startTextPanel);
		spacePanel = new JPanel();
		spacePanel.setPreferredSize(new Dimension(250, 1));
		gridTitlesPanel.add(spacePanel);
		resultTextPanel = new JPanel();
		resultText = new JLabel("OUTPUT");
		resultText.setFont(new Font("Serif", Font.BOLD, 15));
		resultText.setPreferredSize(new Dimension(100, 15));
		resultTextPanel.add(resultText);
		gridTitlesPanel.add(resultTextPanel);
		add(gridTitlesPanel);

		messageBox = new JTextArea("List of Points: ");
		messageBox.setEditable(false);
		updateMessageBoxText();

		scrollPane = new JScrollPane (messageBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(500, 100));
		scrollPane.setMinimumSize(new Dimension(500, 100));
		add(scrollPane);

		updateGrids();
	}

	public void actionPerformed(ActionEvent event){
		Square[][] startGrid = grid.getStartGrid();
		for (int i = 0; i < startGridLabels.length; i++){
			for (int j = 0; j < startGridLabels[0].length; j++){
				if (startGridLabels[i][j] == event.getSource()){
					if (startGrid[i][j].getNum() == 0){
						startGrid[i][j].setNum(1);
					}
					else if (startGrid[i][j].getNum() == 1){
						startGrid[i][j].setNum(0);
					}
					grid.setStartGrid(startGrid);
					grid.updateComponents();
					updateMessageBoxText();
					updateGrids();
					scrollPane.revalidate();
					scrollPane.repaint();
				}
			}
		}
	}

	public void updateGrids(){

		Square[][] startGrid = grid.getStartGrid();
		Square[][] resultGrid = grid.getResultGrid();

		for (int i = 0; i < startGrid.length; i++){
			for (int j = 0; j < startGrid[0].length; j++){
				if (startGrid[i][j].getNum() == 0){
					this.startGridLabels[i][j].setText("");
					this.startGridLabels[i][j].setBackground(Color.black);
				}
				else{
					this.startGridLabels[i][j].setText(startGrid[i][j].getNum() + "");
					this.startGridLabels[i][j].setBackground(Color.cyan);
				}
			}
		}

		for (int i = 0; i < resultGrid.length; i++){
			for (int j = 0; j < resultGrid[0].length; j++){
				if (resultGrid[i][j].getNum() == 0){
					this.resultGridLabels[i][j].setText("");
					this.resultGridLabels[i][j].setBackground(Color.black);
				}
				else {
					this.resultGridLabels[i][j].setText(resultGrid[i][j].getNum() + "");
					this.resultGridLabels[i][j].setBackground(colors[resultGrid[i][j].getNum() % colors.length]);
				}
			}
		}
	}

	private void updateMessageBoxText(){
		String message = "";
		Queue<Queue<Point>> queueOfComponentPoints = grid.getPoints();
		int componentNum = 2;
		while (queueOfComponentPoints.size() > 0){
			Queue<Point> singleComponentPoints = queueOfComponentPoints.dequeue();
			message += (" " + componentNum + ": ");
			while (singleComponentPoints.size() > 0){
				Point p = singleComponentPoints.dequeue();
				message += ( "(" + (int)p.getX() + "," + (int)p.getY() + ")" );
				if (singleComponentPoints.size() > 0){
					message += ", ";
				}
			}
			message += "\n";
			componentNum++;
		}
		messageBox.setText(message);
	}
	
}