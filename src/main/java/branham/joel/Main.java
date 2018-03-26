package branham.joel;

import java.io.File;
import branham.joel.Grid;
import branham.joel.Panel;

import javax.swing.*;

public class Main{
	
	public static void main(String[] args){
		Grid g = new Grid(new File("src//main//resources//input1.txt"));
		Panel panel = new Panel(g);

		JFrame win = new JFrame("Image-Component Labeler");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.add(panel);
		win.pack();
		win.setVisible(true);
	}
}