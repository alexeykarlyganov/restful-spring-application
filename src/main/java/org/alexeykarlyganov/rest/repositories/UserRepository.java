package org.alexeykarlyganov.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.alexeykarlyganov.rest.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
