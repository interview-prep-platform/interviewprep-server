package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.HistoryRepository;
import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.History;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Optional;
import java.util.UUID;

public class HistoryService {

  private final HistoryRepository historyRepository;

  public HistoryService(
      HistoryRepository historyRepository) {
    this.historyRepository = historyRepository;
  }

  public Optional<History> get(UUID id) {
    return historyRepository.findById(id);
  }

  public Optional<History> get(UUID key, User user) {
    return historyRepository.findByExternalKeyAndUser(key, user);
  }

  public void delete(UUID id) {
    historyRepository.deleteById(id);
  }

  public void delete(UUID key, User user) {
    historyRepository
        .findByExternalKeyAndUser(key, user)
        .ifPresent(historyRepository::delete);
  }

  public History saveHistory(String question, String answer) {
    History history = new History();
    history.setUserQuestion(question);
    history.setUserAnswer(answer);
    return historyRepository.save(history);
  }
  public Question processHistory(UUID questionKey, Question question, User user) {
    return QuestionRepository
        .findByExternalKeyAndUser(questionKey, user)
        .map((question) -> {
          history.setQuestion(question);
          return historyRepository.save(history);
        })
        .orElseThrow();
    //TODO Can we somehow combine the history method on line 38 and proces method of line 44. & How to save the question ID.
  }

}
