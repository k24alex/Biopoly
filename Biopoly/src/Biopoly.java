
import processing.core.*;
import g4p_controls.*;
import javax.swing.*;

//change name of class:
public class Biopoly extends PApplet {

    static String[] program = {"Biopoly"};

    public void setup() {
        size(640, 480, JAVA2D);
        int players; //players
        int rounds; //number of rounds
        
        //asks how many rounds (shortens game play)
        while (true) {
            rounds = Integer.parseInt(JOptionPane.showInputDialog("How many rounds? (min 3)"));
            if (rounds >= 3) {
                break;
            }
        }
        
        //asks for amount of players min 1, max 6
        while (true) {
            players = Integer.parseInt(JOptionPane.showInputDialog("How many players? (max 6)"));
            if (players >= 1 && players <= 6) {
                break;
            }
        }
        
        //2 parallel arrays
        String names[] = new String[players]; //names
        int data[][] = new int[players][2]; //current round, money
        
        //loop asks for players' names
        for (int i = 0; i < players; i++) {
            names[i] = JOptionPane.showInputDialog("What is player "+(i+1)+"'s name?"); 
        }
        
    }

    public void handleButtonEvents(GButton button, GEvent event) {

        //code for buttons goes here
    }

    public void draw() {
        background(240, 240, 220);
    }

    //this is needed to run the program
    public static void main(String ags[]) {

        PApplet.main(program);
    }

}
