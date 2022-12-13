package com.repository.exam;

import com.model.ExamTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamTestRepository extends JpaRepository<ExamTest, Long> {
}
