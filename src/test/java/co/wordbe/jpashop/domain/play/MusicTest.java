package co.wordbe.jpashop.domain.play;

import co.wordbe.jpashop.repository.play.MusicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(false)
class MusicTest {
    @Autowired
    MusicRepository musicRepository;

    @Autowired
    EntityManager em;

    @Test
    void Music_저장시_PlayType에_MUSIC이_들어간다() {
        // given
        Music music = new Music();
        music.setName("사랑은 은하수 다방에서");
        music.setPrice(600);
        music.setArtist("10cm");

        // when
        Music save = musicRepository.save(music);
        System.out.println(save);

//        // DataJpaTest 는 @Transactional 을 포함하고, 테스트 종료시 변경된 데이터를 rollback 한다.
//        // @Rollback(false) 를 통해 rollback 을 허용하지 않게 설정해도, 트랜잭션 커밋 직전에 insert 문이 발생한다.
//        // 따라서 테스트가 모두 끝날 때까지 insert 문이 생기지 않는다. 테스트에서는 insert 가 필요하므로 영속성컨텍스트를 초기화해준다.
        em.flush();
        em.clear();

        Music foundMusic = musicRepository.findById(save.getId()).get();
        System.out.println(foundMusic);

        // then
        assertThat(foundMusic.getPlayType()).isEqualTo("MUSIC");
    }
}