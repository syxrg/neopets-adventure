package student.server;

public class Room {
    private String name;
    private String description;
    private String item;
    private Direction[] directions;
    private String image;

    public Room (String nameInput, String descriptionInput, String itemsInput,
                 Direction[] directionsInput, String imageInput) {
        name = nameInput;
        description = descriptionInput;
        item = itemsInput;
        directions = directionsInput;
        image = imageInput;
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

    public String getImage() {
        return image;
    }
}
