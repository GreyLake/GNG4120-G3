package gng4120.group3.project.models.initializers;

import gng4120.group3.project.database.MongoConfig;
import gng4120.group3.project.database.repository.account.RoleRepository;
import gng4120.group3.project.models.user.ERole;
import gng4120.group3.project.models.user.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleInitializer {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private MongoConfig mongoConfig;

    @PostConstruct
    public void init() {
        // Iterate over each enum value and save the role if it doesn't exist
        if(mongoConfig.isMongoDBAvailable()) {
            for (ERole roleName : ERole.values()) {
                saveRoleIfNotExists(roleName);
            }
        }
    }

    private void saveRoleIfNotExists(ERole roleName) {
        // Check if role exists
        Optional<Role> existingRole = roleRepository.findByName(roleName);
        if (existingRole.isEmpty()) {
            // Save roles to the database using the repository
            Role role = new Role(roleName);
            roleRepository.save(role);
        }
    }
}
