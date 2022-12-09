package com.repository.jwt;

import com.model.jwt.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    AppUser findAppUserByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
