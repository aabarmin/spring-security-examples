package dev.abarmin.spring.security.user.service.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Aleksandr Barmin
 */
public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, String> {
}
