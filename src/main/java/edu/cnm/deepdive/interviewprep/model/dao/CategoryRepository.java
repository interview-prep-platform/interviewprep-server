package edu.cnm.deepdive.interviewprep.model.dao;

import edu.cnm.deepdive.interviewprep.model.entity.Category;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface defines various methods that can be used to query the database.
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {

  /**
   * Returns a Category object that matches the external key.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @return An optional Category object.
   */
  Optional<Category> findByExternalKey(UUID externalKey);

}
