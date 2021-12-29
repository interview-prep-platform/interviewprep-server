package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.CategoryRepository;
import edu.cnm.deepdive.interviewprep.model.dao.HistoryRepository;
import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.History;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * This class implements the high level persistence and business logic for the Question entity.
 */
@Service
@Profile("service")
public class HistoryService {

  private final HistoryRepository historyRepository;
  private final QuestionRepository questionRepository;

  @Autowired
  public HistoryService(
      HistoryRepository historyRepository,QuestionRepository questionRepository) {
    this.historyRepository = historyRepository;
    this.questionRepository = questionRepository;
  }


  public Optional<History> get(UUID externalKey, User user) {
    return historyRepository.findByExternalKeyAndUser(externalKey, user);
  }


  public void delete(UUID externalKey, User user) {
    historyRepository
        .findByExternalKeyAndUser(externalKey, user)
        .ifPresent(historyRepository::delete);
  }


  /**
   * Returns a list of all {@link Question} in the database.
   *
   * @return A list of all Question objects.
   */
  public Iterable<History> getHistories(User user) {
    return historyRepository.findAllByUser(user);
  }

  /**
   * Gets the current Question object records from the database identified by external key.
   *
   * @param externalKey Id in the form of a universally unique identifier.
   * @return An optional Question object.
   */
  public Optional<History> get(UUID externalKey) {
    return historyRepository.findByExternalKey(externalKey);
  }


  public Optional<History> createHistory(History history, UUID questionId, User user) {
    if (user != null) {
      history.setUser(user);
    }
    return questionRepository
        .findByExternalKeyAndUser(questionId, user)
        .map((q) -> { //if we don't have anything returned from the optional, then the map won't execute
          history.setQuestion(q);
          return historyRepository.save(history);
        });
  }


  public History saveHistory(History history, User user) {
    if (user != null) {
      history.setUser(user);
    }
    return historyRepository.save(history);
  }

  public Optional<History> updateHistory(UUID externalKey, History history, User user) {
    return historyRepository
        .findByExternalKeyAndUser(externalKey, user)
        .map((h) -> { //if we don't have anything returned from the optional, then the map won't execute
          h.setAnswer(history.getAnswer());
          return historyRepository.save(h);
        });
  }

  public Iterable<History> getAllHistories() {
    return historyRepository.getAllByOrderByQuestionAsc();
  }
}
