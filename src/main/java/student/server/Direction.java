package student.server;

public class Direction {

    private String direction;
    private String room;

    /**
     * Constructor for Direction object
     * @param directionInput the direction
     * @param roomInput the room
     */
    public Direction(String directionInput, String roomInput) {
        direction = directionInput;
        room = roomInput;
    }

    /**
     * Getter for direction
     * @return returns the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Getter for room
     * @return returns the room
     */
    public String getRoom() {
        return room;
    }
}
