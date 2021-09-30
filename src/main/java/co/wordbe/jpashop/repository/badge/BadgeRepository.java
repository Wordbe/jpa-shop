package co.wordbe.jpashop.repository.badge;

import co.wordbe.jpashop.domain.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
