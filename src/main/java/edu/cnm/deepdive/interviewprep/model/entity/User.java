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

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    name = "user_profile",
    indexes = {
        @Index(columnList = "created")
    }
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"id, created, displayName"})
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_id", updatable = false, columnDefinition = "UUID")
  @JsonIgnore
  private UUID id;

  @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(value = "id", access = Access.READ_ONLY)
  private UUID externalKey = UUID.randomUUID();

  @CreationTimestamp
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @Column(nullable = false, updatable = false, unique = true, length = 30)
  @JsonIgnore
  private String oauthKey;

  @Column(nullable = false, updatable = true, unique = true, length = 100)
  private String displayName;

  //This private field is only saying games has to point at one list. We can change/add to the items of the list.
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("created DESC")
  @JsonIgnore
  private final List<Question> questions = new LinkedList<>();

  //Getters and setters
  public UUID getId() {
    return id;
  }

  public UUID getExternalKey() {
    return externalKey;
  }

  public Date getCreated() {
    return created;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }


  public List<Question> getQuestions() {
    return questions;
  }
}
