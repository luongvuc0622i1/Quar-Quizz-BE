package com.model.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)",unique = true,nullable = false)
    @Email
    private String email;

    @Column(columnDefinition = "varchar(255)",unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    @Pattern(regexp = "^([A-Z]{1})([a-z]{4,})([0-9]{1,})")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

}
