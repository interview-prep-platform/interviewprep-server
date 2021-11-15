package edu.cnm.deepdive.interviewprep.model.dao;

import edu.cnm.deepdive.interviewprep.model.entity.History;
import edu.cnm.deepdive.interviewprep.model.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<History, UUID> {

  Optional<History> findByExternalKey(UUID key);

  Optional<History> findByExternalKeyAndUser(UUID key, User user);

}