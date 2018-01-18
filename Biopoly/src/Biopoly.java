
import processing.core.*;
import g4p_controls.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author k24alex
 */
public class Biopoly extends PApplet
{

    // GUI declarations
    GImageButton imgButton1;
    GImageButton loc0;

    static String[] program =
    {
        "Biopoly"
    };

    public void imgButton1_click1(GImageButton source, GEvent event)
    { //_CODE_:imgButton1:856840:
        println("imgButton1 - GImageButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:imgButton1:856840:

    public void loc0_click1(GImageButton source, GEvent event)
    { //_CODE_:loc0:590206:
        System.out.println("LOC0 BUTTON CLICKED!!!!");
    } //_CODE_:loc0:590206:

    public void createGUI()
    {
        imgButton1 = new GImageButton(this, 0, 0, 800, 800, new String[]
        {
            "board.jpg", "board.jpg", "board.jpg"
        });
        imgButton1.addEventHandler(this, "imgButton1_click1");
        loc0 = new GImageButton(this, 680, 680, 120, 120, new String[]
        {
            "", "", ""
        });
        loc0.addEventHandler(this, "loc0_click1");
    }

    public void setup()
    {
        size(800, 800, JAVA2D);
        /**
         * Number of players playing the game.
         */
        int players;

        /**
         * Number of rounds the game will reach.
         */
        int rounds;

        /**
         * Stores x and y locations of board location, comes from csv file.
         */
        int positions[][] = new int[40][2];

        /**
         * Stores knowledge questions.
         */
        String knowq[] = new String[80];

        /**
         * Stores knowledge question answers.
         */
        String ansq[] = new String[80];

        /**
         * stores multiple choice questions.
         */
        String mcq[] = new String[20];

        /**
         * stores multiple choice question answers.
         */
        String ansmcq[] = new String[20];

        /**
         * file that stores the positions of tiles.
         */
        File pos = new File("positions.csv");

        /**
         * file that stores knowledge questions and answers.
         */
        File know = new File("knowledge.txt");

        /**
         * file that stores multiple choice questions and answers.
         */
        File multiple = new File("multiplechoice.txt");

        /**
         * User inputs the amount of rounds they wish the game will last,
         * minimum of 3.
         */
        while (true)
        {
            rounds = Integer.parseInt(JOptionPane.showInputDialog("How many rounds? (min 3)"));
            if (rounds >= 3)
            {
                break;
            }
        }

        /**
         * User inputs the amount of players in the game.
         */
        while (true)
        {
            players = Integer.parseInt(JOptionPane.showInputDialog("How many players? (max 6)"));
            if (players >= 1 && players <= 6)
            {
                break;
            }

            createGUI();
        }

        /**
         * Stores user's name, will be used to identify users later.
         */
        String names[] = new String[players];

        /**
         * Stores the round each player is at, and their amount of money.
         */
        int data[][] = new int[players][2]; //current round, money

        /**
         * User Inputs their name.
         */
        for (int i = 0; i < players; i++)
        {
            names[i] = JOptionPane.showInputDialog("What is player " + (i + 1) + "'s name?");
        }

    }

    public void handleButtonEvents(GButton button, GEvent event)
    {

        //code for buttons goes here
    }

    public void draw()
    {
        background(240, 240, 220);
    }

    //this is needed to run the program
    public static void main(String ags[])
    {

        PApplet.main(program);
    }

    /**
     * Checks if the the player has finished the game
     *
     * @param t this is the players current round
     * @param m this is the amount of rounds the game will go to
     * @return true if the player finished the last round, otherwise false
     */
    public static boolean checkWin(int t, int m)
    {
        return t > m;
    }
}
