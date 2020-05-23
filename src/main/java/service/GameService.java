package service;

import domain.Game;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GameService {

    private final String serviceUrl;
    private RestTemplate rest = new RestTemplate();

    public GameService(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public List<Game> getAllGames() {
        ResponseEntity<List<Game>> response = rest.exchange(serviceUrl + "/games",
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<Game>>() {
                });
        return response.getBody();
    }

    public Game getGameById(Integer gameId) {
        ResponseEntity<Game> response = rest.getForEntity(serviceUrl + "/games/" + gameId,
                Game.class, gameId);
        return response.getBody();
    }

    public Game addGame(Game newGame) {
        return rest.postForObject(serviceUrl + "/games", newGame, Game.class);
    }
}
