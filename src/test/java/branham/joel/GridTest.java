package branham.joel;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import branham.joel.Square;
import branham.joel.Queue;

public class GridTest{
	
	private Grid g;
	
	@Before
	public void setup(){
		g = new Grid(new File("src//test//resources//test1.txt"));
	}
	
	@Test
	public void checkPointsInGrid(){
		boolean correctGrid = true;
		int[][] expected = new int[][]{
		{2, 2, 0 ,0},
		{0, 0, 3, 3},
		{0, 0, 3, 0},
		{4, 0, 0, 5}};
		Square[][] grid = g.getResultGrid();
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				if (expected[i][j] != grid[i][j].getNum()){
					correctGrid = false;
				}
			}
		}
		assertTrue(correctGrid);
	}

}