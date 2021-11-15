package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

public class QuestionService {

  private final QuestionRepository questionRepository;
  private final Random rng;

  @Autowired
  public QuestionService(
      QuestionRepository questionRepository, Random rng) {
    this.questionRepository = questionRepository;
    this.rng = rng;
  }

  public List<Question> getQuestions() {
    return questionRepository.findAll();
  }

  public Optional<Question> get(UUID id) {
    return questionRepository.findById(id);
  }

  public Optional<Question> getByExternalKey(UUID key) {
    return questionRepository.findByExternalKey(key);
  }

  public Question createQuestion(Question question) {
    //TODO Find how to implement
    return questionRepository.save(question);
  }

  public void delete(UUID id) {
    questionRepository.deleteById(id);
  }

  public Question saveQuestion(Question question) {
    return questionRepository.save(question);
  }

  public Question getRandomQuestion() {
    return questionRepository
        .findRandom()
        .orElseThrow();//throws an exception if not in the database
  }


}
