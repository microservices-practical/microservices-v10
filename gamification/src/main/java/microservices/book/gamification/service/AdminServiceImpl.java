package microservices.book.gamification.service;

import microservices.book.gamification.repository.BadgeCardRepository;
import microservices.book.gamification.repository.ScoreCardRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author moises.macero
 */
@Profile("test")
@Service
class AdminServiceImpl implements AdminService {

    private BadgeCardRepository badgeCardRepository;
    private ScoreCardRepository scoreCardRepository;

    public AdminServiceImpl(final BadgeCardRepository badgeCardRepository,
                            final ScoreCardRepository scoreCardRepository) {
        this.badgeCardRepository = badgeCardRepository;
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public void deleteDatabaseContents() {
        scoreCardRepository.deleteAll();
        badgeCardRepository.deleteAll();
    }
}
