package microservices.book.gamification.client;

import microservices.book.gamification.client.dto.MultiplicationResultAttempt;

/**
 * This interface allows us to connect to the Multiplication microservice.
 * Note that it's agnostic to the way of communication.
 */
public interface MultiplicationResultAttemptClient {

    MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(final Long multiplicationId);

}
