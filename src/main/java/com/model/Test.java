package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_quizz",joinColumns = {@JoinColumn(name = "test_id")},
            inverseJoinColumns = {@JoinColumn(name = "quizz_id")})
    private Set<Quizz> quizzes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_level",joinColumns = {@JoinColumn(name = "test_id")},
            inverseJoinColumns = {@JoinColumn(name = "level_id")})
    private Set<Level> levels;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_category",joinColumns = {@JoinColumn(name = "test_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "test_typeTest",joinColumns = {@JoinColumn(name = "test_id")},
            inverseJoinColumns = {@JoinColumn(name = "typeTest_id")})
    private Set<TypeTest> typeTests;
    private String passCore;
    private Date maxTime;
    private String name;
}
