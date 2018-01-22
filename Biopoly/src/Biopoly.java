
import processing.core.*;
import g4p_controls.*;
import java.awt.Font;
import java.io.File;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author k24alex
 */
public class Biopoly extends PApplet {

    // GUI declarations
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

    PImage houseImage, boardImage;
    //all player images will be loaded but not all used
    //PImage p1, p2, p3, p4, p0;
    GButton btnNextTurn, btnInstructions;
    static GLabel lblRound, lblMoney, lblName;

    /**
     * Random used for asking questions.
     */
    static Random r = new Random();

    static String[] program
            = {
                "Biopoly"
            };

    public void createGUI() {
        G4P.messagesEnabled(false);
        G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
        G4P.setCursor(ARROW);
        //      surface.setTitle("Sketch Window");
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
     * Stores x and y locations of board location, comes from csv file.
     */
    static int positions[][] = new int[40][2];

    /**
     * Stores knowledge question answers.
     */
    static String knowans[] = new String[80];

    /**
     * stores multiple choice question, options, correct answer, imagename.
     */
    static String mpchoice[][] = new String[20][7];

    /**
     * Stores location name, type, money, name and owner
     */
    static String house[][] = new String[40][5];

    /**
     * Number of players playing the game.
     */
    static int players;

    /**
     * Number of rounds the game will reach.
     */
    static int rounds;

    /**
     * This is the number of the current player in the main loop.
     */
    static int currentPlayer = 0;

    /**
     * Stores user's name, will be used to identify users later.
     */
    static String names[];

    /**
     * Stores the location, round each player is at, their amount of money, and
     * if they are skipping a turn.
     */
    static int data[][];

    /**
     * Stores if a person is able to get money again
     */
    static boolean getsMoney[][];

    static NumberFormat nf = NumberFormat.getCurrencyInstance();

    PImage p[];

    public void setup() {
        size(800, 800, JAVA2D);
        makeQuestions();

        houseImage = loadImage("icons/house.png");
        boardImage = loadImage("board2.jpg");

        File pr = new File("price.csv");
        try {
            Scanner s = new Scanner(pr);
            s.useDelimiter(",|\n");
            for (int i = 0; i < 40; i++) {
                house[i][0] = s.next().trim();
                positions[i][0] = Integer.parseInt(s.next().trim());
                positions[i][1] = Integer.parseInt(s.next().trim());
                house[i][1] = s.next().trim();
                house[i][2] = s.next().trim();
                house[i][3] = s.next().trim();
                house[i][4] = s.next().trim();

            }

        } catch (Exception e) {
        }

        /**
         * file that stores the positions of tiles.
         */
        File pos = new File("locations.csv");

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
                names = new String[players];
                data = new int[players][4];
                p = new PImage[players];
                getsMoney = new boolean[players][40];
                break;

            }

            createGUI();
        }

        //loads images
        for (int i = 0; i < players; i++) {
            p[i] = loadImage("icons/p" + Integer.toString(i + 1) + ".png");
        }

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

        for (int i = 0; i < players; i++) {
            data[i][0] = 0; //location
            data[i][1] = 1; //round
            data[i][2] = 200; //starting money
            data[i][3] = 0; //1 if the player needs to skip turn
            for (int j = 0; j < 40; j++) {
                getsMoney[i][j] = true;
            }
            
        }

        createGUI();
        lblName = new GLabel(this, 250, 120, 300, 60);
        lblName.setFont(new Font("Calibri", 1, 30));
        lblName.setLocalColorScheme(1);
        lblName.setText("Press Next Turn to Start Game!");

        lblMoney = new GLabel(this, 550, 120, 100, 60);
        lblMoney.setFont(new Font("Calibri", 1, 20));
        lblMoney.setLocalColorScheme(1);
        lblMoney.setText(" ");

        lblRound = new GLabel(this, 140, 120, 120, 60);
        lblRound.setFont(new Font("Calibri", 1, 20));
        lblRound.setLocalColorScheme(1);
        lblRound.setText("Round: " + data[currentPlayer][1]);

