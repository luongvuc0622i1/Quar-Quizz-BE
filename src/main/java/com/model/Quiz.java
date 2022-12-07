package com.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "quiz_answer", joinColumns = {@JoinColumn(name = "quiz_id")},
//    inverseJoinColumns ={@JoinColumn (name = "answer_id")})
//    private Set<Answer> answer;
    @NotNull
    private String answer;

    @NotNull
    private String correct_answer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quiz_typequiz",joinColumns = {@JoinColumn(name = "quiz_id")},
    inverseJoinColumns = {@JoinColumn(name = "typequiz_id")})
    @NotNull
    private Set<TypeQuiz> typeQuizzes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quiz_category",joinColumns = {@JoinColumn(name = "quiz_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    @NotNull
    private Set<Category>categories;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quiz_test",joinColumns = {@JoinColumn(name = "quiz_id")},
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
