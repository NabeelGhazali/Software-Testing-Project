package com.java.singleton;

import java.awt.*;
import java.util.Scanner;

import java.util.ArrayList;


public class CommandHandler {

    private static final Scanner sc = new Scanner(System.in);
    public static Table table;
    public static Singleton robot;
    public static boolean initialized = false;
    public static ArrayList<String> commands = new ArrayList<String>();
    public static boolean flag = false;

    //constructor, runs the UI
    public CommandHandler(){}

    
    /**
     * User Interface 
     */
    public static void ui() {
        String userInput = ""; // initialize user input variable
        // display UI
        System.out.println("\nHello, my name is Jarvis! Please enter your command:");
        userInput = sc.nextLine(); // store user input in variable

        if(handleInput(userInput, false)){ // check if user input is valid
            // display end message if input is valid
            System.out.println("\"Jarvis is low on battery Jarvis is .. dy...\"");
        }
    }


    public static boolean handleInput(String userInput, boolean isHistoryMode) {
        // If user input is empty, set it to -1
        if (userInput.isEmpty()) {
            userInput = "-1";
        }
        
        // If user input length is greater than 6, set it to -1
        if (userInput.length() > 6) {
            userInput = "-1";
        }
        
        int number = 0; // variable to store number entered in the command
        
        // If user enters 'M' or 'I' alone without a number, set it to 'x'
        if ((userInput.charAt(0) == 'M' || userInput.charAt(0) == 'm' || userInput.charAt(0) == 'I' || userInput.charAt(0) == 'i')
                && userInput.length() == 1) {
            userInput = "x"; // incorrect input
        }
        else if (userInput.charAt(0) == 'I' || userInput.charAt(0) == 'i') { // if 'I' is entered, initialization is requested
            // get number entered in the command
            number = intValueGiven(userInput);
            // if number is negative or not within range of 2 to 100, set it to 'x'
            if ((number < 2) || (number > 100)) {
                userInput = "x";
            } else {
                userInput = "I";
            }
        }
        else if (userInput.charAt(0) == 'M' || userInput.charAt(0) == 'm') { // if 'M' is entered, move is requested
            // get number entered in the command
            number = intValueGiven(userInput);
            // if number is negative and not equal to "MENU" or "menu", set it to 'x'
            if (number <= 0) {
                if (userInput.equals("MENU") || userInput.equals("menu")) {
                    // do nothing
                }
                else {
                    userInput = "x";
                }
            } else {
                userInput = "M";
            }
        }

        // Switch statement for handling all possible commands
        switch (userInput) {
            case "menu":
            case "MENU":
                System.out.println("\nEnter 'Q' to close program");
                System.out.println("All Possible commands:\n" +
                        "I n: Initialize the system\n" +
                        "U: Pen Up\n" +
                        "D: Pen Down\n" +
                        "R: Turn Right\n" +
                        "L: Turn Left\n" +
                        "M s: Move forward s spaces\n" +
                        "P: Print the table\n" +
                        "C: Print current position of the pen\n" +
                        "H: Replay all the commands entered\n" +
                        "Q: Stop the program\n");
                break;
            case "i":
            case "I":
                initializeSystem(number);
                break;
            case "u":
            case "U":
                if (initialized) {
                    penUp();
                } else {
                    System.out.println("Please initialize the system first");
                }
                break;
            case "d":
            case "D":
                if (initialized) {
                    penDown();
                } else {
                    System.out.println("Please initialize the system first");
                }
                break;
            case "r":
            case "R":
                if (initialized) {
                    turnRight();
                } else {
                    System.out.println("Please initialize the system first");
                }
                break;
            case "l":
            case "L":
                if (initialized) {
                    turnLeft();
                    } else {
                        System.out.println("Please initialize the system first");
                    }
                    break;
                case "m":
                case "M":
                    if (initialized) {
                        moveRobot(number);
                    } else {
                        System.out.println("Please initialize the system first");
                    }
                    break;
                case "p":
                case "P":
                    if (initialized) {
                        printTable();
                    } else {
                        System.out.println("Please initialize the system first");
                    }
                    break;
                case "c":
                case "C":
                    if (initialized) {
                        printPosition();
                    } else {
                        System.out.println("Please initialize the system first");
                    }
                    break;
                case "h":
                case "H":
                    //run history of commands
                    if (!commands.isEmpty()){
                        runHistory();
                    }else {
                        System.out.println("Please run a few commands before.");
                    }
                    break;
                case "q":
                case "Q":
                    return true;
                    // exit(1);
                case "-1":
                    System.out.println("User selected Nothing");
                    break;
                default:
                    System.out.println("Sorry, I can not understand. Please choose a command from the menu");
                }
                if (!isHistoryMode) {
                    ui();
                }
                return false;
 
    }

 // getting the number from the string
    
    public static int intValueGiven(String value)
    {
        int number = 0;
        String var;

     // Getting integer
        
        StringBuilder sb = new StringBuilder(value);
        sb.deleteCharAt(0);
        var = removeBlankSpace(sb);

        if(isNumeric(var)){
            //save given number
            number = Integer.parseInt(var);
        }else{
            //invalid format
            number = -1;
        }
        return number;
    }
    
