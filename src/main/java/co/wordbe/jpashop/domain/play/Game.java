package co.wordbe.jpashop.domain.play;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("MY_GAME")
public class Game extends Play {
    private int numberOfPlayers;
}
