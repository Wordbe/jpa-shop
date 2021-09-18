package co.wordbe.jpashop.domain.play;

import co.wordbe.jpashop.repository.play.PlayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlayTest {
    @Autowired
    PlayRepository playRepository;

    @Autowired
    EntityManager em;

    @Test
    public void DTYPE_컬럼값확인() {
        Music music = new Music();
        music.setName("사랑은 은하수 다방에서");
        music.setPrice(600);
        music.setArtist("10cm");

        Music save = playRepository.save(music);
        em.flush();
        em.clear();
        Play play = playRepository.findById(save.getId()).get();
        System.out.println(save);
        System.out.println(play);

        assertThat(save.getArtist()).isEqualTo("10cm");


    }
}