package com.service.jwt.user;

import com.model.jwt.AppUser;
import com.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.facebook.api.Account;

public interface IUserService extends IGeneralService<AppUser>, UserDetailsService {
    UserDetails loadUserByUsername(String username);

    AppUser getUserByUsername(String username);

    AppUser findAppUserByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    AppUser findByUsername(String username);

    Iterable<AppUser> findAppUserByRolesUser();

}
