package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the high level persistence and business logic for the Question entity.
 */
@Service
public class QuestionService {

  private final QuestionRepository questionRepository;

  /**
   * Constructor that instantiates a new Question service object.
   *
   * @param questionRepository Question repository object.
   */
  @Autowired
  public QuestionService(
      QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  /**
   * Returns a Question object identified by the external key for the current user.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @param user        A User object.
   * @return An optional Question object.
   */
  public Optional<Question> get(UUID externalKey, User user) {
    return questionRepository.findByExternalKeyAndUser(externalKey, user);
  }


  /**
   * Deletes a Question object identified by external key for the current user.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @param user        A User object.
   */
  public void delete(UUID externalKey, User user) {
    questionRepository
        .findByExternalKeyAndUser(externalKey, user)
        .ifPresent(questionRepository::delete);
  }


  /**
   * Returns a list of all Questions in the database.
   *
   * @return A list of all Question objects.
   */
  public List<Question> getQuestions() {
    return questionRepository.findAll();
  }

  /**
   * Gets the current Question object records from the database identified by external key.
   *
   * @param externalKey Id in the form of a universally unique identifier.
   * @return An optional Question object.
   */
  public Optional<Question> get(UUID externalKey) {
    return questionRepository.findByExternalKey(externalKey);
  }

  /**
   * Creates a new Question object in the database for the current user.
   *
   * @param question A Question object.
   * @param user     A User object.
   * @return A Question object.
   */
  public Question createQuestion(Question question, User user) {
    if (user != null) {
      question.setUser(user);
    }
    return questionRepository.save(question);
  }

  /**
   * Saves a Question object to the database for the current user.
   *
   * @param question A Question object.
   * @param user     A User object.
   * @return A Question object.
   */
  public Question saveQuestion(Question question, User user) {
    if (user != null) {
      question.setUser(user);
    }
    return questionRepository.save(question);
  }

  /**
   * Returns a random Question object from the database.
   *
   * @return A random Question object.
   */
  public Question getRandomQuestion() {
    return questionRepository
        .findRandom()
        .orElseThrow();//throws an exception if not in the database
  }

  /**
   * Updates a Question object to the database for the current user.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @param question A Question object.
   * @param user     A User object.
   * @return A Question object.
   */
  public Optional<Question> updateQuestion(UUID externalKey, Question question, User user) {
    return questionRepository
        .findByExternalKey(externalKey)
        .map((q) -> { //if we don't have anthing returned from the optional, then the map won't execute
          q.setQuestion(question.getQuestion());
          q.setAnswer(question.getAnswer());
          q.setSource(question.getSource());
          return questionRepository.save(q);
        });
    //updatedQuestion.setUserAnswer(question.getUserAnswer());
    // TODO
  }

}