 // remove blank spaces from the StringBuilder object
    public static String removeBlankSpace(StringBuilder sb) {
        int j = 0;
        // iterate over the characters in the StringBuilder object
        for (int i = 0; i < sb.length(); i++) {
            // check if the character is not a whitespace character
            if (!Character.isWhitespace(sb.charAt(i))) {
                // move the non-whitespace character to the front of the StringBuilder object
                sb.setCharAt(j++, sb.charAt(i));
            }
        }
        // delete any extra characters at the end of the StringBuilder object
        sb.delete(j, sb.length());
        // return the modified StringBuilder object as a string
        return sb.toString();
    }

    //make sure given value is a number
    public static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    //Replay all the commands entered by the user as a history
    public static void runHistory(){
        flag = true;
        for (String str : commands) {
            System.out.println("\nHistory command:" + str + "\n");
            handleInput(str,true);
        }

        commands.clear();
        flag = false;
    }
    //print the position of the robot
    public static void printPosition() {
        if(!flag)
            commands.add("c");

        //System.out.println("Printing position...");
        //get coordinates
        Point position = robot.getCoordinates();
        int x = position.x;
        int y = position.y;
        //get state of the pen
        boolean upDown = robot.getPenState();
        String pen;
        if (upDown)
            pen = "down";
        else
            pen = "up";

        //get direction of the pen
        String direction = robot.getDirection();

        //print position
        System.out.println("Position: " + y + ", " + x + " - Pen: " + pen + " - Facing: " + direction);
    }

    //print the table along with the coordinates of the robot and the orientation of the pen
    public static void printTable() {
        if(!flag)
            commands.add("p");
        
        //System.out.println("Printing table...");
        table.printTable(robot.getCoordinates(), robot.getPenState());
    }

    //move the robot
    public static void moveRobot(int steps) {
        if(!flag)
            commands.add("m " + steps);

        String direction = robot.getDirection();
        Point position = robot.getCoordinates();
        Point nextPoint = new Point(-1, -1);
        int x = position.x;
        int y = position.y;
        boolean onTable;
        boolean penState = robot.getPenState();

        switch (direction) {
            case "north":
                nextPoint = new Point(x + steps, y);
                break;
            case "south":
                nextPoint = new Point(x - steps, y);
                break;
            case "west":
                nextPoint = new Point(x, y - steps);
                break;
            case "east":
                nextPoint = new Point(x, y + steps);
                break;
        }

        onTable = table.isOnTable(nextPoint);

        if(onTable){
            //move robot
            System.out.println("Jarvis Moving...");
            while(steps>0) {
                if (direction.equals("north"))
                    robot.getCoordinates().move((int) (robot.getCoordinates().getX()+1), (int) robot.getCoordinates().getY());
                if (direction.equals("south"))
                    robot.getCoordinates().move((int) (robot.getCoordinates().getX()-1), (int) robot.getCoordinates().getY());
                if (direction.equals("west"))
                    robot.getCoordinates().move((int) (robot.getCoordinates().getX()), (int) robot.getCoordinates().getY()-1);
                if (direction.equals("east"))
                    robot.getCoordinates().move((int) (robot.getCoordinates().getX()), (int) robot.getCoordinates().getY()+1);
                steps--;
                table.writeTable(robot.getCoordinates(), penState);
            }
        }else{
            System.out.println("Can not move " + steps + " in the " + direction + " direction, the robot will fall off the table");
        }
    }

    //turn the robot left
    public static void turnLeft() {
        if(!flag)
            commands.add("l");
        System.out.println("Jarvis Turning left...");
        String currentDirection = robot.getDirection();

     // Turn the robot to the correct direction, depending on its current direction
        switch (currentDirection) {
            case "north":
                robot.setDirectionWest();
                break;
            case "south":
                robot.setDirectionEast();
                break;
            case "east":
                robot.setDirectionNorth();
                break;
            case "west":
                robot.setDirectionSouth();
                break;
        }
    }

    //turn the robot right
    public static void turnRight() {
        if(!flag)
            commands.add("r");

        System.out.println("Jarvis Turning right...");
        String currentDirection = robot.getDirection();

     // Turn the robot to the correct direction, depending on its current direction
        switch (currentDirection) {
            case "north":
                robot.setDirectionEast();
                break;
            case "south":
                robot.setDirectionWest();
                break;
            case "east":
                robot.setDirectionSouth();
                break;
            case "west":
                robot.setDirectionNorth();
                break;
        }
    }

    //make the pen face down
    public static void penDown() {
        if(!flag)
            commands.add("d");

        if(robot.getPenState()){
            System.out.println("Pen direction already down");
        } else{
            System.out.println("Pen direction going down...");
            robot.setPenState(true);
            table.writeTable(robot.getCoordinates(), true);//when pen is turned down, automatically write * on the current robot position
        }
    }

    //make the pen face up
    public static void penUp() {
        if(!flag)
            commands.add("u");

        if(!robot.getPenState()){
            System.out.println("Pen direction already up");
        } else{
            System.out.println("Pen direction going up...");
            robot.setPenState(false);
        }
    }

    //initialize the system
    //make new table
    //reset robot
    //set initialized boolean
    public static void initializeSystem(int size) {
        if(!flag)
            commands.add("i " + size);

        if(size < 2){
            System.out.println("Please choose a size bigger or equal to 2");
        } else{
            System.out.println("Initializing with size: " + size);
            table = new Table(size,size);
            robot = Singleton.getInstance();
            robot.reinitialize();
            initialized = true;
        }
    }
}

