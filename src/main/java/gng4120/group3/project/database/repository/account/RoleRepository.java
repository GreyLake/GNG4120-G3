package gng4120.group3.project.database.repository.account;

import gng4120.group3.project.models.user.ERole;
import gng4120.group3.project.models.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
