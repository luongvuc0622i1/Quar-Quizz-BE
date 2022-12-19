package com.model;

import com.model.jwt.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
@Table(name = "examQuiz")
public class ExamQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @NotNull
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @NotNull
    private Test test;

    @NotNull
    private String answerUser;

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    @NotNull
    private AppUser appUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
