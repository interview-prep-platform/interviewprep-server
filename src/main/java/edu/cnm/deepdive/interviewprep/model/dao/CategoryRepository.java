package edu.cnm.deepdive.interviewprep.model.dao;

import edu.cnm.deepdive.interviewprep.model.entity.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
