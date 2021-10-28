package edu.cnm.deepdive.interviewprep.model.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(
    name = "question",
    indexes = {
        @Index(columnList = "created")
    }
)
public class Question {

  @Id
  @GeneratedValue
  @Column(name = "question_id", updatable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(nullable = false, updatable = false, columnDefinition = "UUID", unique = true)
  private UUID externalKey = UUID.randomUUID();

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @Column(nullable = false, updatable = false, unique = true, length = 2000)
  private String question;

  @Column(nullable = true, updatable = false, length = 2000)
  private String answer;

  @Column(nullable = true, updatable = false, length = 100)
  private String source;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY,
      cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("created DESC")
  private final List<History> history = new LinkedList<>();

  public UUID getId() {
    return id;
  }

  public UUID getExternalKey() {
    return externalKey;
  }

  public Date getCreated() {
    return created;
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

  public List<History> getHistory() {
    return history;
  }
}

