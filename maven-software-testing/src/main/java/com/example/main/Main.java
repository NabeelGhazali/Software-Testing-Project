package com.example.main;

import com.example.Singleton.CommandHandler;

public class Main {
    public static void main(String[] args) {
        //start teh commandHandler and display teh UI
        System.out.println("Hi my name is JARVIS, I am trapped in a room and only you can control me \n");
        System.out.println("Press 'N' to look at all the commands you can use to control me \n");

        CommandHandler commandHandler = new CommandHandler();
        commandHandler.ui();

        

    }
}
