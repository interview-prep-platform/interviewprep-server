package edu.cnm.deepdive.interviewprep.model.dao;

import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This interface defines various methods that can be used to query the database.
 */
public interface QuestionRepository extends JpaRepository<Question, UUID> {

  /**
   * Returns a Question object that matches the external key.
   *
   * @param externalKey External key in the form of universally unique identifier.
   * @return An optional Question object.
   */
  Optional<Question> findByExternalKey(UUID externalKey);


  /**
   * Returns a Question object that matches the external key and user id.
   *
   * @param externalKey External key in the form of universally unique identifier.
   * @param user        User object
   * @return An optional Question object.
   */
  Optional<Question> findByExternalKeyAndUser(UUID externalKey, User user);

  /**
   * Returns a list of Question objects identified by the source.
   *
   * @param source A source in the form of a string.
   * @return An optional list of Question objects.
   */
  Optional<List<Question>> findAllBySource(String source);

  /**
   * Returns a random Question object from the database.
   *
   * @return An optional Question object.
   */
  @Query(value = "SELECT * FROM question ORDER BY RAND() limit 1", nativeQuery = true)
  Optional<Question> findRandom();


}
