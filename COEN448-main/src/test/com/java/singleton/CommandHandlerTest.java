package com.java.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {

    CommandHandler commandHandler;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

 
    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
    String table(boolean flag){
        //get expected output
        StringBuilder output = new StringBuilder();

        int[][] tableArray;
        //initialize array
        tableArray = new int[10][10];

        //initialize table with 0s
        for (int[] ints : tableArray) {
            Arrays.fill(ints, 0);
        }

        if(flag){
            tableArray[5][5]=1;
        }

        //get initial table
        for (int row = tableArray.length-1; row >= 0; row--) {
            output.append(row + "| ");
            for (int col = 0; col < tableArray[row].length; col++) {
                if (row == 0 && col == 0)
                    output.append("↑ ");
                else if (tableArray[row][col] == 0)
                    output.append("  ");
                else
                    output.append("* ");
            }
            output.append("\n");
        }
        output.append("  ");
        for (int col = 0; col < tableArray.length; col++) {
            output.append("__");
        }
        output.append("\n   ");
        for (int col = 0; col < tableArray.length; col++) {
            output.append(col + " ");
        }
        return output.toString();
    }
    
    
    
//Test case 1: Testing "P" and "p" commands
    
    /*Test Case 1.1: Test when "P" is given as input
    Input: "P"
    Expected Output: The current state of the system (the N by N array with indices)
    Test Case 1.2: Test when "p" is given as input
    Input: "p"
    Expected Output: The current state of the system (the N by N array with indices)
*/

@Test
void test_handleInput_PrintArrayCommand() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    
    outputStreamCaptor.reset();
    CommandHandler.handleInput("P", true);
    assertEquals(table(false), outputStreamCaptor.toString());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("p", true);
    assertEquals(table(false), outputStreamCaptor.toString());
}

//Test case 2: Testing "I n" and "i n" commands
/*Test Case 2.1: Test when "I n" is given as input
Input: "I 5"
Expected Output: "Initializing with size: 5"

Test Case 2.2: Test when "i" is given as input
Input: "i"
Expected Output: "Sorry, I can not understand. Please choose a command from the menu"
Test Case 2.3: Test when "i 1" is given as input (checking the minimum value of 2 for initialization)
Input: "i 1"
Expected Output: "Sorry, I can not understand. Please choose a command from the menu"*/


@Test
void test_handleInput_InitializeSystemCommand() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    outputStreamCaptor.reset();
    CommandHandler.handleInput("I 5", true);
    assertEquals("Initializing with size: 5", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    CommandHandler.handleInput("I", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("I 1", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

    
}
//Test case 3: Testing "U" and "u" commands

/*Test Case 3.1: Test when "U" is given as input
Input: "U"
Expected Output: "Pen direction already up"
Test Case 3.2: Test when "u" is given as input
Input: "u"
Expected Output: "Pen direction already up"*/


@Test
void test_handleInput_PenUpCommand() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
    CommandHandler.handleInput("u", true);
    assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("U", true);
    assertEquals("Pen direction already up", outputStreamCaptor.toString().trim());
}

//Test case 4: Testing "D" and "d" commands

/*Test Case 4.1: Test when "D" is given as input
Input: "D"
Expected Output: "Pen direction going down..."
Test Case 4.2: Test when "d" is given as input
Input: "d"
Expected Output: "Pen direction already down"
*/
@Test
void test_handleInput_PenDownCommand() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
    CommandHandler.handleInput("d", true);
    assertEquals("Pen direction going down...", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("D", true);
    assertEquals("Pen direction already down", outputStreamCaptor.toString().trim());
}
//Test case 5: Testing "R" and "r" commands

/*Test Case 5.1: Test when "R" is given as input
Input: "R"
Expected Output: "Jarvis Turning right..."
Test Case 5.2: Test when "r" is given as input
Input: "r"
Expected Output: "Jarvis Turning right..."
*/
@Test
void test_handleInput_TurnRightCommand() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
    CommandHandler.handleInput("r", true);
    assertEquals("Jarvis Turning right...", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("R", true);
    assertEquals("Jarvis Turning right...", outputStreamCaptor.toString().trim());
}
//Test case 6: Testing "L" and "l" commands

/*Test Case 6.1: Test when "L" is given as input
Input: "L"
Expected Output: "Jarvis Turning left..."
Test Case 6.2: Test when "l" is given as input
Input: "l"
Expected Output: "Jarvis Turning left..."
}*/
@Test
void test_handleInput_TurnLeftCommand() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
    CommandHandler.handleInput("l", true);
    assertEquals("Jarvis Turning left...", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("L", true);
    assertEquals("Jarvis Turning left...", outputStreamCaptor.toString().trim());
}

//Test case 7: Testing "M s" and "m s" commands

/*Test Case 7.1: Test when "M s" is given as input
Input: "M 2"
Expected Output: "Jarvis Moving..."


Test Case 7.2: Test when "M" is given as input 0 as input 
Input: "M 0"
Expected Output: "Sorry, I can not understand. Please choose a command from the menu"
Test Case 7.3: Test when "M" is given as input 10 as input 
Input: "M 10"
Expected Output: "Can not move 10 in the north direction, the robot will fall off the table"
*/

@Test
void test_handleInput_MoveForwardCommand() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
    CommandHandler.handleInput("M 2", true);
    assertEquals("Jarvis Moving...", outputStreamCaptor.toString().trim());

     outputStreamCaptor.reset();
    CommandHandler.handleInput("m 0", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("m 10", true);
    assertEquals("Can not move 10 in the north direction, the robot will fall off the table", outputStreamCaptor.toString().trim());
}

//Test case 8: Testing The initial position of the robot after initialization
/*Test Case 8.1: Test whenC"given as input
Input: "C
Expected Output: "Position: 0, 0 - Pen: up - Facing: north
*Test Case 8.2: Test when "c"given as input
Input: "c"
Expected Output: "Position: 0, 0 - Pen: up - Facing: north
*/
@Test
void test_handleInput_PrintInitialPosition() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    String expectedOutput = "Position: 0, 0 - Pen: up - Facing: north";

    outputStreamCaptor.reset();
    CommandHandler.handleInput("C", true);
    assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("c", true);
    assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
}


