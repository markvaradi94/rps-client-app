package service;

import domain.Game;
import domain.GameSession;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GameSessionService {

    private final String serviceUrl;
    private RestTemplate rest = new RestTemplate();

    public GameSessionService(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public List<GameSession> getAllGameSessions() {
        ResponseEntity<List<GameSession>> response = rest.exchange(serviceUrl + "/sessions",
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<GameSession>>() {
                });
        return response.getBody();
    }

    public GameSession addGameSession(GameSession gameSession) {
        return rest.postForObject(serviceUrl + "/sessions", gameSession, GameSession.class);
    }

    public void addGameToGameSession(GameSession gameSession) {
        rest.put(serviceUrl + "/" + gameSession.getId(), gameSession);
    }
}
