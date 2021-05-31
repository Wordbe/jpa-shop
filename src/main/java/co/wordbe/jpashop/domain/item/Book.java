package co.wordbe.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter @Setter
@DiscriminatorValue("book")
@Entity
public class Book extends Item {

    private String author;
    private String isbn;
}
