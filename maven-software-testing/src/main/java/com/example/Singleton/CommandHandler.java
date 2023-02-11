package com.example.Singleton;

import java.awt.*;
import java.net.PasswordAuthentication;
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

    //constructor is running the user interface UI

    


    public CommandHandler(){}


    // UI of the client
    // displays and handles the commands to the server
    public static void ui() {
        String val = "";

        
        System.out.println("Please enter your command: ");
        val = sc.nextLine();
        
        if(handleInput(val, false)){
            System.out.println("Jarvis is low on battery Jarvis is .. dy...");
        }
    }

    public static boolean handleInput(String val, boolean history){
        if (val.isEmpty()) {
            val = "-1";
        }
        //the user can input a maximum of command of length 5
        if (val.length()>6) {
            val = "-1";
        }
        //moveSpaces given for initialization and move spaces
        int moveSpaces = 0;


        if((val.charAt(0) == 'M' || val.charAt(0) == 'm' || val.charAt(0) == 'I' || val.charAt(0) == 'i')
                && val.length()==1) {
            val = "x"; // the value is set to x for incorrect input
        }
        else if(val.charAt(0) == 'I' || val.charAt(0) == 'i') {
            // intcommand teks in the command and converts the movespace command to integer
            
            
            moveSpaces = intCommand(val);
            //if moveSpaces is negative, it generates an invalid input
            if((moveSpaces < 2) || (moveSpaces > 100)) {
                val = "x";
            } else {
                val = "I";
            }
        }
        else if(val.charAt(0) == 'M' || val.charAt(0) == 'm') { //if m input, move requested
            //get moveSpaces given
            moveSpaces = intCommand(val);
            //if moveSpaces is negative, it generates invalid input
            if(moveSpaces <=0) {
                if(val.equals("MENU") || val.equals("menu")){}
                else
                    val = "x";
            } else {
                val = "M";
            }
        }



        // these are all the switch cases
        switch (val) {
            case "n":
            case "N":
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
                initializeSystem(moveSpaces);
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
                    moveRobot(moveSpaces);
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
        
            case "-1":
                System.out.println("You have selected nothing");
                break;
            default:
                System.out.println("Sorry I can not understand. Please choose a command from the menu");
            }
        if(!history){
            ui();
        }
        return false;
        
        
    

    }

    // this function is to covert the string coommand to integer and extract only the integer to move spaces
    public static int intCommand(String val){
        val = val.replace(" ", "");
        int len = val.length();
        val = val.substring(1, len);
        int intValue = Integer.parseInt(val);
        return intValue;
       
    }

    //checks to see if the movespaces is numeric
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

        //get direction of the pen
        String direction = robot.getDirection();

        //print position
        System.out.println("Position: " + y + ", " + x + " - Pen: " + pen + " - Facing: " + direction);
    }

    public static void printTable() {
        if(!flag)
            commands.add("p");
        //System.out.println("Printing table...");
        table.printTable(robot.getCoordinates(), robot.getPenState());
    }


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
            System.out.println("Jarvis is Moving...");
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
            System.out.println(" Jarvis Can not move " + steps + " in the " + direction + " direction, there is no more room for Jarvis to move");
        }
    }

    //turn the robot left
    public static void turnLeft() {
        if(!flag)
            commands.add("l");
        System.out.println("Jarvis Turning left...");
        String currentDirection = robot.getDirection();

        //turn correct direction, depending on current direction
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


    public static void initializeSystem(int size) {
        if(!flag)
            commands.add("i " + size);

        if(size < 2){
            System.out.println("Please choose a size bigger or equal to 2");
        } else{
            System.out.println("Jarvis is Initializing with size: " + size);
            table = new Table(size,size);
            robot = Singleton.getInstance();
            robot.reinitialize();
            initialized = true;
        }
    }
}
