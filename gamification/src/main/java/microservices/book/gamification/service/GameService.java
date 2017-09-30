package microservices.book.gamification.service;

import microservices.book.gamification.domain.GameStats;
import microservices.book.gamification.domain.ScoreCard;

/**
 * This service includes the main logic for gamifying the system.
 */
public interface GameService {

    /**
     * Process a new attempt from a given user.
     *
     * @param userId    the user's unique id
     * @param attemptId the attempt id, can be used to retrieve extra data if needed
     * @param correct   indicates if the attempt was correct
     *
     * @return a {@link GameStats} object containing the new score and badge cards obtained
     */
    GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);

    /**
     * Gets the game statistics for a given user
     * @param userId the user
     * @return the total statistics for that user
     */
    GameStats retrieveStatsForUser(Long userId);

    /**
     * Gets the score for a given attempt
     * @param attemptId the attempt unique id
     * @return a {@link ScoreCard} with the details of the score for that attempt
     */
    ScoreCard getScoreForAttempt(Long attemptId);

}
