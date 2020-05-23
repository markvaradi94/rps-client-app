package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Player {

    private Integer id;
    private String name;
    private Hand hand;
    private int wins = 0;
    private int losses = 0;
    private int draws = 0;

    public Player(@JsonProperty("name") String name, @JsonProperty("hand") Hand hand) {
        this.name = name;
        this.hand = hand;
    }
}
