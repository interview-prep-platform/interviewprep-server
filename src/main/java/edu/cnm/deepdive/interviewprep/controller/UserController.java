package edu.cnm.deepdive.interviewprep.controller;

import edu.cnm.deepdive.interviewprep.model.entity.User;
import edu.cnm.deepdive.interviewprep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class creates REST endpoints that has access to the User entity in the database through the
 * user service. Identified by the URL /interviewprep/users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService service;

  /**
   * Constructor that instantiates a new User service object.
   *
   * @param service User service object.
   */
  @Autowired //implicitly autowired, but can add here for clarity
  public UserController(UserService service) {
    this.service = service;
  }

  /**
   * This method defines the behavior of a GET request to the URL /interviewprep/users/me. It grabs
   * the current user from the User service.
   *
   * @return A User object in the form of JSON.
   */
  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public User me() {
    return service.getCurrentUser();
  }//Spring hands off to Jackson for serialization into JSON object

}
