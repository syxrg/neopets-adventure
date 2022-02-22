package student.server;

import student.GameEngine;

import java.util.HashMap;
import java.util.SortedMap;

public class MonstroCityService implements AdventureService {
    private HashMap<Integer, GameEngine> gamesRunning = new HashMap<>();
    private int gameId = 0;


    @Override
    public void reset() {
       gameId = 0;
       gamesRunning.clear();
    }

    @Override
    public int newGame() throws AdventureException {
        gamesRunning.put(gameId, new GameEngine());
        return gameId++;
    }

    @Override
    public GameStatus getGame(int id) {
        GameEngine game = gamesRunning.get(id);
        String image = game.getCurrentRoom().getImage();

        return null;
    }

    @Override
    public boolean destroyGame(int id) {
        return false;
    }

    @Override
    public void executeCommand(int id, Command command) {

    }

    @Override
    public SortedMap<String, Integer> fetchLeaderboard() {
        return null;
    }
}
