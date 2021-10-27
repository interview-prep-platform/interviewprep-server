package edu.cnm.deepdive.interviewprep.model.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
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

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @Column(nullable = false, updatable = false, unique = true, length = 300)
  private String question;

  @Column(nullable = true, updatable = false, length = 2000)
  private String answer;

  @Column(nullable = true, updatable = false, length = 100)
  private String source;

}
