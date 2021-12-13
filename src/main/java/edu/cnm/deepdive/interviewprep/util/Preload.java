package edu.cnm.deepdive.interviewprep.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.interviewprep.model.dao.QuestionRepository;
import edu.cnm.deepdive.interviewprep.model.entity.Question;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Profile("preload")
public class Preload implements CommandLineRunner {

  public static final String INITIAL_DATA_RESOURCE = "preload/initial-data.json";
  private final QuestionRepository repository;

  @Autowired
  public Preload(QuestionRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    ClassPathResource resource = new ClassPathResource(INITIAL_DATA_RESOURCE);
    try (InputStream input = resource.getInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      List<Question> listQuestions = mapper.readValue(input, new TypeReference<List<Question>>(){});
      System.out.println("ListQuestions: " + listQuestions.toString());
    }
  }
}
