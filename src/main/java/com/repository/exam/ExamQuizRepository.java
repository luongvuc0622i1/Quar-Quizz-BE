package com.repository.exam;

import com.model.ExamQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamQuizRepository extends JpaRepository<ExamQuiz, Long> {
}
