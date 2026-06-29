package app.brio.repositories;

import app.brio.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for User entities.
 * Inherits standard CRUD from JpaRepository:
 *   - save (inserts if new, updates if existing)
 *   - findById, findAll, existsById, count
 *   - deleteById, delete, deleteAll
 *   (plus batch/paging/sorting variants)
 * Below: custom queries specific to User.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by his unique login
     * @param login the login to search for
     * @return an Optional containing the matching user or an empty Optionnal if no user has this login
     */
    Optional<User> findByLogin(String login);

    /**
     * Find a user by his unique mail address
     * @param mail the mail address to search for
     * @return an Optional containing the matching user, or an empty Optionnal if no user has this mail
     */
    Optional<User> findByMail(String mail);

    /**
     * Check whether a user already exists with the given mail.
     * Used to enforce mail uniqueness before creating a user.
     * @param mail the mail to check
     * @return true if a user exists with this mail false otherwise
     */
    boolean existsByMail(String mail);

    /**
     * Check whether a user already exists with the given login.
     * Used to enforce login uniqueness before creating a user.
     * @param login the login to check
     * @return true if a user exists with this login false otherwise
     */
    boolean existsByLogin(String login);
}
