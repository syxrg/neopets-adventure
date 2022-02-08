package student.server;

public class Room {
    private String name;
    private String description;
    private String items;
    private Direction[] directions;

    public Room (String nameInput, String descriptionInput, String itemsInput,
                 Direction[] directionsInput) {
        name = nameInput;
        description = descriptionInput;
        items = itemsInput;
        directions = directionsInput;
    }

    public String getRoomName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItems() {
        return items;
    }
    public Direction[] getDirections() {
        return directions;
    }
}
