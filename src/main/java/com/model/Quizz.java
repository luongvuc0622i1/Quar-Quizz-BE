package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "quizz")
public class Quizz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "quizz_answer", joinColumns = {@JoinColumn(name = "quizz_id")},
//    inverseJoinColumns ={@JoinColumn (name = "answer_id")})
//    private Set<Answer> answer;

    private String answer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quizz_typequizz",joinColumns = {@JoinColumn(name = "quizz_id")},
    inverseJoinColumns = {@JoinColumn(name = "typequizz_id")})
    private Set<TypeQuizz>typeQuizzes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quizz_category",joinColumns = {@JoinColumn(name = "quizz_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category>categories;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quizz_test",joinColumns = {@JoinColumn(name = "quizz_id")},
            inverseJoinColumns = {@JoinColumn(name = "test_id")})
    private Set<Test> tests;
    private String name;
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
}
