package edu.cnm.deepdive.interviewprep.controller;


import edu.cnm.deepdive.interviewprep.model.entity.History;
import edu.cnm.deepdive.interviewprep.service.HistoryService;
import edu.cnm.deepdive.interviewprep.service.UserService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {

  private final UserService userService;
  private final HistoryService historyService;

  @Autowired
  public HistoryController(UserService userService,
      HistoryService historyService) {
    this.userService = userService;
    this.historyService = historyService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public History post(@RequestBody History history) {
    return historyService.createHistory(history, userService.getCurrentUser());
  }

  @GetMapping(value = "/{externalKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public History get(@PathVariable UUID externalKey) {
    return historyService
        .get(externalKey, userService.getCurrentUser())
        .orElseThrow();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<History> get() {
    return userService
        .getCurrentUser()
        .getHistory();
  }

}
