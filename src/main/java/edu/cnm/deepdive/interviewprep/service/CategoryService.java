package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.CategoryRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Category;
import java.util.Optional;
import java.util.UUID;


/**
 * This class implements the high level persistence and business logic for the Category entity.
 */
public class CategoryService {

  private final CategoryRepository categoryRepository;

  /**
   * Constructor that instantiates a new Category service object.
   * @param categoryRepository Category repository object.
   */
  public CategoryService(
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  /**
   * Gets the current Category object records from the database identified by id.
   * @param id Id in the form of a universally unique identifier.
   * @return An optional Category object.
   */
  public Optional<Category> get(UUID id) {
    return categoryRepository.findById(id);
  }



  /**
   * Deletes a Category object identified by the id.
   * @param id An Id in the form of a universally unique identifier.
   */
  public void delete(UUID id) {
    categoryRepository.deleteById(id);
  }



  /**
   * Saves a Category object to the database identified by name.
   * @param name A category name in the form of a string.
   * @return A Category object.
   */
  public Category saveCategory(String name) {
    Category category = new Category();
    category.setName(name);
    return categoryRepository.save(category);
  }

}
