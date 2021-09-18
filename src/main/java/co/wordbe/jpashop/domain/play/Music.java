package co.wordbe.jpashop.domain.play;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("MUSIC")
public class Music extends Play{
    private String artist;
}
