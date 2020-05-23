package menu;

import domain.Game;
import domain.GameSession;
import domain.Hand;
import domain.Player;
import service.GameService;
import service.GameSessionService;
import service.PlayerService;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final PlayerService playerService;
    private final GameService gameService;
    private final GameSessionService gameSessionService;

    public MainMenu(PlayerService playerService, GameService gameService, GameSessionService gameSessionService) {
        this.playerService = playerService;
        this.gameService = gameService;
        this.gameSessionService = gameSessionService;
    }

    public void run() {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            printMenu();
            option = readOption(scanner);
            processOption(option);
        } while (option != 0);
    }

    private void printAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        players.forEach(System.out::println);
    }

    private void addPlayer() {
        try {
            Player player = readNewPlayer();
            Player addedPlayer = playerService.addPlayer(player);
            System.out.println("Added new player: " + addedPlayer);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage() + "\n");
        }
    }

    private void getPlayerById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player ID: ");
        Integer id = scanner.nextInt();
        try {
            Player player = playerService.getPlayerById(id);
            System.out.println(player);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage() + "\n");
        }
    }

    private Player readNewPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player name: ");
        String name = scanner.next();
        System.out.print("Player hand: ");
        Hand hand = Hand.valueOf(scanner.next().toUpperCase());
        return new Player(name, hand);
    }

    private void printAllGames() {
        List<Game> games = gameService.getAllGames();
        games.forEach(System.out::println);
    }

    private void getGameById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter game ID: ");
        Integer id = scanner.nextInt();
        try {
            Game game = gameService.getGameById(id);
            System.out.println(game);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage() + "\n");
        }
    }

    private void addGame() {
        try {
            Game newGame = readNewGame();
            Game addedGame = gameService.addGame(newGame);
            System.out.println("Added new game: " + addedGame);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage() + "\n");
        }
    }

    private Game readNewGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First player ID: ");
        Integer player1Id = scanner.nextInt();
        System.out.print("Second player ID: ");
        Integer player2Id = scanner.nextInt();
        return new Game(player1Id, player2Id);
    }

    private void printAllGameSessions() {
        List<GameSession> sessions = gameSessionService.getAllGameSessions();
        sessions.forEach(System.out::println);
    }

    private void addGameSession() {
        try {
            GameSession gameSession = readNewGameSession();
            GameSession addedSession = gameSessionService.addGameSession(gameSession);
            System.out.println("Added new session: " + addedSession);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage() + "\n");
        }
    }

    private GameSession readNewGameSession() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First player ID: ");
        Integer player1Id = scanner.nextInt();
        System.out.print("Second player ID: ");
        Integer player2Id = scanner.nextInt();
        Game addedGame = gameService.addGame(new Game(player1Id, player2Id));
        GameSession newSession = new GameSession();
        Player player1 = playerService.getPlayerById(player1Id);
        Player player2 = playerService.getPlayerById(player2Id);
        newSession.addPlayer(player1);
        newSession.addPlayer(player2);
        newSession.addGame(addedGame);
        return newSession;
    }

    private int readOption(Scanner scanner) {
        System.out.print("Enter your option: ");
        return scanner.nextInt();
    }

    private void processOption(int option) {
        switch (option) {
            case 1:
                printAllPlayers();
                break;
            case 2:
                addPlayer();
                break;
            case 3:
                getPlayerById();
                break;
            case 4:
                addGame();
                break;
            case 5:
                printAllGames();
                break;
            case 6:
                getGameById();
                break;
            case 7:
                addGameSession();
                break;
            case 8:
                printAllGameSessions();
                break;
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println("1) Print all players");
        System.out.println("2) Add new player");
        System.out.println("3) Find player by ID");
        System.out.println("4) Add new game");
        System.out.println("5) Print all games");
        System.out.println("6) Find game by ID");
        System.out.println("7) Add new game session");
        System.out.println("8) Print all game sessions");
        System.out.println("0) Exit");
        System.out.println("---------------------------------------");
    }
}
