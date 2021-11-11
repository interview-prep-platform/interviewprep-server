package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class QuestionService {

  private final QuestionRepository questionRepository;
  private final Random rng;

  public QuestionService(
      QuestionRepository questionRepository, Random rng) {
    this.questionRepository = questionRepository;
    this.rng = rng;
  }

  public Optional<Question> get(UUID id) {
    return questionRepository.findById(id);
  }

  public Optional<Question> get(UUID key, User user) {
    return questionRepository.findByExternalKeyAndUser(key, user);
  }

  public void delete(UUID id) {
    questionRepository.deleteById(id);
  }

  public void delete(UUID key, User user) {
    questionRepository
        .findByExternalKeyAndUser(key, user)
        .ifPresent(questionRepository::delete);
  }

  public Question saveQuestion(String questionText, String answer, String source) {
    Question question = new Question();
    question.setQuestion(questionText);
    question.setAnswer(answer);
    question.setSource(source);
    return questionRepository.save(question);

  }

  public Question getRandomQuestion() {
    return getRandomQuestion(); rng.nextInt(question);
    //TODO Ask Nick about tablesize & how to get a random question.
  }

}
