package edu.cnm.deepdive.interviewprep.model.dao;

import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

//Like our Daos but already knows how to do most of things we defined in our Per. Projects.

/**
 * This interface defines various methods that can be used to query the database.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

  /**
   * Returns a {@link User} object that matches the OAuth key.
   *
   * @param oauthKey OAuth key in the form of a string.
   * @return An optional User object.
   */
  Optional<User> findByOauthKey(String oauthKey);

  /**
   * Returns a {@link User} object that matches the external key.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @return An optional User object.
   */
  Optional<User> findByExternalKey(UUID externalKey);

  /**
   * Returns a list of {@link User} objects ordered by display name in ascending order.
   *
   * @return Returns an iterable User object.
   */
  Iterable<User> getAllByOrderByDisplayNameAsc();
}
