package edu.cnm.deepdive.interviewprep.model.dao;

import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

  Optional<Question> findByExternalKey(UUID key);

  Optional<Question> findByExternalKeyAndUser(UUID key, User user);

  Optional<List<Question>> findAllBySource(String source);

  @Query(value = "SELECT * FROM question ORDER BY RAND() limit 1", nativeQuery = true)
  Optional<Question> findRandom();


}
