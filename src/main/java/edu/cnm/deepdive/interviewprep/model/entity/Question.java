package edu.cnm.deepdive.interviewprep.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 * This is our Question entity class that represents Question objects in the Database. It is keeping
 * track of all attributes (i.e., id, externalKey, created, question, answer, source).
 */
@SuppressWarnings("JpaDataSourceORMInspection")
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
  @JsonIgnore
  private UUID id;

  @Column(nullable = false, updatable = false, columnDefinition = "UUID", unique = true)
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey = UUID.randomUUID();

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", updatable = false)
  @JsonIgnore
  private User user;

  @Column(nullable = false, updatable = true, unique = true, length = 2000)
  private String question;

  @Column(nullable = true, updatable = true, length = 2000)
  private String answer;

  @Column(nullable = true, updatable = true, length = 100)
  private String source;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
  @JoinTable(
      name = "question_category",
      joinColumns = @JoinColumn(name = "question_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  @OrderBy("name ASC")
  private Set<Category> categories = new LinkedHashSet<>();

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
   * Returns a question in the form of a string.
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Sets a question in the form of a string.
   */
  public void setQuestion(String question) {
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

  /**
   * Returns a source in the form of a string.
   */
  public String getSource() {
    return source;
  }

  /**
   * Sets a source in the form of a string.
   * <p>
   * source
   */
  public void setSource(String source) {
    this.source = source;
  }

  public Set<Category> getCategories() {
    return categories;
  }
}

