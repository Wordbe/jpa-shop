package co.wordbe.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter @Setter
@DiscriminatorValue("movie")
@Entity
public class Movie extends  Item {

    private String director;
    private String actor;
}
