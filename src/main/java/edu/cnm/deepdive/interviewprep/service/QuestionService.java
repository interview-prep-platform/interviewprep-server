package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

  private final QuestionRepository questionRepository;

  @Autowired
  public QuestionService(
      QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public Optional<Question> get(UUID key, User user) {
    return questionRepository.findByExternalKeyAndUser(key, user);
  }

  /**
   * Deletes the current question records from the provided Question database.
   * @param key
   * @param user
   */
  public void delete(UUID key, User user) {
    questionRepository
        .findByExternalKeyAndUser(key, user)
        .ifPresent(questionRepository::delete);
  }

  public List<Question> getQuestions() {
    return questionRepository.findAll();
  }


  /**
   * Gets the current Question records from the database.
   * @param id
   * @return
   */
  public Optional<Question> get(UUID id) {
    return questionRepository.findById(id);
  }

  public Optional<Question> getByExternalKey(UUID key) {
    return questionRepository.findByExternalKey(key);
  }

  public Question createQuestion(Question question, User user) {
    //TODO Find how to implement
    if (user != null) {
      question.setUser(user);
    }
    return questionRepository.save(question);
  }


  public void delete(UUID id) {
    questionRepository.deleteById(id);
  }

  public Question saveQuestion(Question question, User user) {
    if (user != null) {
    question.setUser(user);
    }
    return questionRepository.save(question);
  }

  public Question getRandomQuestion() {
    return questionRepository
        .findRandom()
        .orElseThrow();//throws an exception if not in the database
  }


}
