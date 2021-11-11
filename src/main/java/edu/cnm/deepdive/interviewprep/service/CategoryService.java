package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.CategoryRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Category;
import java.util.Optional;
import java.util.UUID;

public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Optional<Category> get(UUID id) {
    return categoryRepository.findById(id);
  }

  public void delete(UUID id) {
    categoryRepository.deleteById(id);
  }

  public Category saveCategory(String name) {
    Category category = new Category();
    category.setName(name);
    return categoryRepository.save(category);
  }

}
