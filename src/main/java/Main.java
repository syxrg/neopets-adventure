import org.glassfish.grizzly.http.server.HttpServer;
import student.server.AdventureResource;
import student.server.AdventureServer;
import student.server.CommandLine;

public class Main {
    public static void main(String[] args) throws Exception {
        CommandLine game = new CommandLine();
        game.gamePlay();

        //api
        //HttpServer server = AdventureServer.createServer(AdventureResource.class);
        //server.start();
    }
}
