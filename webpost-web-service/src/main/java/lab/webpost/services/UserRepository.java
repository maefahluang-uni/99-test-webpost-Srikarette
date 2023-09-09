package lab.webpost.services;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.webpost.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // TODO: add necessary derived methods
    User findByUsername(String username);

    @Override
    User save(User entity);
}
