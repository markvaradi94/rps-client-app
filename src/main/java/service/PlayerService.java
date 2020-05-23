package service;

import domain.Game;
import domain.Player;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PlayerService {
    private final String serviceUrl;
    private RestTemplate rest = new RestTemplate();

    public PlayerService(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public List<Player> getAllPlayers() {
        ResponseEntity<List<Player>> response = rest.exchange(serviceUrl + "/players",
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<Player>>() {
                });
        return response.getBody();
    }

    public Player getPlayerById(Integer playerId) {
        ResponseEntity<Player> response = rest.getForEntity(serviceUrl + "/players/" + playerId,
                Player.class, playerId);
        return response.getBody();
    }

    public Player addPlayer(Player newPlayer) {
        return rest.postForObject(serviceUrl + "/players", newPlayer, Player.class);
    }


}
