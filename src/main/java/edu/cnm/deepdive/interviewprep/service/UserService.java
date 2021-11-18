package edu.cnm.deepdive.interviewprep.service;

import edu.cnm.deepdive.interviewprep.model.dao.UserRepository;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserService implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final UserRepository repository;

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

  //Operation to look into the database and if a User doesn't exist we add it in.
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

  public Optional<User> get(UUID id) {
    return repository.findById(id);
  }

  public Optional<User> getByExternalKey(UUID key) {
    return repository.findByExternalKey(key);
  }

  public Iterable<User> getAll() {
    return repository.getAllByOrderByDisplayNameAsc();
  }

  public User save(User user) {
    return repository.save(user);
  }

  public void delete(User user) {
    repository.delete(user);
  }

  /**
   * Gets the current user records from the database.
   *
   * @return
   */
  public User getCurrentUser() {
    return (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
  }
  /**
   * Updates the current user records from the provided updated user record, and saves the result to the database.
   *
   * @param updateUser User deserialized from body of request.
   * @param user Current requestor.
   * @return Updated user instance.
   */
  public User update(User updateUser, User user) {
    if (updateUser.getDisplayName() != null) {
      user.setDisplayName(updateUser.getDisplayName());
    }
    return save(user);
  }

}
