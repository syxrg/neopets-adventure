package student.server;

import student.GameEngine;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.SortedMap;

public class MonstroCityService implements AdventureService {
    private HashMap<Integer, GameEngine> gamesRunning = new HashMap<>();
    private int id = 0;


    @Override
    public void reset() {
       id = 0;
       gamesRunning.clear();
    }

    @Override
    public int newGame() throws Exception {
        gamesRunning.put(id, new GameEngine());
        return id++;
    }

    @Override
    public GameStatus getGame(int id) {
        GameEngine game = gamesRunning.get(id);
        String image = game.getCurrentRoom().getImage();

        Map<String, List<String>> commandOptions;
        if (game.gameIsWon()) {
            commandOptions = new HashMap<>();
            return new GameStatus(false, id, game.getWinningMessage(),
                    image, null, new AdventureState(), commandOptions);
        } else {
            commandOptions = new HashMap<>();
            commandOptions.put("go", game.getDirectionMap().get(game.getCurrentRoom().getRoomName()));
            commandOptions.put("take", game.getItemMap().get(game.getCurrentRoom().getRoomName()));
            commandOptions.put("drop", game.getItemsPickedUp());
            commandOptions.put("traversed", game.getRoomsTraversedMap());
            return new GameStatus(false, id, game.getCurrentRoomInformation() + game.getItemInRoomInfo()
            + "\n" + game.getInventory(), image, null, new AdventureState(), commandOptions);
        }
    }

    @Override
    public boolean destroyGame(int id) {
        return gamesRunning.remove(id) != null;
    }

    @Override
    public void executeCommand(int id, Command command) {
        String commandName = command.getCommandName();
        String commandValue = command.getCommandValue();
        gamesRunning.get(id).startGame(commandName + "" + commandValue);

    }

    @Override
    public SortedMap<String, Integer> fetchLeaderboard() {
        return null;
    }
}
