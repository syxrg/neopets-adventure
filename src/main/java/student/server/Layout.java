package student.server;

public class Layout {
    private String startRoom;
    private String endRoom;
    private Room[] rooms;

    public Layout(String startRoomInput, String endRoomInput, Room[] roomsInput) {
        startRoom = startRoomInput;
        endRoom = endRoomInput;
        rooms = roomsInput;
    }

    public String getStartRoom() {
        return startRoom;
    }

    public String getEndRoom() {
        return endRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Room getRoomStringAsRoomObject(String roomName) {
        if (roomName == null || roomName.length() == 0) {
            throw new IllegalArgumentException("Name of room is invalid");
        }

        boolean roomFound = false;
        Room roomLookingFor = new Room ("", null, null, null);

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
