package student.server;

import student.GameEngine;

import java.util.Scanner;

public class CommandLine {
    private Scanner scanner;
    private GameEngine game;
    private final String GAME_WON = "\n" + "Congratulations you've won the game!";
    private final String GAME_EXIT = "Thanks for playing!";

    public CommandLine() {
       game = new GameEngine();
       scanner = new Scanner(System.in);
    }

    public void gamePlay() {
        System.out.println("Welcome to the Monstro City Map Game!");
        System.out.println(game.getStartUpInformation());
        System.out.println(game.getCurrentRoomInformation() + game.getItemInRoomInformation());

        boolean gameIsRunning = true;
        while (gameIsRunning) {

            System.out.print("> "); // shows user where to type, after ">"

            String input = scanner.nextLine().trim();
            String output = game.startGame(input);
            System.out.println(output);

            if (output.equals(GAME_EXIT)) {
                gameIsRunning = false;
            }

            if (output.equals(GAME_WON)) {
                gameIsRunning = false;

            }
        }
    }
}

