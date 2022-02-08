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

}
