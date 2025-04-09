package org.vikas.todoapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vikas.todoapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsUserByUsername(String username);

    User findByUsername(String username);
}
