
import processing.core.*;
import g4p_controls.*;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/*
TODO:
-make gamepeices array (up to 6 players)
-make a peice moving method:
public static void move(int location)
-make a property buy method:
public static void buy(int location)

-build program

Optional: 
-add a soundtrack
 */
/**
 *
 * @author k24alex
 */
public class Biopoly extends PApplet {

    // GUI declarations
    GImageButton board;
    GButton loc1;
    GButton loc3;
    GButton loc6;
    GButton loc8;
    GButton loc9;
    GButton loc11;
    GButton loc13;
    GButton loc14;
    GButton loc16;
    GButton loc18;
    GButton loc19;
    GButton loc21;
    GButton loc23;
    GButton loc24;
    GButton loc26;
    GButton loc27;
    GButton loc29;
    GButton loc31;
    GButton loc32;
    GButton loc34;
    GButton loc37;
    GButton loc39;

    /**
     * Random used for asking questions.
     */
    static Random r = new Random();

    static String[] program = {"Biopoly"};

    public void createGUI() {
        G4P.messagesEnabled(false);
        G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
        G4P.setCursor(ARROW);
        //      surface.setTitle("Sketch Window");
        board = new GImageButton(this, 0, 0, 800, 800, new String[]{"board.jpg", "board.jpg", "board.jpg"});
        board.addEventHandler(this, "imgButton1_click1");
        loc1 = new GButton(this, 616, 676, 60, 30);
        loc1.setLocalColorScheme(GCScheme.PURPLE_SCHEME);
        loc1.addEventHandler(this, "loc1_click1");
        loc3 = new GButton(this, 492, 676, 60, 30);
        loc3.setLocalColorScheme(GCScheme.PURPLE_SCHEME);
        loc3.addEventHandler(this, "loc3_click1");
        loc6 = new GButton(this, 306, 678, 64, 30);
        loc6.setLocalColorScheme(GCScheme.CYAN_SCHEME);
        loc6.addEventHandler(this, "loc6_click1");
        loc8 = new GButton(this, 188, 676, 56, 30);
        loc8.setLocalColorScheme(GCScheme.CYAN_SCHEME);
        loc8.addEventHandler(this, "loc8_click1");
        loc9 = new GButton(this, 120, 678, 64, 30);
        loc9.setLocalColorScheme(GCScheme.CYAN_SCHEME);
        loc9.addEventHandler(this, "loc9_click1");
        loc11 = new GButton(this, 92, 616, 28, 60);
        loc11.setLocalColorScheme(GCScheme.RED_SCHEME);
        loc11.addEventHandler(this, "loc11_click1");
        loc13 = new GButton(this, 92, 492, 30, 60);
        loc13.setLocalColorScheme(GCScheme.RED_SCHEME);
        loc13.addEventHandler(this, "loc13_click1");
        loc14 = new GButton(this, 92, 432, 30, 60);
        loc14.setLocalColorScheme(GCScheme.RED_SCHEME);
        loc14.addEventHandler(this, "loc14_click1");
        loc16 = new GButton(this, 92, 308, 30, 60);
        loc16.setLocalColorScheme(GCScheme.ORANGE_SCHEME);
        loc16.addEventHandler(this, "loc16_click1");
        loc18 = new GButton(this, 92, 184, 28, 64);
        loc18.setLocalColorScheme(GCScheme.ORANGE_SCHEME);
        loc18.addEventHandler(this, "loc18_click1");
        loc19 = new GButton(this, 92, 124, 28, 60);
        loc19.setLocalColorScheme(GCScheme.ORANGE_SCHEME);
        loc19.addEventHandler(this, "loc19_click1");
        loc21 = new GButton(this, 124, 92, 60, 30);
        loc21.setLocalColorScheme(GCScheme.RED_SCHEME);
        loc21.addEventHandler(this, "loc21_click1");
        loc23 = new GButton(this, 244, 92, 64, 30);
        loc23.setLocalColorScheme(GCScheme.RED_SCHEME);
        loc23.addEventHandler(this, "loc23_click1");
        loc24 = new GButton(this, 308, 92, 60, 30);
        loc24.setLocalColorScheme(GCScheme.RED_SCHEME);
        loc24.addEventHandler(this, "loc24_click1");
        loc26 = new GButton(this, 432, 92, 60, 30);
        loc26.setLocalColorScheme(GCScheme.YELLOW_SCHEME);
        loc26.addEventHandler(this, "loc26_click1");
        loc27 = new GButton(this, 492, 92, 60, 30);
        loc27.setLocalColorScheme(GCScheme.YELLOW_SCHEME);
        loc27.addEventHandler(this, "loc27_click1");
        loc29 = new GButton(this, 616, 92, 60, 30);
        loc29.setLocalColorScheme(GCScheme.YELLOW_SCHEME);
        loc29.addEventHandler(this, "loc29_click1");
        loc31 = new GButton(this, 676, 124, 32, 60);
        loc31.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        loc31.addEventHandler(this, "loc30_click1");
        loc32 = new GButton(this, 676, 184, 32, 60);
        loc32.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        loc32.addEventHandler(this, "loc32_click1");
        loc34 = new GButton(this, 676, 308, 32, 64);
        loc34.setLocalColorScheme(GCScheme.GREEN_SCHEME);
        loc34.addEventHandler(this, "loc34_click1");
        loc37 = new GButton(this, 676, 492, 32, 60);
        loc37.addEventHandler(this, "loc37_click1");
        loc39 = new GButton(this, 676, 616, 32, 60);
        loc39.addEventHandler(this, "loc39_click1");
    }

