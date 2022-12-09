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

import java.util.List;

@Repository
public interface IQuizRepository extends PagingAndSortingRepository<Quiz, Long>{
    Page<Quiz> findQuizzesByNameContaining(String name, Pageable pageable);

    Page<Quiz> findAll(Pageable pageable);


    @Query(value = "select q.id,q.name,c.category_name from quiz_category qc join quiz q on  qc.quiz_id= q.id join category c on\n" +
            "    qc.category_id = c.id where c.name LIKE '%category%'" ,nativeQuery = true)
    Iterable<Quiz> findQuizzesByCategoriesContaining (@Param("category") String category);

    @Query(value = "select q.id,q.name,tq.name from quiz_typequiz qtq join quiz q on  qtq.quiz_id= q.id join typequiz tq on\n" +
            "    qtq.typequiz_id = tq.id where tq.name LIKE '%type%'" ,nativeQuery = true)
    Iterable<Quiz> findQuizzesByTypesContaining (@Param("type") String type);

    @Query(value = "select q.id,q.name, l.name from quiz q join level l on  q.level_id= l.id where l.name LIKE '%level%'" ,nativeQuery = true)
    Iterable<Quiz> findQuizzesByLevelContaining (@Param("level") String level);

}
