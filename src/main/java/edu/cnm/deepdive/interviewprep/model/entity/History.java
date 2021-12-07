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
 * This is our History entity class that represents History objects in the Database. It is keeping
 * track of all attributes (i.e., id, externalKey, created, answer).
 */
@Entity
@Table(
    indexes = {
        @Index(columnList = "created")
    }
)
public class History {

  @Id
  @GeneratedValue
  @Column(name = "history_id", nullable = false, updatable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(nullable = false, updatable = false, columnDefinition = "UUID", unique = true)
  private UUID externalKey = UUID.randomUUID();

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @ManyToOne(fetch = FetchType.LAZY,optional = false)
  @JoinColumn(name = "user_id", updatable = false,nullable = false)
  @JsonIgnore
  private User user;

  @ManyToOne(fetch = FetchType.LAZY,optional = false)
  @JoinColumn(name = "question_id", updatable = false,nullable = false)
  @JsonIgnore
  private Question question;

  @Column(nullable = true, updatable = false, length = 2000)
  private String answer;

  /**
   * Returns the primary key identifier for this instance.
   */
  public UUID getId() {
    return id;
  }

  /**
   * Returns a unique external key identifier for this instance.
   */
  public UUID getExternalKey() {
    return externalKey;
  }

  /**
   * Returns an object creation Date for this instance.
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Returns a User object for this instance.
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets a User object for this instance.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Returns a Question object for this instance.
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * Sets a Question object for this instance.
   */
  public void setQuestion(Question question) {
    this.question = question;
  }

  /**
   * Returns an answer in the form of a string.
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Sets an answer in the form of a string.
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

}