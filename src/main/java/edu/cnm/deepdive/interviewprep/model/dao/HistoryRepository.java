package edu.cnm.deepdive.interviewprep.model.dao;

import edu.cnm.deepdive.interviewprep.model.entity.History;
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
public interface HistoryRepository extends JpaRepository<History, UUID> {

  Optional<History> findByExternalKey(UUID externalKey);

  Optional<History> findByExternalKeyAndUser(UUID externalKey, User user);

  Iterable<History> getAllByOrderByQuestionAsc();

  Iterable<History> findAllByUser(User user);

  Iterable<History> findAllByUserAndQuestionOrderByCreatedDesc(User user, Question question);

  Optional<History> findByExternalKeyAndUserAndQuestion(UUID externalKey, User user, Question question);
}
