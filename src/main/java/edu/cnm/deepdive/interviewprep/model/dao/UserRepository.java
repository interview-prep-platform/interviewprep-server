package edu.cnm.deepdive.interviewprep.model.dao;

import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

//Like our Daos but already knows how to do most of things we defined in our Per. Projects.
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByOauthKey(String oauthKey);

  Optional<User> findByOauthKey(UUID externalKey);

  Optional<User> findByExternalKey(UUID externalKey);

  Iterable<User> getAllByOrderByDisplayNameAsc();
}
