package gng4120.group3.project.database.repository;

import gng4120.group3.project.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
}
