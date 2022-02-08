package student.server;

public class Room {
    private String name;
    private String description;
    private String item;
    private Direction[] directions;

    public Room (String nameInput, String descriptionInput, String itemInput,
                 Direction[] directionsInput) {
        name = nameInput;
        description = descriptionInput;
        item = itemInput;
        directions = directionsInput;
    }

    public String getRoomName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Direction[] getDirections() {
        return directions;
    }
}
