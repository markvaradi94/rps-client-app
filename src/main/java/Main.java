import menu.MainMenu;
import service.GameService;
import service.GameSessionService;
import service.PlayerService;

public class Main {

    public static void main(String[] args) {
        PlayerService playerService = new PlayerService("http://localhost:8080");
        GameService gameService = new GameService("http://localhost:8080");
        GameSessionService gameSessionService = new GameSessionService("http://localhost:8080");
        MainMenu menu = new MainMenu(playerService, gameService, gameSessionService);
        menu.run();
    }
}
