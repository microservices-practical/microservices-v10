package microservices.book.gamification.event;

import microservices.book.gamification.domain.GameStats;
import microservices.book.gamification.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author moises.macero
 */
public class EventHandlerTest {

    private EventHandler eventHandler;

    @Mock
    private GameService gameService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventHandler = new EventHandler(gameService);
    }

    @Test
    public void multiplicationAttemptReceivedTest() {
        // given
        Long userId = 1L;
        Long attemptId = 8L;
        boolean correct = true;
        GameStats gameStatsExpected = new GameStats();
        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(attemptId, userId, correct);
        given(gameService.newAttemptForUser(userId, attemptId, correct)).willReturn(gameStatsExpected);

        // when
        eventHandler.handleMultiplicationSolved(event);

        // then
        verify(gameService).newAttemptForUser(userId, attemptId, correct);
    }

}