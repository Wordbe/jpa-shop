package co.wordbe.jpashop.domain.badge;

import lombok.NoArgsConstructor;

import javax.persistence.*;

//@SequenceGenerator(name = "BADGE_SEQUENCE_GENERATOR", sequenceName = "BADGE_SEQ")
@NoArgsConstructor
@Entity
public class Badge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Badge(String name) {
        this.name = name;
    }
}
