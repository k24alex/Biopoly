import processing.core.*;
import g4p_controls.*;
import javax.swing.*;

//change name of class:
public class Biopoly extends PApplet {
	static String[] program = {"Biopoly"};
	public void setup()
	{
		size(640, 480, JAVA2D);

	}

	public void handleButtonEvents(GButton button, GEvent event) 
	{

		//code for buttons goes here
	}		

	public void draw()
	{
		background(240, 240, 220); //light tan
	}

	//this is needed to run the program
	public static void main (String ags[])
	{
		
		PApplet.main(program);
	}

}