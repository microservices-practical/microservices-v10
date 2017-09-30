package microservices.book.testutils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.book.testutils.beans.AttemptResponse;
import microservices.book.testutils.beans.User;
import microservices.book.testutils.beans.LeaderBoardPosition;
import microservices.book.testutils.beans.Stats;
import microservices.book.testutils.beans.ScoreResponse;
import microservices.book.testutils.http.ApplicationHttpUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author moises.macero
 */
public class MultiplicationApplication {

    private static final String APPLICATION_BASE_URL = "http://localhost:8000/api";
    private static final String CONTEXT_ATTEMPTS = "/results";
    private static final String CONTEXT_SCORE = "/scores/";
    private static final String CONTEXT_STATS = "/stats";
    private static final String CONTEXT_USERS = "/users/";
    private static final String CONTEXT_LEADERBOARD = "/leaders";
    private static final String CONTEXT_DELETE_DATA_GAM = "/gamification/admin/delete-db";
    private static final String CONTEXT_DELETE_DATA_MULT = "/multiplication/admin/delete-db";

    private ApplicationHttpUtils httpUtils;

    public MultiplicationApplication() {
        this.httpUtils = new ApplicationHttpUtils(APPLICATION_BASE_URL);
    }

    public AttemptResponse sendAttempt(String userAlias, int factorA, int factorB, int result) {
        String attemptJson = "{\"user\":{\"alias\":\"" + userAlias + "\"}," +
                "\"multiplication\":{\"factorA\":\"" + factorA + "\",\"factorB\":\"" + factorB + "\"}," +
                "\"resultAttempt\":\"" + result + "\"}";
        String response = httpUtils.post(CONTEXT_ATTEMPTS, attemptJson);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(response, AttemptResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ScoreResponse getScoreForAttempt(long attemptId) {
        String response = httpUtils.get(CONTEXT_SCORE + attemptId);
        if (response.isEmpty()) {
            return new ScoreResponse(0);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try {
                return objectMapper.readValue(response, ScoreResponse.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Stats getStatsForUser(long userId) {
        String response = httpUtils.get(CONTEXT_STATS + "?userId=" + userId);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(response, Stats.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(long userId) {
        String response = httpUtils.get(CONTEXT_USERS + userId);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(response, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LeaderBoardPosition> getLeaderboard() {
        String response = httpUtils.get(CONTEXT_LEADERBOARD);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, LeaderBoardPosition.class);
            return objectMapper.readValue(response, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteData() {
        httpUtils.post(CONTEXT_DELETE_DATA_GAM, "");
        httpUtils.post(CONTEXT_DELETE_DATA_MULT, "");
    }
}
