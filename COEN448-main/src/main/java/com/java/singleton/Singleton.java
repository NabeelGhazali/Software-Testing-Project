package com.java.singleton;

import java.awt.*;

public class Singleton {
    private static Singleton robot;
    private boolean penState; //Pen state false means the pen is UP and Pen state true means Pen is Down
    private Point coordinates; //coordinate of the robot on table(floor)
    private String direction; // 4 possible directions

    //constructor
    public Singleton(){
       
        this.penState = false;
        this.coordinates = new Point(0,0);
        this.direction = "north";
    }

    //current instance of the robot
    public static Singleton getInstance(){
        if (robot == null)
            robot = new Singleton();
        return robot;
    }

    //get
    public String getDirection() {
        return this.direction;
    }
    public boolean getPenState(){
        return this.penState;
    }
    public Point getCoordinates(){
        return this.coordinates;
    }

    //set
    public void setDirectionNorth(){
        this.direction = "north";
    }
    public void setDirectionSouth(){
        this.direction = "south";
    }
    public void setDirectionWest(){
        this.direction = "west";
    }
    public void setDirectionEast(){
        this.direction = "east";
    }
   
    public void setPenState(boolean state){
        this.penState = state;
    }
    public void setCoordinates(Point p){
        this.coordinates = p;
    }

    //reinitialize the robot to the initial point
    public void reinitialize(){
        this.penState = false;
        this.coordinates = new Point(0,0);
        this.direction = "north";
    }
}