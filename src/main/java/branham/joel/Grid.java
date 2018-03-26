package branham.joel;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import branham.joel.Square;
import branham.joel.Queue;

public class Grid{
	private Square[][] startGrid;
	private Square[][] grid;
	private int componentNumber;
	private Queue<Queue<Point>> queueOfComponentPoints;

	public Grid(File file){
		try {
			Scanner input = new Scanner(file);
			int rowNumber = 0;
			String firstLine;
			if ((firstLine = input.nextLine()) != null){
				String[] lineArray = firstLine.split(" ");
				startGrid = new Square[lineArray.length][lineArray.length];
				for (int i = 0; i < lineArray.length; i++){
					startGrid[rowNumber][i] = new Square(Integer.parseInt(lineArray[i]));
				}
				rowNumber++;
				while (input.hasNextLine()){
					lineArray = input.nextLine().split(" ");
					for (int i = 0; i < lineArray.length; i++){
						startGrid[rowNumber][i] = new Square(Integer.parseInt(lineArray[i]));
					}
					rowNumber++;
				}
				input.close();
				updateComponents();
			}
		}
		catch(FileNotFoundException fe){}
	}

	public void updateComponents(){
		grid = new Square[startGrid.length][startGrid[0].length];
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				grid[i][j] = new Square(startGrid[i][j].getNum());
			}
		}
		queueOfComponentPoints = new Queue<Queue<Point>>();

		componentNumber = 2;
		for (int row = 0; row < startGrid.length; row++){
			for (int col = 0; col < startGrid[0].length; col++){
				if (needsUpdating(row, col)){
					Queue<Point> points = new Queue<>();
					calculateComponents(row,col, points);
					queueOfComponentPoints.enqueue(points);
					componentNumber++;
				}
			}
		}
	}

	private void calculateComponents(int row, int col, Queue<Point> points){
		grid[row][col].setNum(componentNumber);
		grid[row][col].setUpdated(true);

		points.enqueue(new Point(row, col));

		if (needsUpdating(row + 1, col)){
			calculateComponents(row + 1, col, points);
		}
		if (needsUpdating(row - 1, col)){
			calculateComponents(row - 1, col, points);
		}
		if (needsUpdating(row, col + 1)){
			calculateComponents(row, col + 1, points);
		}
		if (needsUpdating(row, col - 1)){
			calculateComponents(row, col - 1, points);
		}
	}

	private boolean isLegalIndex(int row, int col){
		return (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length);
	}

	private boolean needsUpdating(int row, int col){
		if (isLegalIndex(row, col)){
			return !grid[row][col].isUpdated() && grid[row][col].getNum() != 0;
		}
		return false;
	}

	public void setStartGrid(Square[][] newGrid){
		startGrid = newGrid;
	}

	public Square[][] getStartGrid(){
		return startGrid;
	}

	public Square[][] getResultGrid(){
		return grid;
	}

	public Queue<Queue<Point>> getPoints(){
		return queueOfComponentPoints;
	}
}