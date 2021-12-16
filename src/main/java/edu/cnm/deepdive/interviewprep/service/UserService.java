package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.UserRepository;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * This class implements the high level persistence and business logic for the User entity.
 */
@Service
@Profile("service")
public class UserService implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final UserRepository repository;

  /**
   * Constructor that instantiates a new User repository object.
   *
   * @param repository User repository object.
   */
  @Autowired
  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt source) {
    Collection<SimpleGrantedAuthority> grants =
        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    return new UsernamePasswordAuthenticationToken(
        getOrCreate(
            source.getSubject(), source.getClaimAsString("name")),
        source.getTokenValue(), grants);
  }


  /**
   * Queries the database for a {@link User} object defined by Oauth key. If a User object doesn't exist it
   * gets added to the database.
   *
   * @param oauthKey    An OAuth key in the form of a string.
   * @param displayName A display name in the form of a string.
   * @return A User object.
   */
  public User getOrCreate(String oauthKey, String displayName) {
    return repository
        .findByOauthKey(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setOauthKey(oauthKey);
          user.setDisplayName(displayName);
          return repository.save(user);
        });
  }

  /**
   * Returns a {@link User} object that matches the id.
   *
   * @param id Id in the form of a universally unique identifier.
   * @return An optional User object.
   */
  public Optional<User> get(UUID id) {
    return repository.findById(id);
  }

  /**
   * Returns a {@link User} that matches the external key.
   *
   * @param externalKey External key in the form of a universally unique identifier.
   * @return An optional User object.
   */
  public Optional<User> getByExternalKey(UUID externalKey) {
    return repository.findByExternalKey(externalKey);
  }

  /**
   * Returns a list of {@link User} ordered by display name in ascending order.
   *
   * @return A list of User objects.
   */
  public Iterable<User> getAll() {
    return repository.getAllByOrderByDisplayNameAsc();
  }

  /**
   * Saves a User object to the database.
   *
   * @param user A User object.
   * @return A User object.
   */
  public User save(User user) {
    return repository.save(user);
  }

  /**
   * Deletes a {@link User} object that matches the parameter user from the database.
   *
   * @param user A User Object.
   */
  public void delete(User user) {
    repository.delete(user);
  }

  /**
   * Gets a current {@link User} object from the database.
   *
   * @return A User object.
   */
  public User getCurrentUser() {
    return (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
  }

  /**
   * Updates the current {@link User} object records from the provided updated User record, and saves the
   * result to the database.
   *
   * @param updateUser User deserialized from body of request.
   * @param user       Current requestor.
   * @return Updated user instance.
   */
  public User update(User updateUser, User user) {
    if (updateUser.getDisplayName() != null) {
      user.setDisplayName(updateUser.getDisplayName());
    }
    return save(user);
  }

}