//Test case 9: Testing The initial position of the robot after initialization
/*Test Case 9.1: Test when "Q"given as input
Input: "Q"
Expected Output: assertTrue(CommandHandler.handleInput("Q", true)); 
*Test Case 8.2: Test whenC"given as input
Input: "q"
Expected Output:  assertTrue(CommandHandler.handleInput("q", true));
*/


@Test
void test_handleInput_StopProgramCommand() {
    outputStreamCaptor.reset();
    assertTrue(CommandHandler.handleInput("Q", true));

    outputStreamCaptor.reset();
    assertTrue(CommandHandler.handleInput("q", true));
}


//Test case 10: Testing all possible invalid input after initialization
@Test
void test_handleInput_InvalidInput() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
    CommandHandler.handleInput("", true);
    assertEquals("User selected Nothing", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("i", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    CommandHandler.handleInput("m", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    CommandHandler.handleInput("u 1", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    CommandHandler.handleInput("d 1", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("l 1", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    CommandHandler.handleInput("r 1", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

    outputStreamCaptor.reset();
    CommandHandler.handleInput("c 1", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    CommandHandler.handleInput("p 1", true);
    assertEquals("Sorry, I can not understand. Please choose a command from the menu", outputStreamCaptor.toString().trim());

}

//Test case 11: Testing initialization less than 2 
@Test
void test_handleInput_InitializationLessThan2() {

	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    outputStreamCaptor.reset();
    commandHandler.initializeSystem(1);
  assertEquals("Please choose a size bigger or equal to 2", outputStreamCaptor.toString().trim());

  
}

//Test case 12: Testing Menu as input
/*Input: "MENU"
Expected Output:"Enter 'Q' to close program\n"
            +"All Possible commands:\n" +
           "I n: Initialize the system\n" +
           "U: Pen Up\n" +
           "D: Pen Down\n" +
           "R: Turn Right\n" +
           "L: Turn Left\n" +
           "M s: Move forward s spaces\n" +
           "P: Print the table\n" +
           "C: Print current position of the pen\n" +
           "H: Replay all the commands entered\n" +
           "Q: Stop the program";*/


@Test
void test_handleInput_menu() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    outputStreamCaptor.reset();
    CommandHandler.handleInput("MENU", true);
    String output = "Enter 'Q' to close program\n"
            +"All Possible commands:\n" +
           "I n: Initialize the system\n" +
           "U: Pen Up\n" +
           "D: Pen Down\n" +
           "R: Turn Right\n" +
           "L: Turn Left\n" +
           "M s: Move forward s spaces\n" +
           "P: Print the table\n" +
           "C: Print current position of the pen\n" +
           "H: Replay all the commands entered\n" +
           "Q: Stop the program";
    assertEquals(output.replaceAll("\n", "").replaceAll("\r", ""), outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
}


//Test case 13: Testing intValueGiven function
@Test
void intValueGiven() {
    //gets int value after a letter in a string
    String val = "i10";
    String val1 = "i";
    String val2 = "i 5";
    String val3 = "i -5";
    assertAll(() -> assertEquals(10, CommandHandler.intValueGiven(val)), //test valid number with no space
            () -> assertEquals(-1,commandHandler.intValueGiven(val1)),   //test no number given
            () -> assertEquals(5,commandHandler.intValueGiven(val2)),    //test valid number with space
            () -> assertEquals(-1,commandHandler.intValueGiven(val3)));  //test invalid number
}

//Test case 14: Testing removeBlankspace function
@Test
void removeBlankSpace() {
    String val = " I";
    StringBuilder sb = new StringBuilder(val);

    assertEquals("I",commandHandler.removeBlankSpace(sb));
}

//Test case 15: Testing isNumeric function
@Test
void isNumeric() {
    String numb = "10";
    String notNumb = "A";
    assertAll(() -> assertTrue(commandHandler.isNumeric(numb)),    //test valid number
            () -> assertFalse(commandHandler.isNumeric(notNumb))); //test invalid number
}


//Test case 16: Testing printPosition() function
@Test
void test_printPosition() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
String expectedOutput1 = "Position: 0, 0 - Pen: up - Facing: north";
String expectedOutput2 = "Position: 0, 0 - Pen: down - Facing: north";
String expectedOutput3 = "Position: 0, 0 - Pen: down - Facing: east";
//Verify initial position
outputStreamCaptor.reset();
commandHandler.printPosition();
assertEquals(expectedOutput1, outputStreamCaptor.toString().trim());

//Verify position with pen down
outputStreamCaptor.reset();
commandHandler.penDown();
outputStreamCaptor.reset();
commandHandler.printPosition();
assertEquals(expectedOutput2, outputStreamCaptor.toString().trim());

//Verify position facing east
outputStreamCaptor.reset();
commandHandler.turnRight();
outputStreamCaptor.reset();
commandHandler.printPosition();
assertEquals(expectedOutput3, outputStreamCaptor.toString().trim());
}


//Test case 17: Testing turnLeft() function
@Test
void test_turnLeft() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
commandHandler.turnLeft();
assertEquals("west", commandHandler.robot.getDirection()); //test left turn if initially facing west
commandHandler.turnLeft();
assertEquals("south", commandHandler.robot.getDirection()); //test left turn if initially facing south
commandHandler.turnLeft();
assertEquals("east", commandHandler.robot.getDirection()); //test left turn if initially facing east
commandHandler.turnLeft();
assertEquals("north", commandHandler.robot.getDirection()); //test left turn if initially facing north
}

//Test case 18: Testing turnRight() function
@Test
void turnRight() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    outputStreamCaptor.reset();
    commandHandler.turnRight();
    assertEquals("east", commandHandler.robot.getDirection());  //test right turn if initially facing east
    commandHandler.turnRight();
    assertEquals("south", commandHandler.robot.getDirection());  //test right turn if initially facing south
    commandHandler.turnRight();
    assertEquals("west", commandHandler.robot.getDirection());  //test right turn if initially facing west
    commandHandler.turnRight();
    assertEquals("north", commandHandler.robot.getDirection());  //test right turn if initially facing north

}
//Test case 19: Testing printTable function
@Test
void printTable() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    commandHandler.initializeSystem(10);
    String output  = table(true);
    outputStreamCaptor.reset();
    Point point = new Point(5,5);
    commandHandler.table.writeTable(point,true);
    commandHandler.printTable();
    assertEquals(output, outputStreamCaptor.toString());
}
//Test case 20: Testing moveRobot function
@Test
void moveRobot() {
    Point coo1 = commandHandler.robot.getCoordinates();

    coo1.move(coo1.x+1,coo1.y);
    commandHandler.moveRobot(1);
    assertEquals(coo1, commandHandler.robot.getCoordinates());

    commandHandler.turnRight();
    coo1.move(coo1.x,coo1.y+1);
    commandHandler.moveRobot(1);
    assertEquals(coo1, commandHandler.robot.getCoordinates());

    commandHandler.turnRight();
    coo1.move(coo1.x-1,coo1.y);
    commandHandler.moveRobot(1);
    assertEquals(coo1, commandHandler.robot.getCoordinates());

    commandHandler.turnRight();
    coo1.move(coo1.x,coo1.y-1);
    commandHandler.moveRobot(1);
    assertEquals(coo1, commandHandler.robot.getCoordinates());
}

//Test case 21: Testing all possible inputs without initializing
@Test
void test_handleInput_InvalidInput1() {
	commandHandler = new CommandHandler();
    System.setOut(new PrintStream(outputStreamCaptor));
    outputStreamCaptor.reset();
    commandHandler.handleInput("U",true);
    assertEquals("Please initialize the system first", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    commandHandler.handleInput("D",true);
    assertEquals("Please initialize the system first", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    commandHandler.handleInput("R",true);
    assertEquals("Please initialize the system first", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    commandHandler.handleInput("L",true);
    assertEquals("Please initialize the system first", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    commandHandler.handleInput("M 10",true);
    assertEquals("Please initialize the system first", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    commandHandler.handleInput("P",true);
    assertEquals("Please initialize the system first", outputStreamCaptor.toString().trim());
    
    outputStreamCaptor.reset();
    commandHandler.handleInput("C",true);
    assertEquals("Please initialize the system first", outputStreamCaptor.toString().trim());
}

}
