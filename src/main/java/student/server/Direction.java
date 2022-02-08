package student.server;

public class Direction {

    private String directionName;
    private String room;

    /**
     * Constructor for Direction object
     * @param directionNameInput the direction
     * @param roomInput the room
     */
    public Direction(String directionNameInput, String roomInput) {
        directionName = directionNameInput;
        room = roomInput;
    }

    /**
     * Getter for direction
     * @return returns the direction
     */
    public String getDirection() {
        return directionName;
    }

    /**
     * Getter for room
     * @return returns the room
     */
    public String getRoom() {
        return room;
    }
}
