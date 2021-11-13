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
    return questionRepository.findByExternalKey(key);
  }

  public void delete(UUID id) {
    questionRepository.deleteById(id);
  }

  public void delete(UUID key, User user) {
    questionRepository
        .findByExternalKey(key)
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
    return questionRepository
        .findRandom()
        .orElseThrow();//throws an exception if not in the database
  }

}
