package edu.cnm.deepdive.interviewprep.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 * This is our Category entity class table.  It is keeping track of all attributes (i.e., id,
 * externalKey, created, and name).
 */
@Entity
@Table(
    indexes = {
        @Index(columnList = "created")
    }
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id", "created", "name"})
public class Category {

  @Id
  @GeneratedValue
  @Column(name = "category_id", updatable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID id;


  @Column(nullable = false, updatable = false, columnDefinition = "UUID", unique = true)
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey = UUID.randomUUID();

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Date created;

  @Column(nullable = false, unique = true, length = 40)
  private String name;

  @ManyToMany(fetch = FetchType.LAZY,mappedBy = "categories")
  @OrderBy("question ASC")
  private List<Question> questions = new LinkedList<>();

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
   * Returns a name for the Category.
   */
  public String getName() {
    return name;
  }

  /**
   * Set a name for the Category.
   */
  public void setName(String name) {
    this.name = name;
  }

  public List<Question> getQuestions() {
    return questions;
  }
}

