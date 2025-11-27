package ie.atu.user_service.repository;

import ie.atu.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserID(String userId);
}