    /**
     * Stores knowledge questions.
     */
    static String knowq[] = new String[80];

    /**
     * Stores knowledge question answers.
     */
    static String knowans[] = new String[80];

    /**
     * stores multiple choice question, options, correct answer, imagename.
     */
    static String mpchoice[][] = new String[20][7];

    /**
     * Stores owner of houses.
     */
    static String house[] = new String[40];

    public void setup() {
        size(800, 800, JAVA2D);
        makeQuestions();
        
        /**
         * Sets ownership of squares to nobodu
         */
        for (int i = 0; i < 40; i++) {
            house[i] = "none";
        }
        
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
         * file that stores the positions of tiles.
         */
        File pos = new File("locations.csv");

        //testing funcionality of different methods.
        checkHouse(34);
        dout();
        dout();
        showInstructions();
        mpQuestion("Alex");
        mpQuestion("Alex");
        kQuestion("Alex");
        kQuestion("Alex");

        /**
         * User inputs the amount of rounds they wish the game will last,
         * minimum of 3.
         */
        while (true) {
            rounds = Integer.parseInt(JOptionPane.showInputDialog("How many rounds? (min 3)"));
            if (rounds >= 3) {
                break;
            }
        }

        /**
         * User inputs the amount of players in the game.
         */
        while (true) {
            players = Integer.parseInt(JOptionPane.showInputDialog("How many players? (max 5)"));
            if (players >= 1 && players <= 5) {
                break;
            }

            createGUI();
        }

        /**
         * Stores user's name, will be used to identify users later.
         */
        String names[] = new String[players];

        /**
         * Stores the location, round each player is at, and their amount of
         * money.
         */
        int data[][] = new int[players][3];

        /**
         * How many hops the player takes.
         */
        int dout;

        /**
         * User Inputs their name.
         */
        for (int i = 0; i < players; i++) {
            names[i] = JOptionPane.showInputDialog("What is player " + (i + 1) + "'s name?");
        }
        
        for (int i = 0; i <= players; i++) {
            data[i][0] = 0; //location
            data[i][1] = 1; //round
            data[i][2] = 200; //starting money
        }

        createGUI();
//        customGUI();

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

    /**
     * Checks if the the player has finished the game.
     *
     * @param t this is the players current round
     * @param m this is the amount of rounds the game will go to
     * @return true if the player finished the last round, otherwise false
     */
    public static boolean checkWin(int t, int m) {
        return t > m;
    }

    /**
     * Generates questions from file.
     */
    public static void makeQuestions() {
        File know = new File("know.txt");
        File multiple = new File("mpchoice.txt");

        try {
            Scanner k = new Scanner(know);
            for (int i = 0; i < 80; i++) {
                knowq[i] = k.nextLine();
                knowans[i] = k.nextLine();
            }
            k.close();
        } catch (Exception e) {
        }

        try {
            Scanner m = new Scanner(multiple);

            /**
             * Inputs multple choice questions.
             */
            for (int i = 0; i < 20; i++) {
                mpchoice[i][0] = m.nextLine(); //question
                mpchoice[i][1] = m.nextLine(); //option A
                mpchoice[i][2] = m.nextLine(); //option B
                mpchoice[i][3] = m.nextLine(); //option C
                mpchoice[i][4] = m.nextLine(); //option D
                mpchoice[i][5] = m.nextLine(); //correct option
                mpchoice[i][6] = m.nextLine(); //image file, null if missing
            }

            m.close();

        } catch (Exception e) {
        }
    }

    /**
     * Generates a random number, as if a die was rolled.
     *
     * @return a random number between 1 and 6
     */
    public static int dout() {
        int out = r.nextInt(6) + 1;
        
        ImageIcon d[] = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            d[i] = new ImageIcon("dies/d" + (i + 1) + ".jpg");
        }

        JOptionPane.showMessageDialog(null, "You rolled " + out, "Dice", PERSPECTIVE, d[out - 1]);
        return out;
    }

