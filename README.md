#Software-Testing-Project

#COEN6761\_ Project(RunTime Terror)

# Test Case Explaination https://docs.google.com/document/d/1X6kOLsRICKV7PPZM78SYh-ggOSrMgwFEEKo0JBpU6Og/edit?usp=sharing

**Contributors**

Tauhid Ul Islam.

Nabeel Ahmed Ghazali.

Muneeb Khan.

Mohammad Abdul Wasay Ejaz.

#How to run the program

In the src folder run the Main.java in the IDE(for the project we have used Eclipse and VScode to as IDE)
All the dependencies are listed in the pom.xml

#Objective

The objective of this project is to utilize the software testing techniques thought in the course. For the first part of the project we have developed the requirements mentioned in the project description and have created unit tests to test the specific functionality of the requirements.

##Project Description:

In the develpoment part of the project we have write code for a robot which can walk around the room and follow certain command through a java file.

We have named our robot Jarvis and The robot have the ability of holding one pen and move along the coordinate accoring to the commands provided(discussed details of th command in the following sections).The robots holds the pen in either UP or down position.When the pen is up the robots moves freely without writing anything. The total room is mapped using an N by N array. all the values in the array is initiated by zero and initially the position of the robot is in [0,0] facing north and the initial position of the pen is UP.

### Commands and their detailed meaning

[MENU|menu] To showup the total menu

[U|u] Pen up

[D|d] Pen down

[R|r] Turn right

[L|l] Turn left

[M s|m s] Move forward s spaces (s is a non-negative integer)

[P|p] Print the N by N array and display the indices

[C|c] Print current position of the pen and whether it is up or down and its facing direction

[Q|q] Stop the program

[I n|i n] Initialize the system (n is an integer and size of the row and column of intitail floor array.)
