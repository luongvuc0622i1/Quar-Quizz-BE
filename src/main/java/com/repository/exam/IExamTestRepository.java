package com.repository.exam;

import com.model.ExamTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface IExamTestRepository extends JpaRepository<ExamTest, Long> {
//    Iterable<ExamTest> findExamTestsByUserId (Long user_id);
    @Query(nativeQuery = true, value = "SELECT * FROM quarquizz.exam_test where app_user_id = :id")
    Iterable<ExamTest> findExamTestsByAppUser(@Param("id") Long id);

}
