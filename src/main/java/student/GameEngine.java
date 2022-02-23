package student;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import student.server.Direction;
import student.server.Layout;
import student.server.Room;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;


public class GameEngine {
    private String input;
    private Layout gameLayout;
    private Room currentRoom;
    private static HashMap <String, ArrayList<String>> itemMap;
    private static HashMap <String, ArrayList<String>> directionMap;
    private ArrayList<String> itemsPickedUp;
    private ArrayList<String> roomsTraversed;


    /**
     * Function that sets up maps and variables.
     * itemsMap: Loads roomName (String) and items part of the room into a HashMap.
     * itemsPicked: Set to none as no items have been picked initially, tracks items picked up for inventory.
     * directionMap: Loads roomName (String) and directions part of the room into a HashMap.
     * roomsTraversed: Tracks travel history.
     * @throws Exception thrown when JSON file is not formatted correctly.
     */
    public GameEngine() {
        try {
            Gson gson = new Gson();
            Reader jsonReader;
            jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/monstrocity.json"));
            gameLayout = gson.fromJson(jsonReader, Layout.class);

            currentRoom = gameLayout.getRoomStringAsRoomObject(gameLayout.getStartRoom());
            itemMap = new HashMap<>();
            directionMap = new HashMap<>();
            itemsPickedUp = new ArrayList<>();
            roomsTraversed = new ArrayList<>();

            int numberOfRooms = gameLayout.getRooms().length;
            for (int i = 0; i < numberOfRooms; i++) {
                String roomName = gameLayout. getRooms()[i].getRoomName();
                String itemsInRoom = gameLayout.getRooms()[i].getItems();
                int numberOfDirections = gameLayout.getRooms()[i].getDirections().length;
                for (int j = 0; j < numberOfDirections; j++) {
                    String directions = gameLayout.getRooms()[i].getDirections()[j].getDirection();

                    ArrayList<String> itemsAsArray = new ArrayList<>();
                    ArrayList<String> directionsAsArray = new ArrayList<>();
                    itemsAsArray.add(itemsInRoom);
                    directionsAsArray.add(directions);

                    itemMap.put(roomName, itemsAsArray);
                    directionMap.put(roomName, directionsAsArray);
                }
            }
        } catch (JsonParseException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game
     * @param userInput the command the user inputs
     * @return returns message corresponding to user input
     */
    public String startGame(String userInput) {
        input = userInput.toLowerCase();
        String[] command = input.split(" +");

        if (getCurrentRoom().getRoomName().equals(getGameLayout().getEndRoom())
            && getItemsPickedUp().contains("icecream")) {
            return "\n" + "Congratulations you have won the game!";
        }

        else if (command[0].equals("go")) {
            if (command.length == 1) {
                return "Please specify the direction of movement";
            }
            if (command.length > 2) {
                return "Please specify one direction of movement";
            }
            if (command.length == 2) {
                String direction = command[1];
                return (checkPossibleDirection(direction) + "\n" + getCurrentRoomInformation() +
                        getItemInRoomInformation() + "\n");
            }
        }

        else if (command[0].equals("take")) {
            if (command.length == 1) {
                return "Please specify the item to take";
            }
            if (command.length > 2) {
                return "Please specify ONE item to take";
            }
            if (command.length == 2) {
                String item = command[1];
                return takeItem(item);

            }
        }

        else if (command[0].equals("drop")) {
            if (command.length == 1) {
                return "Please specify item to drop";
            }
            if (command.length > 2) {
                return "Please specify ONE item to drop";
            }
            if (command.length == 2) {
                String item = command[1];
                return dropItem(item);
            }
        }

        else if (userInput.equals("examine")) {
            return "\n" + getCurrentRoomInformation() + getItemInRoomInformation() + "\n";
        }

        else if (userInput.equals("travel history")) {
            return getRoomsTraversed();
        }

        else if (userInput.equals("inventory")) {
            return getInventory();
        }

        else if (userInput.equals("info")) {
            return getStartUpInformation();
        }

        else if (userInput.equals("exit") || userInput.equals("quit")) {
            return "Thanks for playing!";
        }

        else {
            return invalidCommand(userInput);
        }

        return "";
    }

    /**
     * Functions which returns invalid command message
     * @param userInput what the user inputs
     * @return returns an error message
     */
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

    /**
     * Getter for currentRoom
     * @return returns currentRoom (which was found in above function)
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Getter for the gameLayout (utilised in Main.java)
     * @return returns the gameLayout
     */
    public Layout getGameLayout() {
        return gameLayout;
    }

    /**
     * ArrayList for storing items picked up by user during game
     * @return returns itemsPicked by user
     */
    public ArrayList<String> getItemsPickedUp() {
        return itemsPickedUp;
    }

    /**
     * Getter for current room information
     * @return returns current room information, utilises Room class for getRoomInformation()
     */
    public String getCurrentRoomInformation() {
        return getRoomInformation();
    }

    /**
     * Function that returns information about the room.
     * @return returns the current room information, which includes room name and directions.
     */
    public String getRoomInformation() {
        return "You are now at: " + getCurrentRoom().getRoomName() +  "\n" +
                getCurrentRoom().getDescription() + "\n" + getDirectionInformation() + "\n";
    }

    /**
     * Function that returns information about directions from a room
     * @return returns the directions from which the user can go to from the current room
     */
    public String getDirectionInformation() {
        StringBuilder directionInfo = new StringBuilder();
        directionInfo.append("From here, you can go: ");

        if (getCurrentRoom().getDirections() == null || getCurrentRoom().getDirections().length == 0) {
            directionInfo.append("nowhere.");
        } else if (getCurrentRoom().getDirections().length == 1) {
            directionInfo.append(getCurrentRoom().getDirections()[0].getDirection());
        } else {
            directionInfo.append(getCurrentRoom().getDirections()[0].getDirection());
            for (int i = 1; i < getCurrentRoom().getDirections().length - 1; i++) {
                directionInfo.append(", ");
                directionInfo.append(getCurrentRoom().getDirections()[i].getDirection());
            }

            directionInfo.append(", or ");
            directionInfo.append(getCurrentRoom().getDirections()
                    [getCurrentRoom().getDirections().length - 1].getDirection());
        }

        return directionInfo.toString();
    }

    /**
     * Getter for items in a room
     * @return returns the item that is in the currentRoom
     */
    public String getItemInRoomInformation() {
        return getItemsInRoom(currentRoom.getRoomName());
    }

    /**
     * Function which shows user what items are in the room
     * @param roomName the roomName user is looking for item
     * @return returns message about what items are in the room
     */
    public String getItemsInRoom(String roomName) {
        if (roomName == null || roomName.length() == 0) {
            throw new IllegalArgumentException("Room is invalid");
        }

        if (itemMap.get(getCurrentRoom().getRoomName()).isEmpty()) {
            return "There are no items in this room!";
        }

        if (itemMap.containsKey(getCurrentRoom().getRoomName())) {
            return "Items visible: " + itemMap.get(getCurrentRoom().getRoomName());
        }

        return "";
    }

    /**
     * Function which checks if user can go specified direction
     * @param directionName the direction user wants to go
     * @return allows user to move in direction, or returns a message if it is not a valid direction
     */
    public String checkPossibleDirection(String directionName) {
        if (directionName == null || directionName.length() == 0) {
            throw new IllegalArgumentException("Direction is invalid");
        }

        Direction[] playerDirections = currentRoom.getDirections();
        for (Direction direction : playerDirections) {
            if (direction.getDirection().equalsIgnoreCase(directionName)) {
                currentRoom = gameLayout.getRoomStringAsRoomObject(direction.getRoom());
                roomsTraversed.add(getCurrentRoom().getRoomName());
                return "";
            }
        }
        return "\n" + "'" + directionName + "' is not a valid direction to go.";
    }

    /**
     * Function which shows user the items they have picked up.
     * @return a list of items the user has picked up.
     */
    public String getInventory() {
        StringBuilder getInventory = new StringBuilder();
        getInventory.append("Here are the item/s you have picked up: ");

        if (itemsPickedUp.size() == 0) {
            getInventory.append(" ");
        } else {
            arrayListHelper(getInventory, itemsPickedUp);
        }
        return getInventory.toString();
    }

    /**
     * Function for user to take an item
     * @param item  the user wants to take
     * @return returns message about whether user was successful in taking item or not
     * @throws IllegalArgumentException if item is null or there is no item (JSON error, not user)
     */
    public String takeItem(String item) {
        if (item == null || item.length() == 0) {
            throw new IllegalArgumentException("Invalid item");
        }

        // checking map for currentRoom status
        if (itemMap.containsKey(getCurrentRoom().getRoomName())) {
            // if room does not have item
            if (itemMap.get(currentRoom.getRoomName()).size() == 0) {
                return "This room is empty. There is no '" + item + "' to take!" + "\n";
            }
        }

        if (item.equals("gold") && (!(itemsPickedUp.contains("key")))) {
            return "You're missing an item to take the " + item + "!" + "\n";
        }

        // if room has item
        else if (itemMap.get(currentRoom.getRoomName()).get(0).equals(item)) {
            itemsPickedUp.add(item);
            itemMap.get(getCurrentRoom().getRoomName()).remove(0);
            return "You have taken '" + item + "'!" + "\n";
        }

        // if room does not have item
        else {
            return "There is no item '" + item + "' in " + getCurrentRoom().getRoomName() + "\n";
        }
    }

    /**
     * Function for user to drop an item
     * @param item item the user wants to drop
     * @return returns message about whether use was successul in dropping item or not
     * @throws IllegalArgumentException if item is null or there is no item (JSON error, not user)
     */
    public String dropItem(String item) {
        if (item == null || item.length() == 0) {
            throw new IllegalArgumentException("Item is invalid");
        }

        if (itemMap.containsKey(getCurrentRoom().getRoomName())) {
            if (itemsPickedUp.contains(item)) {
                itemsPickedUp.remove(item);
                itemMap.get(getCurrentRoom().getRoomName()).add(item);
                return "You have successfully dropped '" + item + "' " + "\n";
            }
        }

        return "You dont have the item '" + item + "' to drop!";
    }

    private void arrayListHelper(StringBuilder stringBuilder, ArrayList<String> arrayList) {
        if (arrayList.size() == 1) {
            stringBuilder.append(arrayList.get(0));
        } else {
            stringBuilder.append(arrayList.get(0));
            for (int item = 1; item < arrayList.size() - 1; item++) {
                stringBuilder.append(", ");
                stringBuilder.append(arrayList.get(item));
            }

            stringBuilder.append(", and ");
            stringBuilder.append(arrayList.get(arrayList.size() - 1));
        }
    }

    /**
     * Function utilised by MonstroCityServce.java to check if game is won or not
     * @return Returns true if game is won, false otherwise
     */
    public boolean gameIsWon() {
        if (currentRoom.getRoomName().equals("Puzzle Palace")
                && itemsPickedUp.contains("icecream")) {
            return true;
        } else {
            return false;
        }
    }

    public String getWinningMessage() {
        return "Congratulations you have won the game!";
    }

    public HashMap<String, ArrayList<String>> getDirectionMap() {
        return directionMap;
    }

    public HashMap<String,ArrayList<String>> getItemMap() {
        return itemMap;
    }

    public ArrayList<String> getRoomsTraversedMap() {
        return roomsTraversed;
    }

    public String getItemInRoomInfo() {
        return getItemsInRoom(currentRoom.getRoomName());
    }

    public String getRoomsTraversed() {
        StringBuilder roomsGone = new StringBuilder();

        if (roomsTraversed.size() == 0) {
            roomsGone.append("Your travel history in Brightvale is: ");
            roomsGone.append("Brightvale Entrance.");
        } else {
            roomsGone.append("Your travel history in Brightvale is: Brightvale Entrance, ");
            arrayListHelper(roomsGone, roomsTraversed);
        }
        return roomsGone.toString();
    }
}
