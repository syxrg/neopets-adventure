package student;

import java.util.Locale;

public class GameEngine {
    private String input;

    public String startGame(String userInput) {
        input = userInput.toLowerCase();
        String[] command = input.split(" +");

        if (command[0].equals("go")) {
            if (command.length == 1) {
               return "Please specify the direction of movement";
            }
            if (command.length > 2) {
                return "Please specify one direction of movement";
            }
            if (command.length == 2) {
                return "EDIT LATER ok moved here";
            }
        }
        if (command[0].equals("take")) {
            if (command.length == 1) {
                return "Please specify the item to take";
            }
            if (command.length > 2) {
                return "Please specify ONE item to take";
            }
            if (command.length == 2) {
                return "EDIT LATER ok item taken";
            }
        }
        if (command[0].equals("drop")) {
            if (command.length == 1) {
                return "Please specify item to drop";
            }
            if (command.length > 2) {
                return "Please specify ONE item to drop";
            }
            if (command.length == 2) {
                return "EDIT LATER ok item dropped";
            }
        }
        if (userInput.equals("examine")) {
            return "INSERT ROOM INFO HERE";
        }
        if (userInput.equals("travel history")) {
            return "INSERT TRAVEL HISTORY";
        }
        if (userInput.equals("inventory")) {
            return "INVENTORY";
        }
        if (userInput.equals("info")) {
            return "INFO";
        }
        if (userInput.equals("exit") || userInput.equals("quit")) {
            return "Thanks for playing!";
        }
        else {
            return invalidCommand(userInput);
        }
    }

    public String invalidCommand(String userInput) {
        return "'" + userInput.trim() + "' is not a valid command!";
    }

}
