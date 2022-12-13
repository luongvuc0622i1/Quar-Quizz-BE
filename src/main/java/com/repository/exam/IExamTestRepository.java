package com.repository.exam;

import com.model.ExamTest;
import com.model.jwt.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExamTestRepository extends JpaRepository<ExamTest, Long> {
//    Iterable<ExamTest> findExamTestsByUserId (Long user_id);
@Query(value = "select * from exam_ where app_user_id =:appUserId",nativeQuery = true)
List<AppUser> findAppUserById(Long appUserId);
}