        btnNextTurn = new GButton(this, 480, 630, 170, 30);
        btnNextTurn.setFont(new Font("Calibri", 1, 20));
        btnNextTurn.setLocalColorScheme(1);
        btnNextTurn.setOpaque(true);
        btnNextTurn.setText("Next Turn -->");

        btnInstructions = new GButton(this, 140, 630, 30, 30);
        btnInstructions.setFont(new Font("Calibri", 1, 20));
        btnInstructions.setLocalColorScheme(1);
        btnInstructions.setOpaque(true);
        btnInstructions.setText("?");

        /**
         * This is the mail loop of the game that is responsible for the
         * gameplay.
         */

        /*
                Here the program needs to stop and wait for the player to hit next turn for the loop to restart
         */
    }

    public void handleButtonEvents(GButton button, GEvent event) {
        if (button == btnInstructions) {
            showInstructions();
        }

        if (button == btnNextTurn) {
            //put code for making the next turn here

            currentPlayer++; //makes the next player the currentPlayer

            currentPlayer = currentPlayer > (players - 1) ? 0 : currentPlayer; //makes sure that current player does not exceed total number of players
            lblName.setText(names[currentPlayer] + "'s Turn");
            if (data[currentPlayer][3] == 1) { //checks if the player is in jail
                JOptionPane.showMessageDialog(null, names[currentPlayer] + " is skipping this turn", "Jail", PERSPECTIVE);
                data[currentPlayer][3] = 0; //renoves jail
                currentPlayer++; //progresses game to next player and loop restarts

            } else {
                int mk = r.nextInt(5) + 0;
                if (mk == 0) {
                    if (mpQuestion(names[currentPlayer])) {
                        int dice = dout(); //rolling the dice if question correct
                        movePlayerR(currentPlayer, dice);
                    }
                } else {
                    if (kQuestion(names[currentPlayer])) {
                        int dice = dout();
                        movePlayerR(currentPlayer, dice);
                    }
                }

                if (checkWin(data[currentPlayer][1], rounds)) {
                    JOptionPane.showMessageDialog(null, "You have won!!!", "GAME OVER", PERSPECTIVE);
                    System.exit(0); //game is shutdown when a player wins
                }

                if (checkHouse(data[currentPlayer][0])) {
                    data[currentPlayer][2] -= 20;
                }
                jail(currentPlayer, data[currentPlayer][0]); //checks if the person landed in jail
                if(getsMoney[currentPlayer][data[currentPlayer][0]]){
                checkQuestion(currentPlayer, data[currentPlayer][0]); //checks if the person landed on question
                }
                checkTax(currentPlayer, data[currentPlayer][0]); //checks if the person owes tax or gets free money
                checkMoney(currentPlayer); //checks if person is bankrupt
                lblRound.setText("Round: " + data[currentPlayer][1]);
                lblMoney.setText("" + nf.format(data[currentPlayer][2]));
            }
        }
    }

    public void draw() {
        background(240, 240, 220);

        image(boardImage, 0, 0);
        for (int i = 0; i < 40; i++) {
            if (!"none".equals(house[i][4])) {
                image(houseImage, positions[i][0], positions[i][1]);
            }
        }

        for (int i = 0; i < players; i++) {
            image(p[i], positions[data[i][0]][0], positions[data[i][0]][1]);
        }

    }

    //this is needed to run the program
    public static void main(String ags[]) {

        PApplet.main(program);
    }

    /**
     * Checks if square is jail or gotojail, and sends people to jail if it is
     * jail
     *
     * @param player currentPlayer
     * @param location player's location
     */
    public static void jail(int player, int location) {
        if ("jail".equals(house[location][1])) {
            JOptionPane.showMessageDialog(null, "You are in jail\nYou will skip the next turn!", "Jail", PERSPECTIVE);
            data[player][3] = 1;
        } else if ("gotojail".equals(house[location][1])) {
            JOptionPane.showMessageDialog(null, "You are going to jail\nYou will skip the next turn!", "Go to Jail", PERSPECTIVE);
            data[player][3] = 1;
            movePlayerA(player, 10); //moves current player to loc10 (jail)
        }
    }

    /**
     * Checks if a player is on a question or 2questions square, and calls
     * question methods.
     *
     * @param player currentPlayer
     * @param location player's location
     */
    public static void checkQuestion(int player, int location) {
        if ("question".equals(house[location][1])) {
            System.out.println("You are on quesiton");
            JOptionPane.showMessageDialog(null, "You have landed on a question\nGet it right, and you will get $150", "Question Square", X);
            int mk = r.nextInt(5) + 0;
            if (mk == 0) {
                if (mpQuestion(names[player])) {
                    data[player][2] += 150;
                }
            } else {
                if (kQuestion(names[player])) {
                    data[player][2] += 150;
                }
            }
            //prevents the player from getting money at this location
            getsMoney[currentPlayer][location] = false;
        } else if ("2questions".equals(house[location][1])) {
            JOptionPane.showMessageDialog(null, "You will have to answer 2 questions", "Knowledge", X);
            System.out.println("you are on 2 queson");
            for (int i = 0; i < 2; i++) {
                int mk = r.nextInt(5) + 0;
                boolean b = mk == 1 ? kQuestion(names[player]) : mpQuestion(names[player]);

            }
        }
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
             * Inputs multiple choice questions.
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
        String[] choices
                = {
                    mpchoice[i][1], mpchoice[i][2], mpchoice[i][3], mpchoice[i][4]
                };
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
    public static boolean checkHouse(int square) {
        if (!"none".equals(house[square][4])) {
            String message = "This property belongs to " + house[square][4] + "\nPay $20!";
            JOptionPane.showMessageDialog(null, message, "Land", PERSPECTIVE);
            data[Integer.parseInt(house[square][4])][2] += 20; //gives owner 20
            return true;

        } else {
            return false;
        }
    }

    /**
     * checks if the square is a tax or free money square
     *
     * @param player
     * @param location
     */
    public static void checkTax(int player, int location) {
        if ("tax".equals(house[location][1])) {
            JOptionPane.showMessageDialog(null, "You will pay a tax of " + house[location][2], "Tax", PERSPECTIVE);
            data[player][2] += Integer.parseInt(house[location][2]);
        } else if ("money".equals(house[location][1]) && getsMoney[player][location]) {
            JOptionPane.showMessageDialog(null, "Hooray! You have found a bag money laying on the road", "Money", PERSPECTIVE);
            data[player][2] += 50;
            getsMoney[player][location] = false;
        }
    }

    /**
     * Checks if the player is bankrupt
     *
     * @param player currentPlayer
     */
    public static void checkMoney(int player) {
        if (data[player][2] < 0) {
            JOptionPane.showMessageDialog(null, "You are bankrupt!\n Everything has been reset", "Money", PERSPECTIVE);
            data[player][1] = 1;
            data[player][2] = 200;
            movePlayerA(player, 0);
        }
    }

    /**
     * Allows the user to buy a property
     *
     * @param name name of purchaser
     * @param location location of square
     */
    public static void buy(int name, int location) {
        if (house[location][4].equals("none") == false) { //property already owned
            JOptionPane.showMessageDialog(null, "This property isn't for sale", "Buy", PERSPECTIVE);
        } else if (data[name][0] != location) { //not on location
            JOptionPane.showMessageDialog(null, "You must be on the square to purchase it!", "Buy", PERSPECTIVE);
        } else {
            if ((data[name][2] - (data[name][2]) * 2) > Integer.parseInt(house[location][2])) { //data-data*2 because price stored in negatives
                JOptionPane.showMessageDialog(null, "You do not have enough money!", "Buy", PERSPECTIVE);
            } else {
                //transaction happens here
                JOptionPane.showMessageDialog(null, "You have purchased " + house[location][3], "Buy", PERSPECTIVE);
                house[location][4] = Integer.toString(name); //ownership set
                data[name][2] += Integer.parseInt(house[location][2]); //money is taken subtracted (added negative)
                lblMoney.setText("" + nf.format(data[currentPlayer][2]));

                //draw an image at positions[location][0], positions[location][1]
            }
        }

    }

    /**
     * moves player to absolute location
     *
     * @param player currentPlayer
     * @param location target location
     */
    public static void movePlayerA(int player, int location) {
        data[player][0] = location;
    }

    /**
     * Moves player relative to current location
     *
     * @param player currentPlayer
     * @param dice outcome of dice
     */
    public static void movePlayerR(int player, int dice) {
        data[player][0] += dice;
        if (data[player][0] > 39) { //if player completes a round
            data[player][0] -= 40; //subract 40
            data[player][1] += 1; //adds 1 to round
            data[player][2] += 200; //gives player $200 for round completion
        }
    }

    public void loc1_click1(GButton source, GEvent event) { //_CODE_:loc1:777178:
        println("loc1 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 1);
    } //_CODE_:loc1:777178:

    public void loc3_click1(GButton source, GEvent event) { //_CODE_:loc3:872241:
        println("loc3 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 3);
    } //_CODE_:loc3:872241:

    public void loc6_click1(GButton source, GEvent event) { //_CODE_:loc6:487743:
        println("loc6 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 6);
    } //_CODE_:loc6:487743:

    public void loc8_click1(GButton source, GEvent event) { //_CODE_:loc8:269587:
        println("loc8 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 8);
    } //_CODE_:loc8:269587:

    public void loc9_click1(GButton source, GEvent event) { //_CODE_:loc9:399246:
        println("loc9 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 9);
    } //_CODE_:loc9:399246:

    public void loc11_click1(GButton source, GEvent event) { //_CODE_:loc11:993494:
        println("loc11 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 11);
    } //_CODE_:loc11:993494:

    public void loc13_click1(GButton source, GEvent event) { //_CODE_:loc13:257534:
        println("loc13 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 13);
    } //_CODE_:loc13:257534:

    public void loc14_click1(GButton source, GEvent event) { //_CODE_:loc14:680707:
        println("loc14 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 14);
    } //_CODE_:loc14:680707:

    public void loc16_click1(GButton source, GEvent event) { //_CODE_:loc16:718130:
        println("loc16 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 16);
    } //_CODE_:loc16:718130:

    public void loc18_click1(GButton source, GEvent event) { //_CODE_:loc18:934508:
        println("loc18 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 18);
    } //_CODE_:loc18:934508:

    public void loc19_click1(GButton source, GEvent event) { //_CODE_:loc19:852682:
        println("loc19 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 19);
    } //_CODE_:loc19:852682:

    public void loc21_click1(GButton source, GEvent event) { //_CODE_:loc21:830483:
        println("loc21 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 21);
    } //_CODE_:loc21:830483:

    public void loc23_click1(GButton source, GEvent event) { //_CODE_:loc23:513948:
        println("loc23 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 23);
    } //_CODE_:loc23:513948:

    public void loc24_click1(GButton source, GEvent event) { //_CODE_:loc24:572394:
        println("loc24 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 24);
    } //_CODE_:loc24:572394:

    public void loc26_click1(GButton source, GEvent event) { //_CODE_:loc26:497215:
        println("loc26 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 26);
    } //_CODE_:loc26:497215:

    public void loc27_click1(GButton source, GEvent event) { //_CODE_:loc27:847439:
        println("loc27 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 27);
    } //_CODE_:loc27:847439:

    public void loc29_click1(GButton source, GEvent event) { //_CODE_:loc29:824381:
        println("loc29 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 29);
    } //_CODE_:loc29:824381:

    public void loc30_click1(GButton source, GEvent event) { //_CODE_:loc31:550845:
        println("loc31 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 30);
    } //_CODE_:loc31:550845:

    public void loc32_click1(GButton source, GEvent event) { //_CODE_:loc32:240339:
        println("loc32 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 32);
    } //_CODE_:loc32:240339:

    public void loc34_click1(GButton source, GEvent event) { //_CODE_:loc34:558537:
        println("loc34 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 34);
    } //_CODE_:loc34:558537:

    public void loc37_click1(GButton source, GEvent event) { //_CODE_:loc37:483830:
        println("loc37 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 37);
    } //_CODE_:loc37:483830:

    public void loc39_click1(GButton source, GEvent event) { //_CODE_:loc39:677374:
        println("loc39 - GButton >> GEvent." + event + " @ " + millis());
        buy(currentPlayer, 39);
    } //_CODE_:loc39:677374:
}
