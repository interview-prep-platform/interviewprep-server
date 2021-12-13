package edu.cnm.deepdive.interviewprep;

import java.io.InputStream;
import javax.naming.Context;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * This is the main class that initiates the Spring server application.
 */
@SpringBootApplication
public class InterviewprepServerApplication implements ApplicationContextAware {


  /**
   * The main entry point to the server application.
   *
   * @param args A String array of arguments.
   */
  public static void main(String[] args) {
    try {
      InputStream input = context.getResources().openRawResource(R.raw.doctors);
      CSVHelper.csvToTutorials(input);
    } catch (Exception e) {

    }
    SpringApplication.run(InterviewprepServerApplication.class, args);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

  }
}
