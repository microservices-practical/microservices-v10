package microservices.book.gamification.service;

/**
 * This service provides methods to an administrator of the application to perform some high risk operations.
 * It should only be used when the application is being tested, never during runtime.
 *
 * @author moises.macero
 */
public interface AdminService {

    /**
     * Deletes all the database contents
     */
    void deleteDatabaseContents();
}
