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
   * Returns a {@link Question} object that matches the external key.
   *
   * @param externalKey External key in the form of universally unique identifier.
   * @return An optional Question object.
   */
  Optional<Question> findByExternalKey(UUID externalKey);


  /**
   * Returns a {@link Question} object that matches the external key and user id.
   *
   * @param externalKey External key in the form of universally unique identifier.
   * @param user        User object
   * @return An optional Question object.
   */
  Optional<Question> findByExternalKeyAndUser(UUID externalKey, User user);

  /**
   * Returns a list of {@link Question} objects identified by the source.
   *
   * @param source A source in the form of a string.
   * @return An optional list of Question objects.
   */
  Optional<List<Question>> findAllBySource(String source);

  /**
   * Returns a random {@link Question} object from the database.
   *
   * @return An optional Question object.
   */
  @Query(value = "SELECT * FROM question ORDER BY RAND() limit 1", nativeQuery = true)
  Optional<Question> findRandom();

  @Query(value = "SELECT * FROM question ORDER BY RAND() limit :count", nativeQuery = true)
  Iterable<Question> findRandom(int count);

  @Query(value = "SELECT DISTINCT q FROM Question AS q INNER JOIN q.answers AS a ON a.user = :user ORDER BY q.question ASC")
  Iterable<Question> findAllQuestionsWithUserAnswers(User user);

  @Query(value = "SELECT DISTINCT q FROM Question AS q LEFT JOIN q.answers AS a ON a.user = :user WHERE a.question IS NULL ORDER BY q.question ASC")
  Iterable<Question> findAllQuestionsWithoutUserAnswers(User user);

  Iterable<Question> getAllByOrderByQuestionAsc();

}
