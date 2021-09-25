package co.wordbe.jpashop.domain.badge;

import co.wordbe.jpashop.repository.BadgeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(value = false)
class BadgeTest {
    @Autowired
    BadgeRepository badgeRepository;

    @Test
    void GeneratedValue_AUTO_전략() {
        Badge badge = new Badge("칭찬왕");
        badgeRepository.save(badge);
    }

    @Test
    void GeneratedValue_IDENTITY_전략() {
        Badge badge = new Badge("칭찬왕");
        badgeRepository.save(badge);
    }
}