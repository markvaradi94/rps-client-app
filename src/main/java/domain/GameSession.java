package domain;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class GameSession {

    @JsonTypeId
    private Integer id;
    private Set<Game> games = new HashSet<>();
    private Set<Player> sessionPlayers = new HashSet<>();

    public GameSession(Set<Game> games, Set<Player> sessionPlayers) {
        this.games = games;
        this.sessionPlayers = sessionPlayers;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void addPlayer(Player player) {
        this.sessionPlayers.add(player);
    }
}
