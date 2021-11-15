package edu.cnm.deepdive.interviewprep.controller;

import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.service.QuestionService;
import edu.cnm.deepdive.interviewprep.service.UserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {


  
  private final QuestionService questionService;

  @Autowired
  public QuestionController(UserService userService,
      QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping(value = "/{externalKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Question get(@PathVariable UUID externalKey) {
    return questionService
        .get(externalKey)
        .orElseThrow();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Question post(@RequestBody Question question) {
    return questionService.createQuestion(question);
  }


  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Question put(@RequestBody Question question) {
    return questionService.saveQuestion(question);
  }

  @DeleteMapping(value = "/{externalKey}")
  public void delete(@PathVariable UUID externalKey) {
    questionService.delete(externalKey);
  }


  @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
  public Question getRandom() {
    return questionService.getRandomQuestion();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Question> get() {
    return questionService
        .getQuestions();
  }





}
