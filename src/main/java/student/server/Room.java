package student.server;

public class Room {
    private String name;
    private String description;
    private String item;
    private Direction[] directions;

    public Room (String nameInput, String descriptionInput, String itemsInput,
                 Direction[] directionsInput) {
        name = nameInput;
        description = descriptionInput;
        item = itemsInput;
        directions = directionsInput;
    }

    public String getRoomName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItems() {
        return item;
    }
    public Direction[] getDirections() {
        return directions;
    }
}
