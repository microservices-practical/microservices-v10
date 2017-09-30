package microservices.book.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents a line in our Leaderboard: it links a user to a total score.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class LeaderBoardRow {

    private final Long userId;
    private final Long totalScore;

    // Empty constructor for JSON / JPA
    public LeaderBoardRow() {
        this(0L, 0L);
    }
}
