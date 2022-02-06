package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class GameEngine {
    private String input;
    private static HashMap <String, ArrayList<String>> itemMap;
    private static HashMap <String, ArrayList<String>> directionMap;
    private ArrayList<String> itemsPickedUp;
    private ArrayList<String> roomsTraversed;

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
        return invalidCommand(userInput);
    }

    public String invalidCommand (String userInput) {
        return "'" + userInput.trim() + "' is not a valid command!";
    }

    /**
     * Function to return start up information to user.
     * @return start up information, which includes commands for the game.
     */
    public String getStartUpInformation() {
        StringBuilder startUpInfo = new StringBuilder();
        startUpInfo.append("\n" + "The following commands can interact with the map: GO, TAKE, DROP" + "\n");
        startUpInfo.append("To check room information, use command: EXAMINE" + "\n");
        startUpInfo.append("To check rooms visited, use command: TRAVEL HISTORY" + "\n");
        startUpInfo.append("To check items picked up, use command: INVENTORY" + "\n");
        startUpInfo.append("To leave the game at any time, use command: QUIT or EXIT" + "\n");
        startUpInfo.append("Pick up the correct item and head to the final destination to " +
                "win the game!" + "\n");
        return startUpInfo.toString();
    }
}
