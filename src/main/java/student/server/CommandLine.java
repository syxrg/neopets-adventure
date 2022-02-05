package student.server;

import student.GameEngine;

import java.util.Scanner;

public class CommandLine {
    private Scanner scanner;
    private GameEngine game;
    private final String GAME_WON = "\n" + "Congratulations you've won the game!";
    private final String GAME_EXIT = "Thanks for playing!";

    public CommandLine() throws Exception {
       game = new GameEngine();
       scanner = new Scanner(System.in);
    }

    public void gamePlay() throws Exception {
        System.out.println("hello");
    }
}

