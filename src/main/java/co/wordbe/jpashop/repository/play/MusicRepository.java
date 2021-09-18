package co.wordbe.jpashop.repository.play;

import co.wordbe.jpashop.domain.play.Music;
import co.wordbe.jpashop.domain.play.Play;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
