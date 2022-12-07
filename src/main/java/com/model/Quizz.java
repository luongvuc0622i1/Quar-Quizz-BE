package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String answer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quizz_typequizz",joinColumns = {@JoinColumn(name = "quizz_id")},
    inverseJoinColumns = {@JoinColumn(name = "typequizz_id")})
    @NotNull
    private Set<TypeQuizz>typeQuizzes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quizz_category",joinColumns = {@JoinColumn(name = "quizz_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    @NotNull
    private Set<Category>categories;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quizz_test",joinColumns = {@JoinColumn(name = "quizz_id")},
            inverseJoinColumns = {@JoinColumn(name = "test_id")})
    @NotNull
    private Set<Test> tests;
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "level_id")
    @NotNull
    private Level level;
}
