package student.adventure;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import student.GameEngine;
import student.server.Layout;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONTest {
    private Layout monstrocityData;
    private GameEngine game;
    @Before
    public void setUp() throws IOException {
        Gson gson = new Gson();
        Reader jsonReader = null;
        jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/monstrocity.json"));
        monstrocityData = gson.fromJson(jsonReader, Layout.class);
    }

    @Test
    public void checkCorrectNumberOfRooms() {
        assertEquals(10, monstrocityData.getRooms().length);
    }

    @Test
    public void checkStartingRoom() {
        String startingRoom = "Main Street";
        assertEquals(startingRoom, monstrocityData.getStartRoom());
    }

    @Test
    public void checkEndingRoom() {
        String endingRoom = "Puzzle Palace";
        assertEquals(endingRoom, monstrocityData.getEndRoom());
    }

    @Test
    public void checkStartingRoomDesccription() {
        String startingRoomDescription = "Welcome to Monstro City! You are one stop away from Sludge Street";
        assertEquals(startingRoomDescription, monstrocityData.getRooms()[0].getDescription());
    }

    @Test
    public void checkStartingRoomItem() {
        String startingRoomItem = "map";
        assertEquals(startingRoomItem, monstrocityData.getRooms()[0].getItems());
    }

    @Test
    public void checkStartingRoomNextRoom() {
        String startingRoomNextRoom = "Sludge Street";
        assertEquals(startingRoomNextRoom, monstrocityData.getRooms()[1].getRoomName());
    }

}
