package com.repository.quiz;

import com.model.Category;
import com.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuizRepository extends PagingAndSortingRepository<Quiz, Long>, JpaRepository<Quiz,Long> {
    Page<Quiz> findQuizzesByNameContaining(String name, Pageable pageable);

    Page<Quiz> findAll(Pageable pageable);

    @Query(value = "select q.id,q.name from quiz_category qc join quiz q on  qc.quiz_id= q.id join category c on\n" +
            "    qc.category_id = c.id where c.name LIKE :addefff" ,nativeQuery = true)
    Iterable<Quiz> findQuizzesByCategoriesContaining (@Param("position") String position);

//    @Query(value = "select p from Player p join Status s on p.status.id = s.id where s.status LIKE %:status%")
//    Iterable<Quiz> findQuizzesByTypeQuizContaining (@Param("status")String status);
//
//    @Query(value = "select p from Player p join Status s on p.status.id = s.id where s.status LIKE %:status%")
//    Iterable<Quiz> findQuizzesByLevelContaining (@Param("status")String status);

}
