package domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Game {

    private Integer id;
    private Integer firstPlayerId;
    private Integer secondPlayerId;
    private GameResult gameResult;

    public Game(Integer firstPlayerId, Integer secondPlayerId, GameResult gameResult) {
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.gameResult = gameResult;
    }

    public Game(Integer firstPlayerId, Integer secondPlayerId) {
        this(firstPlayerId, secondPlayerId, GameResult.WAITING_FOR_PLAYER);
    }
}
