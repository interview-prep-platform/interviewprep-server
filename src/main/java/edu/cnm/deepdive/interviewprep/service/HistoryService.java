package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.HistoryRepository;
import edu.cnm.deepdive.interviewprep.model.entity.History;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

public class HistoryService {

  private final HistoryRepository historyRepository;

  @Autowired
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

  public History saveHistory(String userQuestion, String answer, User user, Question question) {
    History history = new History();
    history.setUserQuestion(userQuestion);
    history.setUserAnswer(answer);
    history.setQuestion(question);
    history.setUser(user);
    return historyRepository.save(history);
  }

  public History createHistory(History history, User user) {
    history.setUser(user);
    return historyRepository.save(history);
  }

}
