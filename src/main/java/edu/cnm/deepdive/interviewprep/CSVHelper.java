package edu.cnm.deepdive.interviewprep;

import edu.cnm.deepdive.interviewprep.model.entity.Question;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Question", "Answer", "Source" };

//  public static boolean hasCSVFormat(MultipartFile file) {
//    if (TYPE.equals(file.getContentType())
//        || file.getContentType().equals("application/vnd.ms-excel")) {
//      return true;
//    }
//
//    return false;
//  }

  public static List<Question> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Question> questionList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Question question = new Question();
        question.setQuestion(csvRecord.get("Question"));
        question.setAnswer(csvRecord.get("Answer"));
        question.setSource(csvRecord.get("Source"));
        questionList.add(question);
      }
      return questionList;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

//  public static ByteArrayInputStream tutorialsToCSV(List<Question> QuestionList) {
//    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//
//    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
//      for (Question Question : QuestionList) {
//        List<String> data = Arrays.asList(
//            String.valueOf(Question.getId()),
//            Question.getTitle(),
//            Question.getDescription(),
//            String.valueOf(Question.isPublished())
//        );
//
//        csvPrinter.printRecord(data);
//      }
//
//      csvPrinter.flush();
//      return new ByteArrayInputStream(out.toByteArray());
//    } catch (IOException e) {
//      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
//    }
//  }
}

