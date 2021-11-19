package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.CategoryRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Category;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;


/**
 * This class implements the high level persistence and business logic for the {@link Category} entity.
 */
@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  /**
   * Constructor that instantiates a new {@link Category} service object.
   *
   * @param categoryRepository Category repository object.
   */
  public CategoryService(
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  /**
   * Gets the current {@link Category} object records from the database identified by id.
   *
   * @param externalKey Id in the form of a universally unique identifier.
   * @return An optional Category object.
   */
  public Optional<Category> get(UUID externalKey) {
    return categoryRepository.findByExternalKey(externalKey);
  }

  /**
   * Returns a list of all {@link Category} in the database.
   *
   * @return A list of all Category objects.
   */
  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }

  /**
   * Deletes a {@link Category} object identified by external key.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   */
  public void delete(UUID externalKey) {
    categoryRepository
        .findByExternalKey(externalKey)
        .ifPresent(categoryRepository::delete);
  }

  /**
   * Saves a {@link Category} object to the database identified by name.
   *
   * @param name A category name in the form of a string.
   * @return A Category object.
   */
  public Category saveCategory(String name) {
    Category category = new Category();
    category.setName(name);
    return categoryRepository.save(category);
  }

  /**
   * Creates a new {@link Category} object in the database.
   *
   * @param category A Category object.
   * @return A Category object.
   */
  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  /**
   * Updates a {@link Category} object to the database for the current user.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @param category A Category object.
   * @return A Category object.
   */
  public Category updateCategory(UUID externalKey, Category category) {
    Category updatedCategory = categoryRepository.findByExternalKey(externalKey).orElseThrow();
    updatedCategory.setName(category.getName());
    return categoryRepository.save(updatedCategory);
  }

}