    /**
     * Asks user question from mpchoice.txt file, compares answer.
     *
     * @param player player's name.
     * @return true if answer correct.
     */
    public static boolean mpQuestion(String player) {
        int i = r.nextInt(20) + 0;
        String input;
        String[] choices = {mpchoice[i][1], mpchoice[i][2], mpchoice[i][3], mpchoice[i][4]};
        if (mpchoice[i][6] != null) {
            ImageIcon icon;
            icon = new ImageIcon("images/" + mpchoice[i][6]);
            input = (String) JOptionPane.showInputDialog(null, mpchoice[i][0],
                    player + "'s Question", JOptionPane.QUESTION_MESSAGE, icon, choices, choices[0]);
        } else {
            input = (String) JOptionPane.showInputDialog(null, mpchoice[i][0],
                    player + "'s Question", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        }

        if (input.startsWith(mpchoice[i][5])) {
            JOptionPane.showMessageDialog(null, "Correct!", "Correct!", PERSPECTIVE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Correct answer is: " + mpchoice[i][5], "Incorrect!", PERSPECTIVE);
            return false;
        }
    }

    /**
     * Asks question from know.txt file, compares answer
     *
     * @param player current player's name
     * @return true if correct answer, otherwise false
     */
    public static boolean kQuestion(String player) {
        int i = r.nextInt(80) + 0;
        String input;

        input = JOptionPane.showInputDialog(null, knowq[i], player + "'s Question", PERSPECTIVE);

        if (input.toLowerCase().equals(knowans[i])) {
            JOptionPane.showMessageDialog(null, "Correct!", "Correct!", PERSPECTIVE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "The correct answer is: " + knowans[i], "Incorrect!", PERSPECTIVE);
            return false;
        }

    }

    /**
     * Show the user the instructions.
     */
    public static void showInstructions() {

        /**
         * file that stores instructions and rules.
         */
        File instructions = new File("instructions.txt");

        try {
            Scanner inst = new Scanner(instructions);
            String rules = new String();

            for (int i = 0; i < 15; i++) {
                rules += inst.nextLine() + "\n";
            }

            JOptionPane.showMessageDialog(null, rules, "Rules", PERSPECTIVE);

            inst.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "file not found!");
        }
    }
    
    /**
     * Checks if there is a property on square
     * 
     * @param square square number
     * @return true if there is property otherwise false
     */
    public static boolean checkHouse(int square){
        if(!"none".equals(house[square])){
            String message = "This property belongs to " + house[square] + "\nPay $20!";
            JOptionPane.showMessageDialog(null, message, "Land", PERSPECTIVE);
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * Allows the user to buy a property
     * 
     * @param name name of purchaser
     * @param location location of square
     * @param cost cost of property
     */
    public static void buy(String name, int location, int cost){
        //missing: place a house on board
        
        house[location] = name;
    }

    //replace with GImage later
    public void imgButton1_click1(GImageButton source, GEvent event) { //_CODE_:board:859749:
        //println("imgButton1 - GImageButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:board:859749:

    public void loc1_click1(GButton source, GEvent event) { //_CODE_:loc1:777178:
        println("loc1 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc1:777178:

    public void loc3_click1(GButton source, GEvent event) { //_CODE_:loc3:872241:
        println("loc3 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc3:872241:

    public void loc6_click1(GButton source, GEvent event) { //_CODE_:loc6:487743:
        println("loc6 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc6:487743:

    public void loc8_click1(GButton source, GEvent event) { //_CODE_:loc8:269587:
        println("loc8 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc8:269587:

    public void loc9_click1(GButton source, GEvent event) { //_CODE_:loc9:399246:
        println("loc9 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc9:399246:

    public void loc11_click1(GButton source, GEvent event) { //_CODE_:loc11:993494:
        println("loc11 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc11:993494:

    public void loc13_click1(GButton source, GEvent event) { //_CODE_:loc13:257534:
        println("loc13 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc13:257534:

    public void loc14_click1(GButton source, GEvent event) { //_CODE_:loc14:680707:
        println("loc14 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc14:680707:

    public void loc16_click1(GButton source, GEvent event) { //_CODE_:loc16:718130:
        println("loc16 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc16:718130:

    public void loc18_click1(GButton source, GEvent event) { //_CODE_:loc18:934508:
        println("loc18 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc18:934508:

    public void loc19_click1(GButton source, GEvent event) { //_CODE_:loc19:852682:
        println("loc19 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc19:852682:

    public void loc21_click1(GButton source, GEvent event) { //_CODE_:loc21:830483:
        println("loc21 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc21:830483:

    public void loc23_click1(GButton source, GEvent event) { //_CODE_:loc23:513948:
        println("loc23 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc23:513948:

    public void loc24_click1(GButton source, GEvent event) { //_CODE_:loc24:572394:
        println("loc24 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc24:572394:

    public void loc26_click1(GButton source, GEvent event) { //_CODE_:loc26:497215:
        println("loc26 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc26:497215:

    public void loc27_click1(GButton source, GEvent event) { //_CODE_:loc27:847439:
        println("loc27 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc27:847439:

    public void loc29_click1(GButton source, GEvent event) { //_CODE_:loc29:824381:
        println("loc29 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc29:824381:

    public void loc30_click1(GButton source, GEvent event) { //_CODE_:loc31:550845:
        println("loc31 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc31:550845:

    public void loc32_click1(GButton source, GEvent event) { //_CODE_:loc32:240339:
        println("loc32 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc32:240339:

    public void loc34_click1(GButton source, GEvent event) { //_CODE_:loc34:558537:
        println("loc34 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc34:558537:

    public void loc37_click1(GButton source, GEvent event) { //_CODE_:loc37:483830:
        println("loc37 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc37:483830:

    public void loc39_click1(GButton source, GEvent event) { //_CODE_:loc39:677374:
        println("loc39 - GButton >> GEvent." + event + " @ " + millis());
    } //_CODE_:loc39:677374:
}
