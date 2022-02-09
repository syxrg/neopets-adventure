package student.server;

public class Layout {
    private String startingRoom;
    private String endingRoom;
    private Room[] rooms;

    public Layout(String startRoomInput, String endRoomInput, Room[] roomsInput) {
        startingRoom = startRoomInput;
        endingRoom = endRoomInput;
        rooms = roomsInput;
    }

    public String getStartRoom() {
        return startingRoom;
    }

    public String getEndRoom() {
        return endingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }


    /**
     * Method which returns the roomName (as a string) to a Room Object.
     * @param roomName name of the room
     * @return a Room object of room name.
     * @throws IllegalArgumentException when string parameter is invalid.
     */
    public Room getRoomStringAsRoomObject(String roomName) {
        if (roomName == null || roomName.length() == 0) {
            throw new IllegalArgumentException("Room name is not valid");
        }

        boolean roomFound = false;
        Room roomLookingFor = new Room("", null,null,null);

        for (Room roomBeingLookedFor : rooms) {
            if (roomName.equals(roomBeingLookedFor.getRoomName())) {
                roomLookingFor = roomBeingLookedFor;
                roomFound = true;
            }
        }
        if (!(roomFound)) {
            throw new IllegalArgumentException("Room does not exist");
        }
        return roomLookingFor;
    }
}
