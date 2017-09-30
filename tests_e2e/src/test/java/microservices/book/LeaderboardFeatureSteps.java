package microservices.book;

import cucumber.api.java.en.Then;
import microservices.book.testutils.beans.LeaderBoardPosition;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author moises.macero
 */
public class LeaderboardFeatureSteps {

    private MultiplicationFeatureSteps mSteps;

    public LeaderboardFeatureSteps(final MultiplicationFeatureSteps mSteps) {
        this.mSteps = mSteps;
    }

    @Then("^the user ([^\\s]+) is the number (\\d+) on the leaderboard$")
    public void the_user_is_the_number_on_the_leaderboard(final String user, final int position) throws Throwable {
        Thread.currentThread().sleep(500);
        List<LeaderBoardPosition> leaderBoard = mSteps.getApp().getLeaderboard();
        assertThat(leaderBoard).isNotEmpty();
        long userId = leaderBoard.get(position - 1).getUserId();
        String userAlias = mSteps.getApp().getUser(userId).getAlias();
        assertThat(userAlias).isEqualTo(user);
    }
}
