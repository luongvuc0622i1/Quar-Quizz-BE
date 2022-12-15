package com.repository.jwt;

import com.model.jwt.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    AppUser findAppUserByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM users " +
            "JOIN users_roles ON users.id = users_roles.app_user_id " +
            "JOIN role ON users_roles.roles_id = role.id " +
            "WHERE roles_id = 3;")
    Iterable<AppUser> findAppUserByRolesUser();

}
