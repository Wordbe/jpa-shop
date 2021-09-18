package co.wordbe.jpashop.domain.play;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "play_type")
public class Play {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;

    @Column(name = "play_type", insertable = false, updatable = false)
    private String playType;
}
