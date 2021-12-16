package edu.cnm.deepdive.interviewprep.controller;

import edu.cnm.deepdive.interviewprep.model.entity.Category;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.service.CategoryService;
import edu.cnm.deepdive.interviewprep.service.QuestionService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class creates REST endpoints that has access to the Question entity in the database through
 * the Category services. Identified by the URL /interviewprep/categories.
 */
@RestController
@RequestMapping("/categories")
@Profile("service")
public class CategoryController {

  private final CategoryService categoryService;

  /**
   * Constructor that instantiates a new User service object.
   *
   * @param categoryService Category service object.
   */
  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /**
   * This method defines the behavior of a GET request to the URL /interviewprep/categories.
   *
   * @return a list of all categories from database via the category service.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Category> get() {
    return categoryService
        .getCategories();
  }

  /**
   * This method defines the behavior of a GET request to the URL /interviewprep/categories/externalKey.
   * It grabs the current category from the Category service.
   *
   * @param externalKey External key in the form of a universally unique identifier as identified by
   *                    the path variable external key.
   * @return A Category object in the form of JSON.
   */
  @GetMapping(value = "/{externalKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Category get(@PathVariable UUID externalKey) {
    return categoryService
        .get(externalKey)
        .orElseThrow();
  }

  /**
   * This method defines the behavior of a POST request to the URL /interviewprep/categories. It
   * creates a new category through the Category service.
   *
   * @param category A Category Object in the form of request body.
   * @return A Category object in the form of JSON.
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Category post(@RequestBody Category category) {
    return categoryService.createCategory(category);
  }


  /**
   * This method defines the behavior of a PUT request to the URL /interviewprep/categorie/categoryId.
   * It updates a category through the Category service.
   *
   * @param categoryId An id in the form of a universally unique identifier as identified by *    *
   *                   the path variable categoryId.
   * @param category   A Category Object in the form of request body.
   * @return A Category object in the form of JSON.
   */
  @PutMapping(value = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Category put(@PathVariable UUID categoryId, @RequestBody Category category) {
    return categoryService.updateCategory(categoryId, category);
  }

  /**
   * This method defines the behavior of a DELETE request to the URL /interviewprep/categories/externalkey.
   * Returns a HTTP no content status via header.
   *
   * @param externalKey External key in the form of a universally unique identifier as identified by
   *                    the path variable external key.
   */
  @DeleteMapping(value = "/{externalKey}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID externalKey) {
    categoryService.delete(externalKey);
  }


}
