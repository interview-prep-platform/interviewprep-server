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
      name = "category",
      indexes = {
          @Index(columnList = "created")
      }
  )
  public class Category {
    //TODO: put in relationships

    @Id
    @GeneratedValue
    @Column(name = "category_id", updatable = false, columnDefinition = "UUID")
    private UUID id;

    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date created;

    @Column(nullable = false, updatable = true, unique = true, length = 40)
    private String name;

  }
}
