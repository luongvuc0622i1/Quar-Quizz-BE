package com.repository.quiz;

import com.model.Category;
import com.model.TypeQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeRepository extends CrudRepository<TypeQuiz,Long> {
    Page<TypeQuiz> findAll(Pageable pageable);
}
