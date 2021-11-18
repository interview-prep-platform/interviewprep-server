package edu.cnm.deepdive.interviewprep.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 * This is our Question entity class that represents Question objects in the Database. It is keeping
 * track of all attributes (i.e., id, externalKey, created, question, answer, source).
 */
@Entity
@Table(
    indexes = {
        @Index(columnList = "created")
    }
)
public class Question {

  @Id
  @GeneratedValue
  @Column(name = "question_id", nullable = false, updatable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(nullable = false, updatable = false, columnDefinition = "UUID", unique = true)
  private UUID externalKey = UUID.randomUUID();

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", updatable = false)
  @JsonIgnore
  private User user;

  @Column(nullable = false, updatable = false, unique = true, length = 2000)
  private String question;

  @Column(nullable = true, updatable = false, length = 2000)
  private String answer;

  @Column(nullable = true, updatable = false, length = 100)
  private String source;

  @Column(nullable = true, updatable = true, length = 2000)
  private String userAnswer;

  /**
   * Returns the primary key identifier for this instance.
   *
   * @return
   */
  public UUID getId() {
    return id;
  }

  /**
   * Returns a unique external key identifier for this instance.
   *
   * @return
   */
  public UUID getExternalKey() {
    return externalKey;
  }

  /**
   * Returns an object creation Date for this instance.
   *
   * @return
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Returns a User object for this instance.
   *
   * @return
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets a User object for this instance.
   *
   * @param user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Returns a question in the form of a string.
   *
   * @return
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Sets a question in the form of a string.
   *
   * @param question
   */
  public void setQuestion(String question) {
    this.question = question;
  }

  /**
   * Returns an answer in the form of a string.
   *
   * @return
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Sets an answer in the form of a string.
   *
   * @param answer
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  /**
   * Returns a source in the form of a string.
   *
   * @return
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets a source in the form of a string.
   *
   * @param source
   */
  public void setSource(String source) {
    this.source = source;
  }

  /**
   * Returns a user answer in the form of a string.
   *
   * @return
   */
  public String getUserAnswer() {
    return userAnswer;
  }

  /**
   * Sets a user answer in the form of a string.
   *
   * @param userAnswer
   */
  public void setUserAnswer(String userAnswer) {
    this.userAnswer = userAnswer;
  }
}

