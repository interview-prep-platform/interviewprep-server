package edu.cnm.deepdive.interviewprep.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 * This is our Question entity class table that represents Question objects in the Database.
 * It is keeping track of all attributes (i.e., id, externalKey, created, question, answer, source).
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

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getUserAnswer() {
    return userAnswer;
  }

  public void setUserAnswer(String userAnswer) {
    this.userAnswer = userAnswer;
  }
}

