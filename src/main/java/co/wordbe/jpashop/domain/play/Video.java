package co.wordbe.jpashop.domain.play;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Video extends Play {
    private String director;
}
