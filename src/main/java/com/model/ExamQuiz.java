package com.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "exam_quiz")
public class ExamQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answerUser;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id",referencedColumnName = "id")
    private Quiz quiz;



}
