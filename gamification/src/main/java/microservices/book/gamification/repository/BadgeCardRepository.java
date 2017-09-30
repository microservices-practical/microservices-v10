package microservices.book.gamification.repository;

import microservices.book.gamification.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Handles data operations with BadgeCards
 */
public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {

    /**
     * Retrieves all BadgeCards for a given user.
     * @param userId the id of the user to look for BadgeCards
     * @return the list of BadgeCards, sorted by most recent.
     */
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);

}
