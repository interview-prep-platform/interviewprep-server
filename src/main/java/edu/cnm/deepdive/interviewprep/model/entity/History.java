package edu.cnm.deepdive.interviewprep.model.entity;

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
 * This is our History entity class table.  It is keeping track of all attributes (i.e., id,
 * externalKey, created, userQuestion, userAnswer, and two foriegn keys: userId and questionId).
 */
@Entity
@Table(
    name = "history",
    indexes = {
        @Index(columnList = "created")
    }
)
public class History {

  @Id
  @GeneratedValue
  @Column(name = "history_id", updatable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(nullable = false, updatable = false, columnDefinition = "UUID", unique = true)
  private UUID externalKey = UUID.randomUUID();

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id", nullable = false, updatable = false)
  private Question question;

  @Column(nullable = true, updatable = true, length = 2000)
  private String userQuestion;

  @Column(nullable = true, updatable = true, length = 2000)
  private String userAnswer;

  public UUID getId() {
    return id;
  }

  public UUID getExternalKey() {
    return externalKey;
  }

  public Date getCreated() {
    return created;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public String getUserAnswer() {
    return userAnswer;
  }

  public void setUserAnswer(String answer) {
    this.userAnswer = answer;
  }

  public String getUserQuestion() {
    return userQuestion;
  }

  public void setUserQuestion(String userQuestion) {
    this.userQuestion = userQuestion;
  }
}
