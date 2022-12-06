package com.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Role> roles;

}
