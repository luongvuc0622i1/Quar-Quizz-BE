package com.repository.jwt;

import com.model.jwt.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
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

    @Query(nativeQuery = true, value = "SELECT * FROM users " +
            "JOIN users_roles ON users.id = users_roles.app_user_id " +
            "JOIN role ON users_roles.roles_id = role.id " +
            "WHERE roles_id = 3 OR roles_id = 2;")
    Iterable<AppUser> findAppUserByRolesUserManager();
    @Modifying
    @Query(value = "UPDATE users SET status = '1' WHERE id = :statusId", nativeQuery = true)
    void openAccountById(Long statusId);
    @Modifying
    @Query(value = "UPDATE users SET status = '0' WHERE id = :statusId", nativeQuery = true)
    void lockAccountById(Long statusId);
    @Modifying
    @Query(value = "UPDATE users_roles join users on users_roles.app_user_id = users.id set users_roles.roles_id = 2 where users.username = :name", nativeQuery = true )
    void changeManager(String name);
    @Modifying

    @Query(value = "UPDATE users_roles join users on users_roles.app_user_id = users.id set users_roles.roles_id = 3 where users.username = :name", nativeQuery = true )
    void changeUser(String name);
    AppUser findByEmail(String email);
}
