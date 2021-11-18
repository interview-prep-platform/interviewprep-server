package edu.cnm.deepdive.interviewprep.controller;

import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.service.QuestionService;
import edu.cnm.deepdive.interviewprep.service.UserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class creates REST endpoints that has access to the Question entity in the database through
 * the Question and User services. Identified by the URL /interviewprep/questions.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

  private final QuestionService questionService;
  private final UserService userService;

  /**
   * Constructor that instantiates a new User service object.
   *
   * @param userService     User service object.
   * @param questionService Question service object.
   */
  @Autowired
  public QuestionController(UserService userService,
      QuestionService questionService) {
    this.questionService = questionService;
    this.userService = userService;
  }

  /**
   * This method defines the behavior of a GET request to the URL /interviewprep/questions/externalKey.
   * It grabs the current question from the Question service.
   *
   * @param externalKey External key in the form of a universally unique identifier as identified by
   *                    the path variable external key.
   * @return A Question service object in the form of JSON.
   */
  @GetMapping(value = "/{externalKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Question get(@PathVariable UUID externalKey) {
    return questionService
        .get(externalKey)
        .orElseThrow();
  }

  /**
   * This method defines the behavior of a POST request to the URL /interviewprep/questions. It
   * creates a new question through the Question service for the current user.
   *
   * @param question A Question Object in the form of request body.
   * @return A Question service object in the form of JSON.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Question post(@RequestBody Question question) {
    return questionService.createQuestion(question, userService.getCurrentUser());
  }


  /**
   * This method defines the behavior of a PUT request to the URL /interviewprep/questions/questionid.
   * It updates a question through the Question service for the current user.
   *
   * @param question A Question Object in the form of request body.
   * @return A Question service object in the form of JSON.
   */
  @PutMapping(value = "/{questionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Question put(@PathVariable String questionId, @RequestBody Question question) {
    return questionService.saveQuestion(question, userService.getCurrentUser());
  }

  /**
   * This method defines the behavior of a DELETE request to the URL /interviewprep/questions/externalkey.
   * Returns a HTTP no content status via header.
   *
   * @param externalKey External key in the form of a universally unique identifier as identified by
   *                    the path variable external key.
   */
  @DeleteMapping(value = "/{externalKey}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID externalKey) {
    questionService.delete(externalKey);
  }

  /**
   * This method defines the behavior of a GET request to the URL /interviewprep/questions/random.
   *
   * @return Question object in the form of JSON.
   */
  @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
  public Question getRandom() {
    return questionService.getRandomQuestion();
  }

  /**
   * This method defines the behavior of a GET request to the URL /interviewprep/questions.
   *
   * @return a list of all questions from database via the question service.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Question> get() {
    return questionService
        .getQuestions();
  }


}
