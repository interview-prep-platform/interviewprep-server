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

/**
 * This is our Category entity class table.  It is keeping track of all attributes (i.e., id, externalKey, created, and name).
 */
@Entity
@Table(
    indexes = {
        @Index(columnList = "created")
    }
)
public class Category {

  @Id
  @GeneratedValue
  @Column(name = "category_id", updatable = false, columnDefinition = "UUID")
  private UUID id;

  @Column(nullable = false, updatable = false, columnDefinition = "UUID", unique = true)
  private UUID externalKey = UUID.randomUUID();

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @Column(nullable = false, updatable = true, unique = true, length = 40)
  private String name;

  /**
   * Returns the primary key identifier for this instance.
   * @return
   */
  public UUID getId() {
    return id;
  }

  /**
   * Returns a unique external key identifier for this instance.
   * @return
   */
  public UUID getExternalKey() {
    return externalKey;
  }

  /**
   * Returns an object creation Date for this instance.
   * @return
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Returns a name for the Category.
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Set a name for the Category.
   * @return
   */
  public void setName(String name) {
    this.name = name;
  }
}

