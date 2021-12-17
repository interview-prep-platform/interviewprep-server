package edu.cnm.deepdive.interviewprep.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.interviewprep.model.dao.CategoryRepository;
import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Category;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Profile("preload")
public class Preload implements CommandLineRunner {

  private static final String INITIAL_QUESTIONS_DATA_RESOURCE = "preload/initial-data.json";
  private static final String INITIAL_CATEGORIES_DATA_RESOURCE = "preload/categories.json";
  private final QuestionRepository questionRepository;
  private final CategoryRepository categoryRepository;

  @Autowired
  public Preload(QuestionRepository questionRepository,
      CategoryRepository categoryRepository) {
    this.questionRepository = questionRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    preloadQuestions();
    //preloadCategories();
  }

  private void preloadQuestions() throws IOException {
    ClassPathResource resource = new ClassPathResource(INITIAL_QUESTIONS_DATA_RESOURCE);
    try (InputStream input = resource.getInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      List<Question> listQuestions = mapper.readValue(input, new TypeReference<List<Question>>() {
      });
      questionRepository.saveAllAndFlush(listQuestions);
    }
  }

  private void preloadCategories() throws IOException {
    ClassPathResource resource = new ClassPathResource(INITIAL_CATEGORIES_DATA_RESOURCE);
    try (InputStream input = resource.getInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      String[] categoryNames = mapper.readValue(input, String[].class);
      List<Category> categories = Stream
          .of(categoryNames)
          .map((name) -> {
            Category category = new Category();
            category.setName(name);
            return category;
          })
          .collect(Collectors.toList());
      categoryRepository.saveAllAndFlush(categories);
    }
  }

}
