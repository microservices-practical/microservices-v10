package microservices.book.multiplication.service;

import microservices.book.multiplication.repository.MultiplicationRepository;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author moises.macero
 */
@Profile("test")
@Service
public class AdminServiceImpl implements AdminService {

    private MultiplicationRepository multiplicationRepository;
    private MultiplicationResultAttemptRepository attemptRepository;
    private UserRepository userRepository;

    public AdminServiceImpl(final MultiplicationRepository multiplicationRepository,
                            final UserRepository userRepository,
                            final MultiplicationResultAttemptRepository attemptRepository) {
        this.multiplicationRepository = multiplicationRepository;
        this.userRepository = userRepository;
        this.attemptRepository = attemptRepository;
    }

    @Override
    public void deleteDatabaseContents() {
        attemptRepository.deleteAll();
        multiplicationRepository.deleteAll();
        userRepository.deleteAll();
    }
}
