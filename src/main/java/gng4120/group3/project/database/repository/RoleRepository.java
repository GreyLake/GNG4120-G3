package gng4120.group3.project.database.repository;

import gng4120.group3.project.models.ERole;
import gng4120.group3.project.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
