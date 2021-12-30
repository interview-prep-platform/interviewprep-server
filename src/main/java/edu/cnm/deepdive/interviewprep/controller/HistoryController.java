package edu.cnm.deepdive.interviewprep.controller;

import edu.cnm.deepdive.interviewprep.model.entity.History;
import edu.cnm.deepdive.interviewprep.service.HistoryService;
import edu.cnm.deepdive.interviewprep.service.UserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class creates REST endpoints that has access to the Question entity in the database through
 * the Question and User services. Identified by the URL /interviewprep/questions.
 */
@RestController
@RequestMapping("/questions/{questionExternalKey}/answers")
@Profile("service")
public class HistoryController {

  private final HistoryService historyService;
  private final UserService userService;

  @Autowired
  public HistoryController(UserService userService,
      HistoryService historyService) {
    this.historyService = historyService;
    this.userService = userService;
  }

  @GetMapping(value = "/{answerExternalKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public History get(@PathVariable UUID questionExternalKey, @PathVariable UUID answerExternalKey) {
    return historyService
        .get(questionExternalKey, answerExternalKey, userService.getCurrentUser())
        .orElseThrow();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public History post(@PathVariable UUID questionExternalKey, @RequestBody History history) {
    return historyService.createHistory(history, questionExternalKey, userService.getCurrentUser())
        .orElseThrow();
  }


  @DeleteMapping(value = "/{answerExternalKey}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID questionExternalKey, @PathVariable UUID answerExternalKey) {
    historyService.delete(questionExternalKey, answerExternalKey, userService.getCurrentUser());
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<History> getAll(@PathVariable UUID questionExternalKey) {
    return historyService
        .getUsersHistory(questionExternalKey, userService.getCurrentUser())
        .orElseThrow();
  }
}
