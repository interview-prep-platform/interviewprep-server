package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.CategoryRepository;
import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.List;
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
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final CategoryRepository categoryRepository;

  /**
   * Constructor that instantiates a new {@link Question} service object.
   *
   * @param questionRepository Question repository object.
   */
  @Autowired
  public QuestionService(
      QuestionRepository questionRepository, CategoryRepository categoryRepository) {
    this.questionRepository = questionRepository;
    this.categoryRepository = categoryRepository;
  }

  /**
   * Returns a {@link Question} object identified by the external key for the current user.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @param user        A User object.
   * @return An optional Question object.
   */
  public Optional<Question> get(UUID externalKey, User user) {
    return questionRepository.findByExternalKeyAndUser(externalKey, user);
  }


  /**
   * Deletes a {@link Question} object identified by external key for the current user.
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
   * Returns a list of all {@link Question} in the database.
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
   * Creates a new {@link Question} object in the database for the current user.
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
   * Saves a {@link Question} object to the database for the current user.
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
   * Returns a random {@link Question} object from the database.
   *
   * @return A random Question object.
   */
  public Question getRandomQuestion() {
    return questionRepository
        .findRandom()
        .orElseThrow();//throws an exception if not in the database
  }

  /**
   * Updates a {@link Question} object to the database for the current user.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @param question A Question object.
   * @param user     A User object.
   * @return A Question object.
   */
  public Optional<Question> updateQuestion(UUID externalKey, Question question, User user) {
    return questionRepository
        .findByExternalKeyAndUser(externalKey, user)
        .map((q) -> { //if we don't have anything returned from the optional, then the map won't execute
          //q.setUser(user);
          q.setQuestion(question.getQuestion());
          q.setAnswer(question.getAnswer());
          q.setSource(question.getSource());
          return questionRepository.save(q);
        });
  }

  public Iterable<Question> getAllQuestions() {
    return questionRepository.getAllByOrderByQuestionAsc();
  }

  public Optional<Boolean> assignCategoryToQuestion(UUID questionKey, UUID categoryKey, boolean assign, User user) {
    return questionRepository
        .findByExternalKeyAndUser(questionKey, user)
        .map((question) -> {
          categoryRepository.findByExternalKey(categoryKey);
        })
  }
}
