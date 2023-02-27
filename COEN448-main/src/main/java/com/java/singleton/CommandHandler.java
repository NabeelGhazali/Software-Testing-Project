package com.java.singleton;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

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
     * UI for displaying commands 
     */
    public static void ui() {
        String value = "";    //input is stored in value variable

        //user_interface(ui)
        System.out.println("Hello My name is Jarvis!!!"
        		+ "Please enter your command: ");
        value = sc.nextLine();

        if(handleInput(value,false)){
            
        	//end of the process
            System.out.println("\"Jarvis is low on battery Jarvis is .. dy...\"");
        }
    }

    public static boolean handleInput(String value, boolean history){
        if (value.isEmpty()) {
            value = "-1";
        }
        //Longest command is reduced to 5 character string
        
        if (value.length()>6) {
            value = "-1";
        }
        //number given for initialization and move spaces
        int number = 0;

        //if initialize or Move command is entered without a number check
        
        
        if((value.charAt(0) == 'I' || value.charAt(0) == 'i' || value.charAt(0) == 'M' || value.charAt(0) == 'm')
                && value.length()==1) {
            value = "x"; //input is incorrect
        }
        else if(value.charAt(0) == 'I' || value.charAt(0) == 'i') { //if I input, initialization start
            
        	//getting the initialization number
            number = intValueGiven(value);
            //if number is negative,or just 1 the minimum allowed value should be more than 2 maximum value is capped at 100
            if((number < 2) || (number > 100)) {
                value = "x";
            } else {
                value = "I";
            }
        }
        else if(value.charAt(0) == 'M' || value.charAt(0) == 'm') { //if m input, move started
            
        	//getting the value for the desired movement
            number = intValueGiven(value);
            //number should be positive integer
            if(number <=0) {
                if(value.equals("MENU") || value.equals("menu")){}
                else
                    value = "x";
            } else {
                value = "M";
            }
        }

        //For every desired command switch cases
        
        // with each command case checking the system is initialized or not
        
        switch (value) {
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
                        "Q: Stop the program\n");
                break;
            case "i":
            case "I":
                initializeSystem(number);
                break;
            case "u":
            case "U":
                if(initialized) {
                    penUp();
                }else
                    System.out.println("Please initialize the system first");
                break;
            case "d":
            case "D":
                if(initialized) {
                    penDown();
                }else
                    System.out.println("Please initialize the system first");
                break;
            case "r":
            case "R":
                if(initialized) {
                    turnRight();
                }else
                    System.out.println("Please initialize the system first");
                break;
            case "l":
            case "L":
                if(initialized) {
                    turnLeft();
                }else
                    System.out.println("Please initialize the system first");
                break;
            case "m":
            case "M":
                if(initialized) {
                    moveRobot(number);
                }else
                    System.out.println("Please initialize the system first");
                break;
            case "p":
            case "P":
                if(initialized) {
                    printTable();
                }else
                    System.out.println("Please initialize the system first");
                break;
            case "c":
            case "C":
                if(initialized) {
                    printPosition();
                }else
                    System.out.println("Please initialize the system first");
                break;
            
            case "q":
            case "Q":
                return true;
                // exit(1);
            case "-1":
                System.out.println("User selected Nothing");
                break;
            default:
                System.out.println("Sorry I can not understand. Please choose a command from the menu");
            }
        
        return false;
    }

    //getting the number from the string 
    
    public static int intValueGiven(String value)
    {
        int number = 0;
        String var;

        //Getting integer 
        
        StringBuilder sb = new StringBuilder(value);
        sb.deleteCharAt(0);
        var = removeBlankSpace(sb);

        if(isNumeric(var)){
          
            number = Integer.parseInt(var);
        }else{
            //invalid
            number = -1;
        }

        return number;
    }

    //removing blank spaces from the string
    public static String removeBlankSpace(StringBuilder sb) {
        int j = 0;
        for(int i = 0; i < sb.length(); i++) {
            if (!Character.isWhitespace(sb.charAt(i))) {
                sb.setCharAt(j++, sb.charAt(i));
            }
        }
        sb.delete(j, sb.length());
        return sb.toString();
    }

    //make sure given value is a number
    public static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
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

        //direction of the pen
        String direction = robot.getDirection();

        //position of the robot
        System.out.println("Position: " + y + ", " + x + " - Pen: " + pen + " - Facing: " + direction);
    }

    //table along with the coordinates of the robot and the orientation of the pen
    public static void printTable() {
        if(!flag)
            commands.add("p");
        
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

    //turnLeft function to turn the robot left
    public static void turnLeft() {
        if(!flag)
            commands.add("l");
        System.out.println("Jarvis Turning left...");
        String currentDirection = robot.getDirection();

       // The current direction is changed based on the input given
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

    //turnRight function to turn the robot right
    public static void turnRight() {
        if(!flag)
            commands.add("r");

        System.out.println("Jarvis Turning right...");
        String currentDirection = robot.getDirection();

        //turn correct direction, depending on current direction
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

    //the pen down 
    public static void penDown() {
        if(!flag)
            commands.add("d");

        if(robot.getPenState()){
            System.out.println("Pen direction already down");
        } else{
            System.out.println("Pen direction going down...");
            robot.setPenState(true);
            table.writeTable(robot.getCoordinates(), true);
            //when pen is turned down, print * on the current robot position
        }
    }

    //making the arrow change when pen is up
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

    //initialization of the robot (Creating new table+reset+boolean initiate)
    
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
